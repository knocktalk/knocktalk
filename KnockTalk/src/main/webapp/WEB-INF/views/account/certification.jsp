<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="stylesheet" href="resources/css/main.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="resources/js/my_controller.js"></script>
		
		<title>Certification</title>
	</head>
	<body class="subpage">
		<!-- Header -->
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
		
		<!-- Certification -->
		<section class="wrapper">
			<div class="inner">
				<header class="align-center">
					<h2>이메일 인증</h2>
					<p>이메일로 받은 링크를 통해 회원가입을 진행할 수 있습니다.</p>
				</header>
				<div class="row">
					<div class="2u 12$(small)">
						<br>
					</div>
					<div class="8u 12$(small)">
						<br>
						<form name="certificationForm">
							<div class="row uniform">
								<div class="9u 12u$(xsmall)">
									<input type="email" name="user_email" value="${user_email}">
								</div>	
								<div class="3u$ 12u$(xsmall)">
									<input type="button" onclick="certificationCheck()" value="인증" class="fit">
								</div>
							</div>
						</form>
					</div>
					<div class="2u$ 12$(small)">
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