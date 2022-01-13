$(function() {

	if (!$("div").hasClass("block-breadcrumbs")) {
		$("header.header").addClass("box-shadow");
	}
	
	if(parseInt($("#iconShowTotalItemsInCart").text())){
		$("#iconShowTotalItemsInCart").css("display","flex");
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
			if($("#iconShowTotalItemsInCart").css("display") == "none"){				
				$("#iconShowTotalItemsInCart").css("display","flex");
			}
		}
	});
}


function subscribe(baseUrl) {
	var email = $("#emailSubscribe");

	if (ValidateEmail(email)) {
		let data = {
			email: email.val() // Lay theo id
		};
		// $ === jQuery
		// json == javascript object
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