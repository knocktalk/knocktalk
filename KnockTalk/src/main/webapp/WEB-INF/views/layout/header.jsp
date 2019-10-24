<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header id="header">
	<div class="inner">
		<a href="index.page" class="logo">KnockTalk</a>
		<nav id="nav">
			<c:choose>
				<c:when test="${user_id eq null}">
					<a href="login.page">Login</a>
					<a href="register.page">Register</a>
				</c:when>
				<c:otherwise>
					<a href="logout.do">Logout</a>
					<a href="mypage.do">My Page</a>
				</c:otherwise>
			</c:choose>
		</nav>
		<a href="#navPanel" class="navPanelToggle"><span class="fa fa-bars"></span></a>
	</div>
</header>