<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta charset="UTF-8">
		<title>프로필 설정</title>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="resources/js/controller.js"></script>
	</head>
	<body>
		<h2>거의 끝났습니다!</h2>
		<img id="profileImage" alt="My Profile Image" src="${pageContext.request.contextPath}/resources/images/defaultProfileImage.jpg" width="300" height="300">
		<form name="settingForm" enctype="multipart/form-data" method="post">
			<input type="file" name="image"><br>
			<input type="text" name="user_nickname">
			
			<input type="hidden" name="user_id" value="${accountVo.user_id}">
			<input type="hidden" name="user_password" value="${accountVo.user_password}">
			<input type="hidden" name="user_name" value="${accountVo.user_name}">
			<input type="hidden" name="user_birth" value="${accountVo.user_birth}">
			<input type="hidden" name="user_gender" value="${accountVo.user_gender}">
			<input type="hidden" name="user_phonenumber" value="${accountVo.user_phonenumber}">
			<input type="hidden" name="user_nationality" value="${accountVo.user_nationality}">
			<input type="hidden" name="user_address" value="${accountVo.user_address}">
			<input type="hidden" name="user_email" value="${accountVo.user_email}">
		</form>
		<button type="button" onclick="settingCheck(true)">완료</button>
		<button type="button" onclick="settingCheck(false)">건너뛰기</button>
	</body>
</html>