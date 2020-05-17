<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>side</title>
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
		    	<div>
		    		MEMU
		    	</div>
		    	<ul>
		    		 <!-- 로그아웃 상태 -->
		            <c:if test="${loginMember == null}">
		            	<li>
		            		<a href="${pageContext.request.contextPath}/LoginServlet">로그인</a>
		            	</li>
		            	<li>
		            		<a href="${pageContext.request.contextPath}/AddMemberServlet">회원가입</a>
		            	</li>
		            </c:if>
		            <!-- 로그인상태 -->
		            <c:if test="${loginMember != null}">
		            	<li>
		            		<a href="${pageContext.request.contextPath}/LogoutServlet">로그아웃</a>
		            	</li>
		            </c:if>
			    		<li>
			    			<a href="${pageContext.request.contextPath}/HomeServlet">홈</a>
			    		</li>
			    		<li>
			    			<a href="${pageContext.request.contextPath}/AddPostServlet">글쓰기</a>
			    		</li>
		    		<c:forEach var="subject" items="${subjectList}">
			    		<li>
			    			<a href="${pageContext.request.contextPath}/SelectPostBySubjectServlet?subjectName=${subject.subjectName}">${subject.subjectName}</a>
			    		</li>
		    		</c:forEach>
		    	</ul>
		    </div>
		</body>
</html>