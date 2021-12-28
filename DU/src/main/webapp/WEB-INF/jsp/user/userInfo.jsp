<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>내 정보</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th colspan="2">내 정보</th>
			</tr>		
		</thead>
		<tbody>
			<tr>
				<th>아이디</th>
				<td><input type="text" value="" /></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" value="" /></td>
			</tr>
			<tr>
				<th>비밀번호확인</th>
				<td><input type="password" value="" /></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" value="" /></td>
			</tr>
			<tr>
				<th>부서</th>
				<td>
					<select>
						<option value="01">개발부</option>
						<option value="02">영업부</option>
						<option value="03">총무부</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>직급</th>
				<td><input type="text" value="" /></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td><input type="text" value="" /></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" value="" /></td>
			</tr>
		</tbody>
	</table>
</body>
</html>