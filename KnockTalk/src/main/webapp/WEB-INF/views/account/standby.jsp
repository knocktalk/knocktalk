<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="stylesheet" href="resources/css/main.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		
		<title>Certification</title>
	</head>
	<body class="subpage">
		<!-- Header -->
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
		
		<!-- Standby -->
		<section class="wrapper">
			<div class="inner">
				<header class="align-center">
					<h2>인증 링크를 전송했습니다!</h2>
				</header>
				<footer class="align-center">
					<span>입력하신 </span><span style="color: blue;">${user_email}</span><span>(으)로 링크를 전송했습니다.</span><br>
					<span>해당 링크를 통해 회원가입을 진행해 주시기 바랍니다.</span>
				</footer>
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