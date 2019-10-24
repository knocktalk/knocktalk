<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta charset="UTF-8">
		<title>비밀번호 변경</title>
	</head>
	<body>
		<form action="my_password_success.do" method="post"> 
			<c:choose>
				<c:when test="${user_id != null}">
					비밀번호 : <input type="password" name="user_password" value="${my_info.user_password }" required><br>
					비밀번호 확인 : <input type="password" name="user_rePassword" required><br>
					<button type="submit">수정완료</button>
				</c:when>
			</c:choose> 
		</form>
	</body>
</html>