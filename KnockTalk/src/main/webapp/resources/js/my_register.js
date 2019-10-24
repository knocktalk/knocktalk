var id_check = false;
var password_check = false;

function idCheck() {
	if ($("#idMessage").text()=="사용 가능한 아이디입니다.") {
		id_check = true;
	} else {
		id_check = false;
	}
	
	allCheck();
}
function passwordCheck() {
	var password = $("input[name=user_password]").val();
	var re_password = $("input[name=user_rePassword]").val();
	
	if (password==re_password) {
		$("#rePasswordMessage").text("비밀번호가 일치합니다.");
		password_check = true;
	} else {
		$("#rePasswordMessage").text("비밀번호가 일치하지 않습니다.");
		password_check = false;
	}
	
	allCheck();
}
function allCheck() {
	if (id_check==true && password_check==true) {
		$("#registerButton").prop("disabled", false);
	} else {
		$("#registerButton").prop("disabled", true);
	}
}

function overlapCheck() {
	var value = $("input[name=user_id]").val();
	var param = {"user_id": value};
	$.ajax({
		url: "account_overlapCheck.do",
		type: "GET",
		data: param,
		success: function(data) {
			$("#idMessage").text(data);
			idCheck();
		}
	})
}
