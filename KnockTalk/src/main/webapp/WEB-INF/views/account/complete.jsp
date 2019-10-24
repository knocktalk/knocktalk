<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta charset="UTF-8">
		<title>가입 완료</title>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="resources/js/controller.js"></script>
	</head>
	<body>
		<h1>${message}</h1>
		<h2>노크톡 회원이 되신 것을 환영합니다!</h2>
		<button type="button" onclick="location.href='/knocktalk/'">메인으로</button>
	</body>
</html>