<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 정보</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>부서코드</th>
				<th>부서명</th>
				<th>추가기능</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${dept}" var="item" >
				<tr>
					<td><c:out value="${item.deptCd}" /></td>
					<td><c:out value="${item.deptNm}" /></td>
					<td><button type="button" onclick="window.location.href='deptDelete.do?deptCd=${item.deptCd}'">삭제</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<button type="button" onclick="window.location.href='deptInsertPage.do'">등록</button>
	</div>
</body>
</html>