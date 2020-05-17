<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>PostList</title>
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
		        <h1>포스팅목록 총페이지${lastPage}</h1>
		        <div>
		            <table border="1">
		            	<!-- 관리자일경우에만 쓸 수 있게 하기 위해 조건을 달아준다 -->
		           		<c:if test="${loginMember !=null && loginMember.memberLevel <10}"> 
		           			<tr>
		           				<th>post_no</th>
		           				<th>member_id</th>
		           				<th>subject_name</th>
		           				<th>post_title</th>
		           				<th>post_date</th>
		           			</tr>
		           			<c:forEach var="postList" items="${postList}">
		           				<tr>
		           					<td>${postList.postNo }</td>
		           					<td>${postList.memberId }</td>
		           					<td>${postList.subjectName }</td>
		           					<td><a href="${pageContext.request.contextPath }/UpdatePostServlet">${postList.postTitle}</a></td>
		           					<td>${postList.postDate}</td>
		           				</tr>
		           			</c:forEach>
		           		</c:if>
		            </table>
		            <div>
		            	<a href="${pageContext.request.contextPath}/SelectPostListServlet?currentPage=${1}">처음페이지</a>
		            <c:if test="${currentPage>1}">
		            	<a href="${pageContext.request.contextPath}/SelectPostListServlet?currentPage=${currentPage-1}">이전</a>
		            </c:if>
		            	-${currentPage}-
		            <c:if test="${currentPage<lastPage}">
		            	<a href="${pageContext.request.contextPath}/SelectPostListServlet?currentPage=${currentPage+1}">다음</a>
		            </c:if>
		            	<a href="${pageContext.request.contextPath}/SelectPostListServlet?currentPage=${lastPage}">마지막페이지</a>
		            </div>		 
		        </div>
		        <!-- 메인 내용.... -->
		    </div>
		</body>
</html>