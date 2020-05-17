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

@WebServlet("/SelectMemberServlet")
public class SelectMemberServlet extends HttpServlet {
	private SubjectService subjectService;
	private MemberService memberService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// �α��� ���� �ʰ� ȸ�������� ������ �ϸ� �α����������� �̵���Ų��!
	HttpSession session = request.getSession();
	if(session.getAttribute("loginMember")==null) {
		response.sendRedirect(request.getContextPath()+"/LoginServlet");
	} else {
		
	// ���� ���!
	this.subjectService = new SubjectService();
	List<Subject> subjectList = subjectService.getSubjectListAll();
	
	String memberId = ((Member) (session.getAttribute("loginMember"))).getMemberId();
	memberService = new MemberService();
	Member member = memberService.removerMember(memberId);
	
	request.setAttribute("subjectList", subjectList);
	request.setAttribute("member", member);
	request.getRequestDispatcher("/WEB-INF/views/selectMember.jsp").forward(request, response);
	System.out.println("SelectMemberServlet doGet �ǹغκ�");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
