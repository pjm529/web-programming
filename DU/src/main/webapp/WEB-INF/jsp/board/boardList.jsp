<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>

	<header>
		<jsp:include page="/WEB-INF/jsp/layout/header.jsp"></jsp:include>
	</header>
	<input type="hidden" id="listSize" value="<c:out value='${pagination.listSize}' />">
	<div id="filterDiv">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" id="searchTitle" value="<c:out value='${title}' />"></td>
				<td><button type="button" id="searchBtn"
						class="btn btn-secondary btn-sm">검색</button>
			</tr>
		</table>
		<button type="button" class="btn btn-primary btn-sm" onclick="window.location.href='boardWritePage.do'">게시글 등록</button>
	</div>

	<table class="table table-hover" id="dataList" style="text-align: center;">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록날짜</th>
				<th>수정날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boardList}" var="item" varStatus="status">
				<tr ondblclick="trDblClick('${item.idx}');">
					<td><c:out value="${status.count + pagination.startList}" /></td>
					<td><c:out value="${item.title}" /></td>
					<td><c:out value="${item.writerName}" /></td>
					<td style="width: 230px;"><c:out value="${item.registDate}" /></td>
					<td style="width: 230px;"><c:out value="${item.modifyDate}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div id="paginationBox" style="display: flex; justify-content: center; align-items: center;">
		<ul class="pagination">
			<c:if test="${pagination.prev}">
				<li class="page-item"><a class="page-link" href="#"
					onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">Previous</a>
				</li>
			</c:if>

			<c:forEach begin="${pagination.startPage}"
				end="${pagination.endPage}" var="idx">
				<li
					class="page-item <c:out value="${pagination.page == idx ? 'active' : ''}"/> "><a
					class="page-link" href="#"
					onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}')">
						${idx} </a></li>
			</c:forEach>

			<c:if test="${pagination.next}">
				<li class="page-item"><a class="page-link" href="#"
					onClick="fn_next('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">Next</a>
				</li>
			</c:if>
		</ul>
	</div>

	<script>

	window.onload = function() {
		
		var listSize = document.getElementById("listSize");
		var searchTitle = document.getElementById("searchTitle");
		var searchBtn = document.getElementById("searchBtn");
		
		searchTitle.addEventListener("keydown", function(event) {
			
			// 엔터가 눌러졌을 떄
			if(event.keyCode === 13) {
				searchBtn.click();
			}
		});
		
		searchBtn.onclick = function() {
			
			// 방법 1
			/* var table = document.getElementById("dataList");
			var tbody = table.getElementsByTagName("tbody")[0];
			var tr = tbody.getElementsByTagName("tr"); */
			
			// 방법 2
			/* var table = document.querySelector("#dataList");
			var tbody = table.querySelector("tbody");
			var tr = tbody.querySelectorAll("tr"); */
			
			// 방법 3
// 			var tr = document.querySelectorAll("#dataList tbody tr")

			// 방법1 
			/* for(var i = 0; i < tr.length; i++) {
				console.log(tr[i].getElementsByTagName("td")[1].innerHTML);
			} */
			
			// 방법2
// 			for (var item of tr) {
// 				var title = item.getElementsByTagName("td")[1].innerHTML;
				
// 				if (title.includes(searchTitle.value)) {
// 					item.style.display = "";
// 				} else {
// 					item.style.display = "none";
// 				}
// 			}
			
			var url = "boardListPage.do";
			url = url + "?title=" + searchTitle.value;
			url = url + "&listSize=" + listSize.value;
			location.href = url;
 		}
		
 	}
	
	// 이전 버튼 이벤트
	function fn_prev(page, range, rangeSize) {
		var page = ((range - 2) * rangeSize) + 1;
		var range = range - 1;
		
		var url = "boardListPage.do";
		
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		url = url + "&listSize=" + listSize.value;
		url = url + "&title=" + searchTitle.value;
		
		location.href = url;
	}
	
	// 페이지 이동
	function fn_pagination(page, range, rangeSize){
		var url = "boardListPage.do";
		
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		url = url + "&listSize=" + listSize.value;
		url = url + "&title=" + searchTitle.value;
		
		location.href = url;
	}
	
	// 다음 버튼 이벤트
	function fn_next(page, range, rangeSize) {
		var page = parseInt((range * rangeSize)) + 1;
		var range = parseInt(range) + 1;
		
		var url = "boardListPage.do";
		
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		url = url + "&listSize=" + listSize.value;
		url = url + "&title=" + searchTitle.value;
		
		location.href = url;
	}
	
	function trDblClick(idx) {
		
		/* var url = "boardInfoPage.do"
		url = url + "?idx=" + idx; */
		
		var url = "boardInfoPage/" + idx + ".do";
		
		location.href = url;
	}
</script>
</body>
</html>