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

import blog.service.PostService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;

@WebServlet("/SelectPostListServlet")
public class SelectPostListServlet extends HttpServlet {
	private final int ROW_PER_PAGE = 5;
	private SubjectService subjectService;
	private PostService postService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember ==null || loginMember.getMemberLevel() >= 10) {
			System.out.println("SelectPostListServlet 인증오류");
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		//view에 옆에 나라배너
		this.subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		//현재 페이지
		int currentPage = 1;
		if(request.getParameter("currentPage") !=null) { //커런트페이지를 인트로 형변환 시켜줌! 매개변수!
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		//포스트 리스트
		postService = new PostService();
		Map<String, Object> map = postService.getPostList(currentPage, ROW_PER_PAGE); //서비스랑 연동!
		request.setAttribute("currentPage", currentPage); // 서블릿에서 선언한 값을 넘기기 위함!
		request.setAttribute("postList", map.get("postlist")); //map 값
		request.setAttribute("lastPage", map.get("lastPage")); // map 값
		request.getRequestDispatcher("/WEB-INF/views/selectPostList.jsp").forward(request, response);
		System.out.println("SelectPostListServlet doPost() 맨밑");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	}

}
