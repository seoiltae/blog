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
		    	<div>
		    		MEMU
		    	</div>
		    	<ul>
		    		<c:forEach var="subject" items="${subjectList}">
		    		<li>
		    			<a href="${pageContext.request.contextPath}/Servlet">${subject.subjectName}</a>
		    		</li>
		    		</c:forEach>
		    	</ul>
		    </div>
		    <div id="section">
		        <h1>회원탈퇴</h1>
		        <div>
		           <form method="post" action="${pageContext.request.contextPath }/DeleteMemberServlet">
		           		<div>
		           			${loginMember.memberId }
		           		</div>
		           		<div>
		           			비밀번호 :
		           			<input type="password" name="memberPw">
		           		</div>
		           		<button type="submit">회원탈퇴</button>
		           </form>
		        </div>
		        <!-- 메인 내용.... -->
		    </div>
		</body>
</html>