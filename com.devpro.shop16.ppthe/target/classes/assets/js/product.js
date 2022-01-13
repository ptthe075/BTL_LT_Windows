$(function() {

    slickSlider($('.list-brand'), $('.btn-action__prev[name-category="brand"]'), $('.btn-action__next[name-category="brand"]'), 8);

    $(".list-product").each(function() {
        var nameCategory = $(this).attr("name-category");
        var selector = '.list-product[name-category="' + nameCategory + '"]';
        var prev = '.btn-action__prev[name-category="' + nameCategory + '"]';
        var next = '.btn-action__next[name-category="' + nameCategory + '"]';
        slickSlider($(selector), $(prev), $(next), 5);
    });

	//Change quantity
    $(".box-quantity__change").click(function(e) {
        e.preventDefault();
        var dataValue = parseInt($(this).attr("data-value"));
        var quantity = parseInt($(".box-quantity__input").attr("value"));
        var minus = $('.box-quantity__change[data-value="-1"]');
        var plus = $('.box-quantity__change[data-value="1"]');
        if (quantity) {
            quantity += dataValue;
            if (quantity >= 1 && quantity <= 10) {
                $(".box-quantity__input").attr("value", quantity);

                minus.removeClass("disable");
                plus.removeClass("disable");
            }
            if (quantity === 1) {
                $(".box-quantity__input").attr("value", 1);
                minus.addClass("disable");
            }
            if (quantity === 10) {
                plus.addClass("disable");
            }
        }
    });

    //#region Shopping cart
    $(".shopping-cart__title .cart-col-product").find(".total-products").text($(".shopping-cart__item").length);

    //Check
    $("#js-check-cart-all-item").click(function(e) {
        e.preventDefault();
        $(this).toggleClass("active");

        if ($(this).hasClass("active")) {
            $(".shopping-cart__item").each(function() {
                $(this).find(".js-check-cart-item").addClass("active");
            });
        } else {
            $(".shopping-cart__item").each(function() {
                $(this).find(".js-check-cart-item").removeClass("active");
            });
        }
    });

    $(".shopping-cart__list .shopping-cart__checkbox.js-check-cart-item").click(function(e) {
        e.preventDefault();
        $(this).toggleClass("active");

        var sum = 0
        $(".shopping-cart__item").each(function(index) {
            if ($(this).find(".js-check-cart-item").hasClass("active")) {
                sum++;
            }
        });

        if (sum === $(".shopping-cart__item").length) {
            $("#js-check-cart-all-item").addClass("active");
        } else {
            $("#js-check-cart-all-item").removeClass("active");
        }
    });
    //Delete
    $(".cart-col-delete").click(function(e) {
        e.preventDefault();
		var parent = $(this).parents(".shopping-cart__item")
		var id = parent.attr("id");
		var url = parent.attr("baseUrl");
        parent.remove();
		DeleteItemCart(url,id);
        $("#js-view-quantity-item>span").text($(".shopping-cart__item").length + ' sản phẩm');
    });

    $("#js-delete-all-item").click(function(e) {
        e.preventDefault();
        $(".shopping-cart__item").each(function(index) {
            $(this).remove();
        });
		$("#js-view-quantity-item>span").text($(".shopping-cart__item").length + ' sản phẩm');
		DeleteAllCart($(this).attr("baseUrl"));
    });


    //Change quantity
    $(".cart-quantity__change").click(function(e) {
        e.preventDefault();
        var parent = $(this).parents(".shopping-cart__item");

        var dataValue = parseInt($(this).attr("data-value"));
        var quantity = parseInt(parent.find(".cart-quantity__input").val());
        if (quantity) {
            quantity += dataValue;
            if (quantity >= 1) {
                parent.find(".cart-quantity__input").val(quantity);
                parent.find(".cart-quantity__input").attr("value", quantity);

				var id = parent.attr("id");
				var url = parent.attr("baseUrl");
				UpdateQuanlityCart(url,id,quantity);

                var price = parseInt(parent.find(".cart-col-price").attr("value"));
                parent.find(".cart-col-total-price").text(formatNumber(quantity * price, 0, ',', '.'));
            }
        }
    });
    $(".cart-quantity__input").change(function(e) {
        e.preventDefault();
        var parent = $(this).parents(".shopping-cart__item");
        var quantity = parseInt($(this).val());

        if (quantity < 1 || !quantity) {
            quantity = 1;
            $(this).val(quantity);
        }
		
		var id = parent.attr("id");
		var url = parent.attr("baseUrl");
		UpdateQuanlityCart(url,id,quantity);
		
        var price = parseInt(parent.find(".cart-col-price").attr("value"));
        parent.find(".cart-col-total-price").text(formatNumber(quantity * price, 0, ',', '.'));
    });

    //#endregion Shopping cart

    function slickSlider(selector, prev, next, num) {
        if (selector.length) {
            selector.slick({
                slidesToShow: num,
                slidesToScroll: num,
                infinite: false,
                prevArrow: prev,
                nextArrow: next
            });

            prev.hide();

            selector.on('afterChange', function(event, slick, currentSlide) {
                if (currentSlide === 0) {
                    prev.hide();
                    next.show();
                } else {
                    prev.show();
                    next.show();
                }
                if (slick.slideCount <= currentSlide + num) {
                    next.hide();
                    prev.show();
                }
            });
        }
    }
});

function UpdateQuanlityCart(baseUrl, productId, quantity) {
	let data = {
		productId: productId,
		quantity: quantity,		
	};
	jQuery.ajax({
		url: baseUrl + "/ajax/updateQuanlityCart",
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(data),
		dataType: "json",
		success: function(jsonResult) {
			$("#iconShowTotalItemsInCart").html(jsonResult.totalItems);
			$( "#js-total-cart-payment").html(formatNumber(jsonResult.totalPrice, 0, ',', '.'));
		}
	});
}

function DeleteItemCart(baseUrl, productId) {
	let data = {
		productId: productId,
	};
	jQuery.ajax({
		url: baseUrl + "/ajax/deleteItemCart",
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(data),
		dataType: "json",
		success: function(jsonResult) {
			if(jsonResult.totalItems == 0){
				$("#iconShowTotalItemsInCart").css("display","none");
			}
			$("#iconShowTotalItemsInCart").html(jsonResult.totalItems);
			$( "#js-total-cart-payment").html(formatNumber(jsonResult.totalPrice, 0, ',', '.'));
			Swal.fire({
				toast: true,
				position: 'top-end',
				showConfirmButton: false,
				title: 'Đã xóa sản phẩm khỏi giỏ hàng',
				icon: 'success',
				timer: 1500,
			})
		}
	});
}

function DeleteAllCart(baseUrl) {
	jQuery.ajax({
		url: baseUrl + "/ajax/deleteAllCart",
		type: "post",
		contentType: "application/json",
		dataType: "json",
		success: function(jsonResult) {
			$("#iconShowTotalItemsInCart").css("display","none");
			$( "#js-total-cart-payment").html(formatNumber(jsonResult.totalPrice, 0, ',', '.'));
			Swal.fire({
				toast: true,
				position: 'top-end',
				showConfirmButton: false,
				title: 'Đã toàn bộ sản phẩm trong giỏ hàng',
				icon: 'success',
				timer: 1500,
			})
		}
	});
}

function formatNumber (number, decimals, dec_point, thousands_sep) {
        number = number.toFixed(decimals);

        var nstr = number.toString();
        nstr += '';
        x = nstr.split('.');
        x1 = x[0];
        x2 = x.length > 1 ? dec_point + x[1] : '';
        var rgx = /(\d+)(\d{3})/;

        while (rgx.test(x1))
            x1 = x1.replace(rgx, '$1' + thousands_sep + '$2');

    return x1 + x2+' ₫';
}
