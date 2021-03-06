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
		        <h1>회원가입</h1>
		        <div>
					<form method="post" action="${pageContext.request.contextPath }/AddMemberServlet">
						<table	border="1">
							<tr>
								<td>ID</td>
								<td>
									<input type="text" name="memberId">
								</td>
								<td>PW</td>
								<td>
									<input type="password" name="memberPw">
								</td>
								<td><button type="submit">회원가입</button></td>
							</tr>
						</table>
					</form>
		        </div>
		        <!-- 메인 내용.... -->
		    </div>
		</body>
</html>