function signup(baseUrl) {
	var username = $("#username").val(), pass = $("#password").val(), email = $(
		"#email").val(), name = $("#name").val(), passConfirm = $(
			"#password_confirm").val();

	if (username || pass || email || name || passConfirm) {
		if (pass == passConfirm) {
			let data = {
				username: username,
				password: pass,
				email: email,
				name: name,
			};
			$.ajax({
				url: baseUrl + "/ajax/signup",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(data),
				dataType: "json",
				success: function(jsonResult) {
					Swal.fire('Thành công', "Đăng ký tài khoản thành công", 'success');
					$("#username").val("");
					pass = $("#password").val("");
					email = $("#email").val("");
					name = $("#name").val("");
					passConfirm = $("#password_confirm").val("");
				}
			});
		} else {
			Swal.fire('Thất bại', 'Mật khẩu xác nhận không chính xác', 'error');
		}
	} else {
		Swal.fire('Thất bại', 'Vui lòng nhập đầy đủ thông tin', 'error');
	}
	

}

function forgotPassword(baseUrl) {
	(async () => {
		const { value: data } = await Swal.fire({
			title: 'Nhập thông tin tài khoản',
    		confirmButtonText: "Lấy mật khẩu",
			html:
				'<input id="usernameGetPass" class="swal2-input" style="width: 370px;" placeholder="Tên tài khoản">' +
				'<input id="emailGetPass" type="email" class="swal2-input" style="width: 370px;" placeholder="Email">',
			preConfirm: () => ({
				username: $('#usernameGetPass').val(),
				email: $('#emailGetPass').val()
			})
		});

		if (data.username || data.email) {
			$.ajax({
				url: baseUrl + "/ajax/forgot-password",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(data),
				dataType: "json",
				success: function(jsonResult) {
					if(jsonResult.status){
						Swal.fire('Thành công', "Mật khẩu của bạn đã được gửi vào mail. <br/> Vui lòng kiểm tra trong email!", 'success');
					} else {
						Swal.fire('Thất bại', "Tên đăng nhập hoặc email không đúng", 'error');
					}
				}
			});
		} else {
			Swal.fire('Thất bại', 'Vui lòng nhập đầy đủ thông tin', 'error');
		}
	})()
}