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
		        <h1>BLOG</h1>
		        <div>
		            <!-- 로그인상태 -->
		            <c:if test="${loginMember != null}">
		            	${loginMember.memberId} 레벨 ${loginMember.memberLevel}반갑습니다
		            	<a href="${pageContext.request.contextPath}/SelectMemberServlet">${loginMember.memberId}님 : 회원정보</a>
		            </c:if>
		        </div>
		        <div>
		            <c:if test="${loginMember != null && loginMember.memberLevel < 10}">
		    			<a href="${pageContext.request.contextPath}/AdminServlet">관리자 페이지</a>
		    		</c:if>
		        </div>
		        <!-- 메인 내용.... -->
		    </div>
		</body>
</html>