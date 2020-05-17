package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.dao.MemberDao;
import blog.service.MemberService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private SubjectService subjectService;
	private MemberService memberService;	
	
	//login form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//�α��� ���� �� �α��������� �� �α���â���� ��α��� �����ϱ�����
	HttpSession session = request.getSession();
	if(session.getAttribute("loginMember")!=null) {
	response.sendRedirect(request.getContextPath()+"/HomeServlet");
	return;
	}
	
	// ���� ���! ���� ȣ��
	this.subjectService = new SubjectService();
	List<Subject> subjectList = subjectService.getSubjectListAll();
	
	request.setAttribute("subjectList", subjectList);
	System.out.println(subjectList.size()+"  <--LoginServlet doGdet subjectlist.szie()");
	request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response); //request,response�� ()������
	
	}
	
	//login action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// view�� �Է��� ���� ������Ʈ�� �־ �޾ƿ´�
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberId+"	<--memberId LoginServlet doPost()");
		System.out.println(memberPw+"	<--memberPw LoginServlet doPost()");
		
		//�信�� �Է��� ���� member�� �ֱ� ����
		Member member = new Member(); 
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// member�� ���� �𵨿� ���� ���ϱ� ����
		this.memberService = new MemberService();
		Member returnMember = memberService.removerMember(memberId);
		
		if(returnMember == null) {//���ؼ� ���̶��
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
		} else {
			HttpSession session = request.getSession(); 
			session.setAttribute("loginMember", returnMember); //"���Ӽ���" , ���� ���ǿ� ����
			System.out.println("�α��μ���");
			response.sendRedirect(request.getContextPath()+"/HomeServlet"); //�α��� �����ؼ� Ȩȭ��
		}
	}

}
