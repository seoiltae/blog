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
import blog.vo.Post;
import blog.vo.Subject;


@WebServlet("/SelectPostBySubjectServlet")
public class SelectPostBySubjectServlet extends HttpServlet {
	private SubjectService subjectService;
	private PostService postService; 
	private final int ROW_PER_PAGE = 5;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		
		//view�� ���� ������
		this.subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		int currentPage =1;
		if(request.getParameter("currentPage") !=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		String subjectName = request.getParameter("subjectName");
		System.out.println(subjectName+"<--------subjectName");
		
		//����Ʈ ���� �� �������� ���
		postService = new PostService();
		
		Map<String, Object> map2 = postService.getselectPostBySubject(subjectName, currentPage, ROW_PER_PAGE); //���Ӻ� �������۾�
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("postBySubject", map2.get("postBySubject")); //���Ӻ� ����¡
		request.setAttribute("lastPage", map2.get("lastPage")); //���Ӻ� �Ѱ���
		request.setAttribute("subject", subjectName);
		request.getRequestDispatcher("/WEB-INF/views/selectPostBySubject.jsp").forward(request, response);
		System.out.println("SelectPostBySubjectServlet doGet() �ǹ�");
}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
