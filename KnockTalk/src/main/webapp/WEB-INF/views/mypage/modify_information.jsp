<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta charset="UTF-8">
		<title>내 정보 수정</title>
	</head>
	<body>
		<form action="my_information_edit_success.do" method="post"> 
			<c:choose>
				<c:when test="${user_id != null}">
					이름 : ${my_info.user_name}<br>
					별명 : <input type="text" name="user_nickname" value="${my_info.user_nickname}" required><br>
					휴대전화 : <select name="user_phonenumber1">
					<option value="010">010</option>
					<option value="010">011</option>
					<option value="010">016</option>
					<option value="010">017</option>
					<option value="010">018</option>
					<option value="010">019</option>
					</select>-<input type="number" name="user_phonenumber2" required>-<input type="number" name="user_phonenumber3" required><br>
					<button type="submit">수정완료</button>
				</c:when>
			</c:choose> 
		</form>
	</body>
</html>