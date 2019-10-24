function clauseCheck() {
	if ($("#clauseAgree").is(':checked')) {
		location.href = "account_clause.do";
	} else {
		$("#clauseMessage").text("노크톡 이용약관에 동의해주세요.");
	}
}
function certificationCheck() {
	if ($("input[name=user_email]").val() != null) {
		account(document.certificationForm);
	} else {
		return;
	}
}
function settingCheck(isSubmit) {
	if (!isSubmit) {
		$("input[name=user_image]").attr('disabled', true);
		$("input[name=user_nickname]").attr('disabled', true);
	}
	account(document.settingForm);
}

// Form Action Controller
function account(form) {
	switch (form) {
	case document.loginForm:
		form.action = "account_login.do";
		form.submit();
		break;

	case document.certificationForm:
		form.action = "account_certification.do";
		form.submit();
		break;

	case document.registerForm:
		form.action = "account_register.do";
		form.submit();
		break;

	case document.settingForm:
		form.action = "account_setting.do";
		form.submit();
		break;
	}
}

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#profileImage').attr('src', e.target.result);
		}

		reader.readAsDataURL(input.files[0]);
	}
}

$("input[name=image]").change(function() {
	readURL(this);
});