<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/layout/header.jsp"></jsp:include>
	</header>
	
	<table class="table table-light" style="width: 50%;">
		<tr>
			<th>제목</th>
			<td><c:out value="${board.title}"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td style="width:80%; height: 100px;"><c:out value="${board.content}"/></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><c:out value="${board.writerName}"/></td>
		</tr>
		<tr>
			<th>등록날짜</th>
			<td><c:out value="${board.registDate}"/></td>
		</tr>
	</table>
	<button type="button" class="btn btn-secondary btn-sm" onclick="history.back(); return false;">이전</button>
	<button type="button" class="btn btn-secondary btn-sm" id="deleteBtn">삭제</button>
	
<script>
	
	window.onload = function() {
		
		var deleteBtn = document.getElementById("deleteBtn");
		
		deleteBtn.onclick = function() {
			
			if(confirm("삭제하시겠습니까?")) {
				url = "${pageContext.request.contextPath }/boardDelete/${board.idx}.do";
				
				location.href = url;
			} else {
				return;
			}
		}
		
	}
</script>	
</body>
</html>