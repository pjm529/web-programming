<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 등록</title>
</head>
<body>
	<form action="deptInsert.do" method="post">
		<table>
			<tr>
				<th>부서코드</th>
				<td><input type="text" name="deptCd" required></td>
			</tr>
			<tr>
				<th>부서명</th>
				<td><input type="text" name="deptNm" required></td>
			</tr>
		</table>
		
		<div>
			<button type="button" onclick="window.history.go(-1)">이전</button>
			<button type="submit">등록</button>
		</div>
	</form>
</body>
</html>