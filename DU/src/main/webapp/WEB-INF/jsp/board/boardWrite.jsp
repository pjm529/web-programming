<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/layout/header.jsp"></jsp:include>
	</header>
	<form action="boardWrite.do" method="post">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" required></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" style="width: 100%; height: 100px;" required></textarea></td>
			</tr>
		</table>
		
		<button type="submit" class="btn btn-secondary btn-sm">등록</button>
	</form>
</body>
</html>