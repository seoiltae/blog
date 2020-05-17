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
	//Å»ÅðÆû
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	if(session.getAttribute("loginMember") == null) {
		response.sendRedirect(request.getContextPath()+"/LoginServlet");
	} else {
	//view¿¡ ¿·¿¡ ³ª¶ó¹è³Ê
	this.subjectService = new SubjectService();
	List<Subject> subjectList = subjectService.getSubjectListAll();	
	
	request.setAttribute("subjectList", subjectList);
	request.getRequestDispatcher("/WEB-INF/views/deleteMember.jsp").forward(request, response);
	System.out.println("DeleteMemberServlet doGet() È®ÀÎ");
	}
	}
	
	//Å»Åð ¾×¼Ç
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// member Å×ÀÌºí»èÁ¦ + memberid Å×ÀÌºí ÀÔ·Â
		memberService = new MemberService();
		
		Member member = (Member) (request.getSession().getAttribute("loginMember"));
		member.setMemberPw(request.getParameter("memberPw"));
		memberService.removeMember(member);
		System.out.println("DeleteMemberServlet doPost() ¿¡·¯È®ÀÎ");
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/HomeServlet");
	}

}
