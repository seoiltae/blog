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


@WebServlet("/DeleteMemberServlet")
public class DeleteMemberServlet extends HttpServlet {
	private MemberService memberService;
	private SubjectService subjectService;
	//Ż����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	if(session.getAttribute("loginMember") == null) {
		response.sendRedirect(request.getContextPath()+"/LoginServlet");
	} else {
	//view�� ���� ������
	this.subjectService = new SubjectService();
	List<Subject> subjectList = subjectService.getSubjectListAll();	
	
	request.setAttribute("subjectList", subjectList);
	request.getRequestDispatcher("/WEB-INF/views/deleteMember.jsp").forward(request, response);
	System.out.println("DeleteMemberServlet doGet() Ȯ��");
	}
	}
	
	//Ż�� �׼�
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// member ���̺���� + memberid ���̺� �Է�
		memberService = new MemberService();
		
		Member member = (Member) (request.getSession().getAttribute("loginMember"));
		member.setMemberPw(request.getParameter("memberPw"));
		memberService.removeMember(member);
		System.out.println("DeleteMemberServlet doPost() ����Ȯ��");
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/HomeServlet");
	}

}
