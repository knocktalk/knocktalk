<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta charset="UTF-8">
		<title>내 정보</title>
		
		<script src="resources/js/controller.js"></script>
		<script src="resources/js/myJQuery.js"></script>		
		<script>
			function signout(){
				var check_signout = confirm("정말 진짜 완전 탈퇴하시겠습니까?");
				
				if(check_signout == true){
					location.href='user_signout.do?user_id=${my_info.user_id}';
				} else if(check_signout == false) {
					alert("취소되었습니다");
				}
			}	
			
		</script>
	</head>
	<body>
		<c:choose>
			<c:when test="${user_id != null}">
					* 아이디 : ${my_info.user_id}<br>
					* 이름 : ${my_info.user_name}<br>
					* 별명 : ${my_info.user_nickname}<br>
					* 생일 : ${my_info.user_birth}<br>
					* 성별 : ${gender}<br>
					* 이메일  ${my_info.user_email}<br>
					<c:if test="${authstatus == 3 }">
						<gg>인증 메일을 확인해야 이메일 변경이 가능합니다.</gg><br>
					</c:if>	
					* 국적 : ${my_info.user_nationality}<br>
					* 휴대전화 : ${my_info.user_phonenumber}<br>
				<button type="submit" name="information_edit" onclick="location.href='modify_information.do?user_name=${my_info.user_name}&user_nickname=${my_info.user_nickname}'">개인정보수정</button>
				<button type="submit" name="password_edit" onclick="location.href='modify_password.do'">비밀번호수정</button>
				<button type="submit" name="email_edit" onclick="location.href='modify_email.do'">이메일수정</button>
				<button onclick="signout()">회원탈퇴</button>
			</c:when>
		</c:choose>
	</body>
</html>