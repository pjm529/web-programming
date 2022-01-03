<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
						<button type="submit" class="btn btn-success btn-sm">등록</button>
					</td>
				</tr>
				
				<c:forEach items="${replyList}" var="item" varStatus="status">
					<tr>
						<th style="width: 10%;"><c:out value="${item.writerName}" /></th>
						<td data-idx="${item.idx}">
							<span><c:out value="${item.content}" /></span> 
							
							<c:if test="${item.writerId == USER.userId }">
								<button type="button" style="float: right; margin-left: 5px;" 
									class="btn btn-primary btn-sm replyModifyBtn" >수정</button>
								<button type="button" style="float: right;" class="btn btn-secondary btn-sm" 
									onclick="deleteReply('${item.idx}')">삭제</button>
							</c:if>
							
							
							<c:choose>
								<c:when test="${item.modifyDate != null}">
									<fmt:parseDate value="${item.modifyDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="date" />
									<br>(<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/> 수정됨)
								</c:when>
								
								<c:otherwise>
									<fmt:parseDate value="${item.registDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="date" />
									<br>(<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>)
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" name="boardIdx" value="${board.idx}">
		</form>
	</div>
	
	<form id="hiddenForm" style="display: none;" action="${pageContext.request.contextPath}/replyModify.do" method="post">
		<input type="text" name="content" style="width: 80%; margin-rightL 6px;">
		<input type="hidden" name="idx">
		<input type="hidden" name="boardIdx" value="${board.idx}">
		<button type="submit" class="btn btn-primary btn-sm">확인</button>
	</form>
<script>
	
	window.onload = function() {
		
		var deleteBtn = document.getElementById("deleteBtn");
		
		deleteBtn.onclick = function () {
			if(confirm("게시글을 삭제하시겠습니까?")) {
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
		
		var replyModifyBtns = document.querySelectorAll(".replyModifyBtn");
		
		replyModifyBtns.forEach(el => el.addEventListener("click", event => {
			
			var td = el.parentNode;
			
			console.log(el);
			console.log(td);
			var content = td.getElementsByTagName("span")[0].innerHTML;
			console.log(content);
			
			td.innerHTML = "";
			
			td.append(makeReplyUpdateForm(td.getAttribute("data-idx"), content));
		}));
	}
	
	function deleteReply(idx) {
		if(confirm("댓글을 삭제하시겠습니까?")) {
			var path = "${pageContext.request.contextPath}/replyDelete.do"
			var params = {
					"idx": idx,
					"boardIdx": "${board.idx}"
			};
			post(path, params);
		} else {
			return;
		}
	}
	
	function makeReplyUpdateForm(idx, content) {

		var form = document.getElementById("hiddenForm").cloneNode(true);
		form.style.display = "";
		
		var contentInput = form.getElementsByTagName("input")[0];
		contentInput.value = content;
		
		var idxInput = form.getElementsByTagName("input")[1];
		idxInput.value = idx;
		
		
		return form;
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