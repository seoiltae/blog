package blog.controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/AddMemberServlet")
public class AddMemberServlet extends HttpServlet {
	private MemberService memberService;
	private SubjectService subjectService;
	
	//������ foWord
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//view�� ���� ������
		this.subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		//�α����� ���� �������� Ȩ���� �̵����� �α׾ƿ��ؾ� �Ѵ�
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		} 
		request.getRequestDispatcher("/WEB-INF/views/addMember.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		memberService = new MemberService(); // DI
		boolean flag = memberService.addMember(member);
		if(flag) {
			// ����
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
		} else {
			// ����
			response.sendRedirect(request.getContextPath()+"/AddMemberServlet");
		}
	}
}
