<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입</h1>

	<form action="signUp.do" method="post" id="signUpForm">
		<table>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" required></td>
			</tr>
			<tr>
				<th>ID</th>
				<td><input type="text" name="userId" required></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pwd" id="pwdInput" required></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" id="pwdCheckInput" required></td>
			</tr>
			<tr>
				<th>부서</th>
				<td><select name="deptCd">
						<option value="01">개발부</option>
						<option value="02">영업부</option>
						<option value="03">총무부</option>
				</select></td>
			</tr>
			<tr>
				<th>직책</th>
				<td><input type="text" name="job"></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email"></td>
			</tr>
		</table>
		<div>
			<button type="button" id="okBtn">등록</button>
			<button type="reset">초기화</button>
			<button type="button" id="backBtn">이전</button>
		</div>
	</form>

	<script>
		window.onload = function() {
			var okBtn = document.getElementById('okBtn');

			okBtn.onclick = function() {
				var pwd = document.getElementById('pwdInput').value;
				var pwdCheck = document.getElementById('pwdCheckInput').value;

				if (pwd == pwdCheck) {
					document.getElementById('signUpForm').submit();
				} else {
					alert("비밀번호 입력을 확인해주세요.");
				}
			}
		}
	</script>
</body>
</html>