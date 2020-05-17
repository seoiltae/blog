package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.PostService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Post;
import blog.vo.Subject;

@WebServlet("/AddPostServlet")
public class AddPostServlet extends HttpServlet{
	private SubjectService subjectService;
	private PostService postService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		// �α����� �ؾ� ���� �� �� �ְ� �Ѵ�
		if(loginMember ==null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		// ���Է½� ������Ʈ����
		subjectService = new SubjectService();
		List<Subject> list = subjectService.getSubjectListAll();
		
		request.setAttribute("loginMember", loginMember.getMemberId());
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/addPost.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String memberId = request.getParameter("memberId");
		String subjectName = request.getParameter("subjectName");
		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("postContent");
		Post post = new Post();
		post.setMemberId(memberId);
		post.setSubjectName(subjectName);
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);
		
		//����Ʈ �Է�
		postService = new PostService();
		postService.addPost(post);
		response.sendRedirect(request.getContextPath()+"/SelectPostBySubjectServlet?subjectName="+post.getSubjectName());
		System.out.println(memberId+subjectName+postTitle+postContent+"AddPostServlet doPost() Ȯ��");
	}
}
