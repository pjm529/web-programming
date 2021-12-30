<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js"></script>
</head>
<body>
<div class="logoDiv">
	<img alt="대구대학교 로고" src="${pageContext.request.contextPath}/images/symbol_1.png" 
		onclick="window.location.href='${pageContext.request.contextPath}/mainPage.do'">
</div>

<ul class="infoUl">
	<li><a href="deptPage.do">부서 정보</a></li>
</ul>

<div class="userInfoDiv">
	<c:out value="${USER.name }"></c:out>님 반갑습니다.
	<button type="button" class="btn btn-primary btn-sm" style="right" onclick="window.location.href='${pageContext.request.contextPath}/logout.do'">로그아웃</button>
	<button type="button" class="btn btn-outline-primary btn-sm" style="right" onclick="window.location.href='${pageContext.request.contextPath}/userInfoConfirmPage.do'">내정보</button>
</div>
</body>
</html>