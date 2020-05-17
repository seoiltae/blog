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
		    	<div>
		    		MEMU
		    	</div>
		    	<ul>
		    		<li>
		    			<a href="${pageContext.request.contextPath}/HomeServlet">홈</a>
		    		</li>
		    		<c:forEach var="subject" items="${subjectList}">
		    		<li>
		    			<a href="${pageContext.request.contextPath}/Servlet">${subject.subjectName}</a>
		    		</li>
		    		</c:forEach>
		    	</ul>
		    </div>
		    <div id="section">
		        <h1>회원목록 ${lastPage }</h1>
		        <div>
		            <table border="1">
		            <c:if test="${loginMember != null && loginMember.memberLevel < 10}">
		            	<tr>
		            		<th>member_id</th>
		            		<th>member_pw</th>
		            		<th>member_level</th>
		            		<th>member_date</th>
		            		<th>회원레벨수정</th>
		            	</tr>
		            	<c:forEach var="memberList" items="${memberList}">
		            	<tr>
		            		<td>${memberList.memberId}</td>
		            		<td>${memberList.memberPw}</td>
		            		<td>${memberList.memberLevel}</td>
		            		<td>${memberList.memberDate}</td>
		            		<td><a href="">회원레벨수정</a></td>
		            	</tr>
		            	</c:forEach>
		            </c:if>
		            </table>
		            <div>
		            	<a href="${pageContext.request.contextPath}/SelectMemberListServlet?currentPage=${1}">처음페이지</a>
		            <c:if test="${currentPage>1}">
		            	<a href="${pageContext.request.contextPath}/SelectMemberListServlet?currentPage=${currentPage-1}">이전</a>
		            </c:if>
		            	-${currentPage}-
		            <c:if test="${currentPage<lastPage}">
		            	<a href="${pageContext.request.contextPath}/SelectMemberListServlet?currentPage=${currentPage+1}">다음</a>
		            </c:if>
		            	<a href="${pageContext.request.contextPath}/SelectMemberListServlet?currentPage=${lastPage}">마지막페이지</a>
		            </div>		 
		        </div>
		        <!-- 메인 내용.... -->
		    </div>
		</body>
</html>