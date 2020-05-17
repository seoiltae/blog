package blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.LikeyService;
import blog.vo.Likey;
import blog.vo.Member;

/**
 * Servlet implementation class AddLikeyServlet
 */
@WebServlet("/AddLikeyServlet")
public class AddLikeyServlet extends HttpServlet {
	private LikeyService likeyService; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")==null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		// 좋아요누르기!
		int postNo = Integer.parseInt(request.getParameter("postNo"));		
		String memberId = request.getParameter("memberId");
		/*int totalCk = 0;
		if(request.getParameter("totalCk") !=null) {
			totalCk = Integer.parseInt(request.getParameter("totalCk"));
		}*/
		Likey likey = new Likey();
		likey.setPostNo(postNo);
		likey.setMemberId(memberId);
		likeyService = new LikeyService();
		// 라이크 값을 받아오는데 트루이면 좋아요를 실행한다
		String like = request.getParameter("like");
		if(like.equals("true")) {
			likeyService.addLikey(likey);
		} else if(like.equals("false")) {
			likeyService.addLikeyno(likey);
		}
		
		response.sendRedirect(request.getContextPath()+"/SelectPostOneServlet?postNo="+postNo);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
