<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/layout/header.jsp"></jsp:include>
	</header>

	<form action="${pageContext.request.contextPath}/boardModify.do" method="post">
		<table class="table table-light" style="width: 50%;">
			<tr>
				<th>제목</th>
				<td style="width: 80%;"><input type="text" style="width:100%;" name="title" value="${board.title}" required></td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td style="width: 80%;">
					<textarea name="content" style="width:100%; height: 100px;" required><c:out value="${board.content}" />
					</textarea>
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><c:out value="${board.writerName}"/></td>
			</tr>
		</table>
		<input type="hidden" name="idx" value="${board.idx}">
		<button type="button" class="btn btn-secondary btn-sm" onclick="history.back(); return false;">이전</button>
		<button type="submit" class="btn btn-primary btn-sm">수정</button>
	</form>
</body>
</html>