<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta charset="UTF-8">
		<title>이메일 변경</title>
	</head>
	<body>
		<form action="my_email_edit_success.do" method="post">
			<c:choose>
				<c:when test="${user_id != null}">
					이메일 변경 > <input type="email" name="user_email" value="${my_info.user_email}"required><br>
					<button type="submit">완료</button>
				</c:when>
			</c:choose> 
		</form>
	</body>
</html>