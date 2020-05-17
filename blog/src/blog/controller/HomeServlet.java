package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private SubjectService subjectService;
	
	private Member member;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//view에 옆에 나라배너
		
		
		this.subjectService = new SubjectService();
		
		
		List<Subject> subjectList = subjectService.getSubjectListAll()
				;
		request.setAttribute("subjectList", subjectList);
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

