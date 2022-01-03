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
		<form id="postForm" method="post" style="display: inline;" onsubmit="return false;">
			<button type="submit" class="btn btn-secondary btn-sm" 
			formaction="${pageContext.request.contextPath}/boardDelete.do" id="deleteBtn">삭제</button>
			<button type="submit" class="btn btn-primary btn-sm"
			formaction="${pageContext.request.contextPath}/boardModifyPage.do" id="modifyBtn">수정</button>
			<input type="hidden" name="idx" value="${board.idx}">
			<input type="hidden" name="attIdx" value="${board.attIdx}">
		</form>
		
	</c:if>
	
	<form id="fileDownload" action="${pageContext.request.contextPath}/download/boardAttFile.do" method="post">
		<input type="hidden" name="boardIdx" value="${board.idx}">
		<input type="hidden" name="idx" value="${board.attIdx}">
	</form>
	
	<div id="replyDiv" style="margin-top: 10px;">
		<form action="${pageContext.request.contextPath}/replyWrite.do" method="post">
			<table class="table table-light" style="width: 50%">
				<tr>
					<th style="width: 10%;">댓글</th>
					<td>
						<input type="text" name="content" style="width: 90%">
						<button type="submit" class="btn btn-success">등록</button>
					</td>
				</tr>
			</table>
			<input type="hidden" name="boardIdx" value="${board.idx}">
		</form>
	</div>
<script>
	
	window.onload = function() {
		
		var deleteBtn = document.getElementById("deleteBtn");
		
		deleteBtn.onclick = function () {
			if(confirm("삭제하시겠습니까?")) {
				alert("게시글이 삭제되었습니다.");
				var postForm = document.querySelector("#postForm");
				postForm.setAttribute("onsubmit", "return true;");
				postForm.submit();
			
			} else {
				return;
			}
		}
		
		
		var modifyBtn = document.getElementById("modifyBtn");
		
		modifyBtn.onclick = function() {
			var postForm = document.querySelector("#postForm");
			postForm.setAttribute("onsubmit", "return true;");
			postForm.submit();
		} 
		
		
		/* deleteBtn.onclick = function() {
			
			if(confirm("삭제하시겠습니까?")) {
				var path = "${pageContext.request.contextPath }/boardDelete.do";
				var params = {
					"idx": "${board.idx}"
				};
				post(path, params);
			} else {
				return;
			}
		} */
		
		/* var modifyBtn = document.getElementById("modifyBtn");
		
		modifyBtn.onclick = function() {
			
			var path = '${pageContext.request.contextPath}/boardModifyPage.do';
			var params = {
				"idx": "${board.idx}"
			};
			post(path, params);
		} */
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