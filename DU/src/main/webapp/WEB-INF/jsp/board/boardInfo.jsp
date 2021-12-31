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
			<th>첨부파일</th>
			<td colspan="3">
				<a href="#" onclick="downloadFile(); return false;">${board.attFilename}</a>
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><c:out value="${board.writerName}"/></td>
		</tr>
		<tr>
			<th>등록날짜</th>
			<td><c:out value="${board.registDate}"/></td>
		</tr>
		<tr>
			<th>수정날짜</th>
			<td><c:out value="${board.modifyDate}"/></td>
		</tr>
	</table>
	<button type="button" class="btn btn-secondary btn-sm" onclick="history.back(); return false;">이전</button>
	
	<c:if test="${board.writerId == USER.userId }">
		<button type="button" class="btn btn-secondary btn-sm" id="deleteBtn">삭제</button>
		<button type="button" class="btn btn-primary btn-sm" id="modifyBtn">수정</button>
	</c:if>
	
	<form id="fileDownload" action="${pageContext.request.contextPath}/download/boardAttFile.do" method="post">
		<input type="hidden" name="boardIdx" value="${board.idx}">
		<input type="hidden" name="idx" value="${board.attIdx}">
	</form>
<script>
	
	window.onload = function() {
		
		var deleteBtn = document.getElementById("deleteBtn");
		
		deleteBtn.onclick = function() {
			
			if(confirm("삭제하시겠습니까?")) {
				var path = "${pageContext.request.contextPath }/boardDelete.do";
				var params = {
					"idx": "${board.idx}"
				};
				post(path, params);
			} else {
				return;
			}
		}
		
		var modifyBtn = document.getElementById("modifyBtn");
		
		modifyBtn.onclick = function() {
			
			var path = '${pageContext.request.contextPath}/boardModifyPage.do';
			var params = {
				"idx": "${board.idx}"
			};
			post(path, params);
		}
	}
	
	function post(path, params) {
		const form = document.createElement('form');
		form.method = "post";
		form.action = path;		
		
		for(const key in params) {
			if(params.hasOwnProperty(key)) {
				const hiddenField = document.createElement('input');
				hiddenField.type = "hidden";
				hiddenField.name = key;
				hiddenField.value = params[key];
				
				form.appendChild(hiddenField);
			}
		}
		
		document.body.append(form);
		
		form.submit();
	}
	
	function downloadFile() {
		var inputIdx = document.querySelector("#fileDownload > input[name='idx']");
		
		if(inputIdx.value) {
			document.forms["fileDownload"].submit();
		}
	}
</script>	
</body>
</html>