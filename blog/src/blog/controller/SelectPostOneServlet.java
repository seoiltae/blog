package blog.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.CommentService;
import blog.service.LikeyService;
import blog.service.PostService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Post;
import blog.vo.Subject;

@WebServlet("/SelectPostOneServlet")
public class SelectPostOneServlet extends HttpServlet {
	private PostService postService;
	private SubjectService subjectService;
	private LikeyService likeyService;
	private CommentService commentService;
	private final int ROW_PER_PAGE = 5;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		String memberId = request.getParameter("memberId");
		System.out.println(postNo+"<----������ ��ȣ");
		
		//view�� ���� ������
		this.subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		//postNo �󼼺���
		Post post = new Post();
		postService = new PostService();
		post = postService.getselectPostOne(postNo);
		post.setPostNo(postNo);
		
		int currentPage =1;
		if(request.getParameter("currentPage") !=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int totalCk = 0;
		if(request.getParameter("totalCk") !=null) {
			totalCk = Integer.parseInt(request.getParameter("totalCk"));
		}
		System.out.println(totalCk+"<-------------------------"
				+ "-----------------totalCk");
		//comment�� postNo���� ����¡!
		commentService = new CommentService();
		Map<String, Object> map = commentService.getselectPostbyCommentList(postNo, currentPage, ROW_PER_PAGE);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", map.get("lastPage"));
		request.setAttribute("postbyCommentList", map.get("postbyCommentList"));
		
		//postNo ���ƿ� 
		likeyService = new LikeyService();
		int likeyCount = likeyService.getLikeyCount(postNo);
		request.setAttribute("likeyCount", likeyCount);
		int likeynoCount = likeyService.getLikeynoCount(postNo);
		request.setAttribute("likeynoCount", likeynoCount);
		request.setAttribute("post", post);
		request.getRequestDispatcher("/WEB-INF/views/selectPostOne.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
