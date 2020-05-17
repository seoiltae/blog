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
		        <h1>글쓰기</h1>
		        <form method="post" action="${pageContext.request.contextPath}/AddPostServlet">
					<select name="subjectName">
						<c:forEach var="s" items="${list}">
							<option value="${s.subjectName }">${s.subjectName}</option>
						</c:forEach>
					</select>
					<table border="1">	
					 <c:if test="${loginMember != null}">
						<tr>
							<td>
							id:
							<input type="text" name="memberId" value="${loginMember}" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td>
							title:
							<input type="text" name="postTitle">
							</td>
						</tr>
						<tr>
							<td>
							content:
							<input type="text" name="postContent">
							</td>
						</tr>
						<tr>
							<td>
							<button type="submit">글쓰기</button>
							</td>
						</tr>
					</c:if>
					</table>
				</form>
			</div>	
		</body>
</html>