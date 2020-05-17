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
	//로그인 유지 및 로그인유지일 때 로그인창으로 재로그인 방지하기위함
	HttpSession session = request.getSession();
	if(session.getAttribute("loginMember")!=null) {
	response.sendRedirect(request.getContextPath()+"/HomeServlet");
	return;
	}
	
	// 왼쪽 배너! 서비스 호출
	this.subjectService = new SubjectService();
	List<Subject> subjectList = subjectService.getSubjectListAll();
	
	request.setAttribute("subjectList", subjectList);
	System.out.println(subjectList.size()+"  <--LoginServlet doGdet subjectlist.szie()");
	request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response); //request,response를 ()보낸다
	
	}
	
	//login action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// view에 입력한 값을 리퀘스트에 넣어서 받아온다
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberId+"	<--memberId LoginServlet doPost()");
		System.out.println(memberPw+"	<--memberPw LoginServlet doPost()");
		
		//뷰에서 입력한 값을 member에 넣기 위함
		Member member = new Member(); 
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// member에 값과 모델에 값을 비교하기 위함
		this.memberService = new MemberService();
		Member returnMember = memberService.removerMember(memberId);
		
		if(returnMember == null) {//비교해서 널이라면
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
		} else {
			HttpSession session = request.getSession(); 
			session.setAttribute("loginMember", returnMember); //"네임설정" , 값을 세션에 저장
			System.out.println("로그인성공");
			response.sendRedirect(request.getContextPath()+"/HomeServlet"); //로그인 성공해서 홈화면
		}
	}

}
