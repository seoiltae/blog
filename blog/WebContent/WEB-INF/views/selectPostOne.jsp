<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<meta charset="UTF-8">
<style>
    body {
        padding: 0;
        margin: 0;
        width: 100%;
        height: 100%;
        overflow: hidden;
        background-position: 0 0;
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: cover;
        position: relative;
        overflow-y: auto;
    }
   span {
		   width: 10px;
		   height: 100px;
		   line-height: 300px;
		   display: block;
	}
    #aside {
        width: 200px;
        height: 3000px;
        position: fixed;
        background: orange;
        overflow: hidden;
        float: left;
    }
    
    #section {
        margin-left: 300px;
        background: white;
    }
</style>
</head>
		<body>
		    <div id="aside">
		    	<!-- subject(나라이름) list 출력 -->
		    	<jsp:include page="/WEB-INF/views/inc/side.jsp"></jsp:include>
		    </div>
		    <div id="section">
		        <h1>상세보기창</h1>
		        <div>
		        	<a><center>현재로그인 ${loginMember.memberId}</a></center>
		        </div>
		        <div>
		        	<c:if test="${loginMember ==null}">
		        		<a href="${pageContext.request.contextPath}/LoginServlet?">좋아요</a>${likeyCount}
		        		<a href="${pageContext.request.contextPath}/LoginServlet?">싫어요</a>${likeynoCount}
		        	</c:if>
		        	<c:if test="${loginMember !=null}">
		        	<a href="${pageContext.request.contextPath}/AddLikeyServlet?postNo=${post.postNo}&memberId=${loginMember.memberId}&like=true">좋아요</a>
		        	${likeyCount}
		        	<a href="${pageContext.request.contextPath}/AddLikeyServlet?postNo=${post.postNo}&memberId=${loginMember.memberId}&like=false">싫어요</a>
		        	${likeynoCount}
		        	</c:if>
		        	
		        </div>
				<table border="1">
					<tr>
						<th>post_no</th>
						<th>member_id</th>
						<th>subject_name</th>
						<th>post_title</th>
						<th>post_content</th>
						<th>post_date</th>
					</tr>
						<tr>
							<td>${post.postNo}</td>
							<td>${post.memberId}</td>
							<td>${post.subjectName}</td>
							<td>${post.postTitle}</td>
							<td>${post.postContent}</td>
							<td>${post.postDate}</td>						
						</tr>
				</table>
				<a href="${pageContext.request.contextPath }/SelectPostBySubjectServlet?subjectName=${post.subjectName}">뒤로가기</a>
		    </div>
		    <div>
		    	수정 or 삭제
		    </div>
		    <div><center>
		    	<form method="post" action="${pageContext.request.contextPath}/AddCommentServlet">
		    		<input type="hidden" name="postNo" value="${post.postNo}">
		    		<input type="hidden" name="memberId" value="${loginMember.memberId}">
		    		<textarea rows="2" cols="100" name="commentContent"></textarea>
		    		<button type="submit">댓글입력</button>
		    	</form>
		    	<div>
		    	 <h2>댓글 리스트 </h2>
		    	 	<table border="1">
		    	 		<c:forEach var="p" items="${postbyCommentList}">
		    	 		<tr>	
		    	 			<td>${p.postNo}</td>
		    	 			<td>${p.memberId}</td>
		    	 			<td>${p.commentContent}</td>
		    	 			<td>${p.commentDate}</td>
		    	 		</tr>
		    	 		</c:forEach>
		    	 	</table>
		    	 	<div>
					<a href="${pageContext.request.contextPath}/SelectPostOneServlet?currentPage=${1}&postNo=${post.postNo}">처음페이지</a>
				<c:if test="${currentPage>1}">
					<a href="${pageContext.request.contextPath}/SelectPostOneServlet?currentPage=${currentPage-1}&postNo=${post.postNo}">이전</a>
				</c:if>
					-${currentPage}-
				<c:if test="${currentPage<lastPage}">
					<a href="${pageContext.request.contextPath}/SelectPostOneServlet?currentPage=${currentPage+1}&postNo=${post.postNo}">다음</a>
				</c:if>
					<a href="${pageContext.request.contextPath}/SelectPostOneServlet?currentPage=${lastPage}&postNo=${post.postNo}">마지막페이지</a>	
				</div>
		    	</div>
		    	
		    	
		    </div><center>
		    <div>
		    	
		    </div>
		</body>
</html>