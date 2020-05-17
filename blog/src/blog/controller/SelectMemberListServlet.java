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
	// 한페이이제 보여줄 갯수를 서비스에서 정했음!
	private final int ROW_PER_PAGE = 5;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember"); // 세션값을 멤버타입으로 변환시켜 loginMember에 넣는다
		//로그인 후 관리자이면 관리자 화면 아닐경우 홈창!
		if(loginMember == null || loginMember.getMemberLevel() > 10) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return; // 이프문을 이걸로 닫겠다
		}
		//현재페이지
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		//view에 옆에 나라배너
		this.subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);

		//관리자 멤버리스트
		this.memberService = new MemberService();
		Map<String, Object> map = memberService.getMemberList(currentPage, ROW_PER_PAGE);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("memberList", map.get("list"));
		request.setAttribute("lastPage", map.get("lastPage"));
		request.getRequestDispatcher("/WEB-INF/views/selectMemberList.jsp").forward(request, response);
		
		System.out.println("SelectMemberListServlet doGet 맨밑!");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	
	}

}
