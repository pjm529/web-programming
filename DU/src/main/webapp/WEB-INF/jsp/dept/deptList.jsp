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
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${dept}" var="item" >
				<tr>
					<td><c:out value="${item.deptCd}" /></td>
					<td><c:out value="${item.deptNm}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>