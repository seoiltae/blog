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

import blog.service.MemberService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;


@WebServlet("/SelectMemberListServlet")
public class SelectMemberListServlet extends HttpServlet {
	private SubjectService subjectService;
	private MemberService memberService;
	// ���������� ������ ������ ���񽺿��� ������!
	private final int ROW_PER_PAGE = 5;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember"); // ���ǰ��� ���Ÿ������ ��ȯ���� loginMember�� �ִ´�
		//�α��� �� �������̸� ������ ȭ�� �ƴҰ�� Ȩâ!
		if(loginMember == null || loginMember.getMemberLevel() > 10) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return; // �������� �̰ɷ� �ݰڴ�
		}
		//����������
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		//view�� ���� ������
		this.subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);

		//������ �������Ʈ
		this.memberService = new MemberService();
		Map<String, Object> map = memberService.getMemberList(currentPage, ROW_PER_PAGE);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("memberList", map.get("list"));
		request.setAttribute("lastPage", map.get("lastPage"));
		request.getRequestDispatcher("/WEB-INF/views/selectMemberList.jsp").forward(request, response);
		
		System.out.println("SelectMemberListServlet doGet �ǹ�!");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	
	}

}
