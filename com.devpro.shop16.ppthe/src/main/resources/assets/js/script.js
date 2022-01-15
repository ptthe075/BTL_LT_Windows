$(function() {

	if (!$("div").hasClass("block-breadcrumbs")) {
		$("header.header").addClass("box-shadow");
	}

	if (parseInt($("#iconShowTotalItemsInCart").text())) {
		$("#iconShowTotalItemsInCart").css("display", "flex");
	}


	//#region Search click
	$("#icon-search").click(function(event) {
		event.preventDefault();

		$("#search").addClass("mobile");
		$("#search").removeClass("hidden");
	});
	$("#close-search").click(function(event) {
		event.preventDefault();

		$("#search").removeClass("mobile");
		$("#search").addClass("hidden");
	});
	//#endregion Search click

	//#region Menu hover
	if ($('.slide_bar').length != 0) {
		$('.header__container-nav').hover(function() {
			displayNav();
			$(window).scroll(function() {
				displayNav();
			});
		});
	}

	function displayNav() {
		if ($(window).scrollTop() >= $('.slide_bar').height()) {
			$('.header__container-nav').css('pointer-events', 'auto');
		} else {
			$('.header__container-nav').css('pointer-events', 'none');
		}
	}
	//#endregion

	// #region Contact us 
	const inputs = document.querySelectorAll(".contact__input");

	function focusFunc() {
		let parent = this.parentNode;
		parent.classList.add("focus");
	}

	function blurFunc() {
		let parent = this.parentNode;
		if (this.value == "") {
			parent.classList.remove("focus");
		}
	}

	inputs.forEach((input) => {
		input.addEventListener("focus", focusFunc);
		input.addEventListener("blur", blurFunc);
	});
	//#endregion Contact us

	$(function() {
		$(".account__form-group.checkbox>input").change(function() {
			// $("input:checkbox").prop('checked', $(this).prop("checked"));
			if ($(this).prop("checked")) {
				$("#change-password").show();
			} else {
				$("#change-password").hide();
				$("#current_pass").val("");
				$("#new_pass").val("");
				$("#re_new_pass").val("");
			}
		});
		$('li.tab-order__link').click(function() {
			var tab_id = $(this).attr('data-tab');

			$('li.tab-order__link').removeClass('current');
			$('.tab-content').removeClass('current');

			$(this).addClass('current');
			$(tab_id).addClass('current');

			$("tab_id").fadeIn(3000);
		})
	})
});

function AddToCart(baseUrl, productId, quanlity) {
	let data = {
		productId: productId,
		quantity: quanlity,
	};

	jQuery.ajax({
		url: baseUrl + "/ajax/addToCart",
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(data),
		dataType: "json",
		success: function(jsonResult) {
			$("#iconShowTotalItemsInCart").html(jsonResult.totalItems);
			if ($("#iconShowTotalItemsInCart").css("display") == "none") {
				$("#iconShowTotalItemsInCart").css("display", "flex");
			}

			Swal.fire({
				toast: true,
				position: 'top-end',
				showConfirmButton: false,
				title: 'Đã thêm sản phẩm vào giỏ hàng',
				icon: 'success',
				timer: 1500,
			})
		}
	});
}
function editMyAccount(baseUrl) {
	let data = {
		id: $("#idAccount").val(),
		email: $("#user_email").val(),
		name: $("#display_name").val(),
		phone: $("#phone_number").val(),
		password: null,
	};

	if ($(".account__form-group.checkbox>input").prop("checked")) {
		var pass = $("#current_pass").val(), newPass = $("#new_pass").val(), reNewPass = $("#re_new_pass").val();

		if (!pass || !newPass || !reNewPass) {
			Swal.fire(
				'Thất bại',
				'Vui lòng nhập mật khẩu để đổi',
				'error'
			)
			return
		}

		data.password = pass;

		$.ajax({
			url: baseUrl + "/ajax/account/check",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data),
			dataType: "json",
			success: function(jsonResult) {
				if (jsonResult.status) {
					if (newPass === reNewPass) {
						data.password = newPass;
						$.ajax({
							url: baseUrl + "/ajax/account/change",
							type: "post",
							contentType: "application/json",
							data: JSON.stringify(data),
							dataType: "json",
							success: function(jsonResult) {
								Swal.fire(
									'Thành công',
									'Cập nhật tài khoản thành công',
									'success'
								)
							}
						});
					} else {
						Swal.fire(
							'Thất bại',
							'Mật khẩu mới không trùng nhau',
							'error'
						)
					}
				} else {
					Swal.fire(
						'Thất bại',
						'Mật khẩu cũ không chính xác',
						'error'
					)
				}
			}
		});
	} else {
		$.ajax({
			url: baseUrl + "/ajax/account/change",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data),
			dataType: "json",
			success: function(jsonResult) {
				Swal.fire(
					'Thành công',
					'Cập nhật tài khoản thành công',
					'success'
				)
			}
		});
	}
}

function updateOrderStatus(id, status) {
	var str = '';
	if (status == 3) { str = 'Đánh giá' }
	else { str = "Nhập lý do muốn hủy" }
	(async () => {
		const { value: note } = await Swal.fire({
			input: 'textarea',
			inputLabel: str,
			inputPlaceholder: 'Nhập nội dung ...',
			inputAttributes: {
				'aria-label': 'Nhập nội dung'
			},
			showCancelButton: true
		})

		if (note) {
			let data = { id: id, userId: status, note: note };
			$.ajax({
				url: "/ajax/account/updateOrderStatus",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(data),
				dataType: "json",
				success: function(jsonResult) {
					if (jsonResult.message) {
						Swal.fire('Thành công', jsonResult.message, 'success');
						$(".account__form-orders").html(jsonResult.buildOrders);
					} else {
						Swal.fire('Thất bại', "Lỗi", 'error');
					}
				}
			});
		}
	})()
}

function subscribe(baseUrl) {
	var email = $("#emailSubscribe");

	if (ValidateEmail(email)) {
		let data = {
			email: email.val()
		};
		$.ajax({
			url: baseUrl + "/ajax/subcrible", //->action
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data),

			dataType: "json", // Kieu du lieu tra ve tu controller la json vì controlelr action trả về kiểu Map
			success: function(jsonResult) {

				if (jsonResult.err) {
					Swal.fire('Thất bại', jsonResult.err, 'error');
				} else {
					Swal.fire('Thành công', jsonResult.message, 'success');
				}
				email.val("");
			}
		});
	}
}

//#region Subscribe
function ValidateEmail(inputText) {
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if (inputText.val().match(mailformat)) {
		return true;
	} else {
		Swal.fire('Thất bại', 'Cú pháp nhập email không đúng', 'error');
		inputText.val("");
		return false;
	}
}
	//#endregion Subscribe