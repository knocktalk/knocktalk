<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta charset="UTF-8">
		<title>가입 절차</title>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$("#search_address_button").postcodifyPopUp(); // 주소 검색
			});
		</script>
		<script src="resources/js/controller.js"></script>
		<script src="resources/js/register.js"></script>
		
		<style>
			input[type="number"]::-webkit-outer-spin-button,
			input[type="number"]::-webkit-inner-spin-button {
			    -webkit-appearance: none;
			    margin: 0;
			}
		</style>
	</head>
	<body>
		<form name="registerForm">
			<p>${message}</p>
			* 아이디 : <input type="text" name="user_id" onblur="overlapCheck()" autocomplete="off" required><br>
			<p id="idMessage" onchange="idCheck()"></p>
			* 비밀번호 : <input type="password" name="user_password" autocomplete="off" required><br>
			<p id="passwordMessage"></p>
			* 비밀번호 확인 : <input type="password" name="user_rePassword" onblur="passwordCheck()" autocomplete="off" required><br>
			<p id="rePasswordMessage"></p>
			* 이름 : <input type="text" name="user_name" required><br>
			<p id="nameMessage"></p>
			* 주민등록번호 : <input type="number" name="user_birth" autocomplete="off" required> - <input type="number" name="user_gender" required>******<br>
			<p id="birthMessage"></p>
			* 휴대전화 : <select name="user_phonenumber1">
				<option value="010">010</option>
				<option value="010">011</option>
				<option value="010">016</option>
				<option value="010">017</option>
				<option value="010">018</option>
				<option value="010">019</option>
			</select>-<input type="number" name="user_phonenumber2" required>-<input type="number" name="user_phonenumber3" required><br>
			* 주소 : <input type="text" name="user_addr1" class="postcodify_postcode" readonly> <button type="button" id="search_address_button">검색</button><br>
			<input type="text" name="user_addr2" class="postcodify_address" readonly>
			<input type="text" name="user_addr3" class="postcodify_extra_info" readonly>
			<input type="text" name="user_addr4" required><br>
			<button id="registerButton" onclick="account(this.form)" disabled>다음</button>
			
			<input type="hidden" name="user_nationality" value="82">
			<input type="hidden" name="user_email" value="${user_email}">
		</form>
	</body>
</html>

<!-- * 국적 : <input type="text" name="user_nationality" required> -->
<!-- * 별명 : <input type="text" name="user_nickname" required> -->
<!-- * 이메일 : <input type="email" name="user_email" required><br> -->