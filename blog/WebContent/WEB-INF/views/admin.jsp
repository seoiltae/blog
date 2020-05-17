<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>admin</title>
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
		    
		        <h1>관리자메뉴</h1>
		        <div>
		          <ol>
		          	<li>
		          		<a href="${pageContext.request.contextPath }/SelectMemberListServlet">멤버관리</a>
		          	</li>
		          	<li>
		          		<a href="${pageContext.request.contextPath }/SelectPostListServlet">포스팅관리</a>
		          	</li>
		          </ol>
		        </div>
		        <div>
		           
		        </div>
		        <!-- 메인 내용.... -->
		    </div>
		</body>
</html>