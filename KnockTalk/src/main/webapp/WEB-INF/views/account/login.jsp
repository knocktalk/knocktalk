<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="stylesheet" href="resources/css/main.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="resources/js/my_controller.js"></script>
		
		<title>Login</title>
	</head>
	<body class="subpage">
		<!-- Header -->
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
		
		<!-- Login -->
		<section class="wrapper">
			<div class="inner">
				<header class="align-center">
					<h2>로그인</h2>
					<p id="loginMessage">${loginMessage}</p>
				</header>
				<div class="row">
					<div class="3u 12$(small)">
						<br>
					</div>
					<div class="6u 12$(small)">
						<br>
						<form name="loginForm">
							<div class="12u$">
								<input type="text" name="user_id" placeholder="아이디" required>
							</div>	
							<div class="12u$">
								<input type="password" name="user_password" placeholder="비밀번호" required>
							</div>
							<div class="12u$">
								<br><button type="button" class="button fit" onclick="account(this.form)">Login</button>
							</div>
						</form>
						<hr>
						<footer class="align-center">
							<a href="#" style="margin-left: 10px; margin-right: 10px;">아이디 찾기</a>
							<a href="#" style="margin-left: 10px; margin-right: 10px;">비밀번호 찾기</a>
							<a href="register.page" style="margin-left: 10px; margin-right: 10px;">회원가입</a>
						</footer>
					</div>
					<div class="3u$ 12$(small)">
						<br>
					</div>
				</div>
			</div>
		</section>
		
		<!-- Footer -->
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
		
		<!-- Script -->
		<script src="resources/js/skel.min.js"></script>
		<script src="resources/js/util.js"></script>
		<script src="resources/js/main.js"></script>	
	</body>
</html>

<%-- 
<form name="loginForm">
	<input type="text" name="user_id" placeholder="아이디" required><br>
	<input type="password" name="user_password" placeholder="비밀번호" required><br>
	<div id="loginMessage">${loginMessage}</div>
	<button type="button" onclick="account(this.form)">로그인</button>
</form>
<button onclick="location.href='account_emailCertification.page'">회원가입</button>
--%>