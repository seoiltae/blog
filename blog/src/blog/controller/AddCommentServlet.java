package blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.CommentService;
import blog.vo.Comment;

@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	private CommentService commentService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")==null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+"<----게시글 번호");
		String memberId = request.getParameter("memberId");
		String commentContent = request.getParameter("commentContent");
		Comment comment = new Comment();
		comment.setPostNo(postNo);
		comment.setMemberId(memberId);
		comment.setCommentContent(commentContent);
		commentService = new CommentService();
		commentService.addComment(comment);
		response.sendRedirect(request.getContextPath()+"/SelectPostOneServlet?postNo="+postNo);
		
	}

}
