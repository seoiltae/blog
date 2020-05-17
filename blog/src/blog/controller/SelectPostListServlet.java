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
			System.out.println("SelectPostListServlet ��������");
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		//view�� ���� ������
		this.subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		//���� ������
		int currentPage = 1;
		if(request.getParameter("currentPage") !=null) { //Ŀ��Ʈ�������� ��Ʈ�� ����ȯ ������! �Ű�����!
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		//����Ʈ ����Ʈ
		postService = new PostService();
		Map<String, Object> map = postService.getPostList(currentPage, ROW_PER_PAGE); //���񽺶� ����!
		request.setAttribute("currentPage", currentPage); // �������� ������ ���� �ѱ�� ����!
		request.setAttribute("postList", map.get("postlist")); //map ��
		request.setAttribute("lastPage", map.get("lastPage")); // map ��
		request.getRequestDispatcher("/WEB-INF/views/selectPostList.jsp").forward(request, response);
		System.out.println("SelectPostListServlet doPost() �ǹ�");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	}

}
