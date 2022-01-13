<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Giỏ hàng</title>
<jsp:include page="/WEB-INF/views/khachhang/layout/css.jsp"></jsp:include>
<link rel="stylesheet"
	href="${base}/assets/lib/slick/css/slick.style.css">
<link rel="stylesheet" href="${base}/assets/css/product.css">
<link rel="stylesheet" href="${base}/assets/css/style.css">
<link rel="stylesheet" href="${base}/assets/css/reponsive.css">
<style type="text/css">
	.cart-customer-block {
            margin-bottom: 10px;
        }
        
        .form-customer-info {
            width: 80%;
            margin: 5px 0;
            padding: 5px;
        }
        
        .form-customer-info:focus-visible {
            outline: none;
        }
        
        .cart__payment-methods {
            margin-bottom: 10px;
        }
        
        .label-container {
            width: 100%;
            line-height: 20px;
            margin: 5px 0;
        }
        
        .cart__button-buy {
            margin: 10px 0;
        }
</style>
</head>

<body>
	<!-- Common -->
	<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/khachhang/layout/header.jsp"></jsp:include>

	<div class="block-breadcrumbs box-shadow">
        <div class="grid breadcrumbs">
            <ul class="breadcrumb-list">
                <li class="breadcrumb-item">
                    <a href="#" class="breadcrumb-link">
                        <i class="fas fa-home breadcrumb-home"></i> Trang chủ
                    </a>
                    <i class="fas fa-angle-right"></i>
                </li>
                <li class="breadcrumb-item">
                    Thông tin giỏ hàng
                </li>
            </ul>
        </div>
    </div>

    <div class="container-wrapper">
        <div class="grid">
            <div class="row">
                <div class="col c-12 cart-wrapper margin-bottom-10">
                    <div class="border-radius box-shadow">
                        <div class="shopping-cart__title display-flex border-none">
                            <div id="js-view-quantity-item">
                                Thông tin giỏ hàng (<span>${cart.cartItems.size()} sản phẩm </span>)
                            </div>
                            <a href="${base}/home" id="js-choose-new-item">
                                <i class="fas fa-chevron-left"></i> Chọn tiếp sản phẩm khác
                            </a>
                            <div id="js-delete-all-item" baseUrl="${base}">Xóa giỏ hàng</div>
                        </div>
                    </div>
                </div>
                <div class="col c-12 cart-wrapper block-container">
                    <div class="border-radius box-shadow">
                        <div class="shopping-cart__title display-flex">
                            <div class="cart-col-product">Sản phẩm</div>
                            <div class="cart-col-color">Màu sắc</div>
                            <div class="cart-col-price">Đơn giá</div>
                            <div class="cart-col-quantity">Số lượng</div>
                            <div class="cart-col-total-price">Thành tiền</div>
                            <div class="cart-col-delete">Thao tác</div>
                        </div>
                        <div class="shopping-cart__list">
                        	<c:forEach var="ci" items="${cart.cartItems}">
	                            <div class="shopping-cart__item display-flex" id="${ci.productId}" baseUrl="${base}">
	                                <div class="cart-col-product">
	                                    <a href="" class="cart-col-product__img">
	                                        <img src="${base}/Upload/Products/${ci.productAvatar}" width="100%" alt="">
	                                    </a>
	                                    <div class="cart-col-product__info">${ci.productName}</div>
	                                </div>
	                                <div class="cart-col-color">
	                                    Màu Xanh
	                                </div>
	                                <div class="cart-col-price" value="${ci.priceUnit}">
	                                    <fmt:formatNumber value="${ci.priceUnit}" pattern="###,### ₫" type="number"/>
	                                </div>
	                                <div class="cart-col-quantity">
	                                    <a href="" class="cart-quantity__change" data-value="-1"> - </a>
	                                    <input type="number" class="cart-quantity__input" value="${ci.quantity}">
	                                    <a href="" class="cart-quantity__change" data-value="1"> + </a>
	                                </div>
	                                <div class="cart-col-total-price"><fmt:formatNumber value="${ci.priceUnit * ci.quantity}" pattern="###,### ₫" type="number"/></div>
	                                <div class="cart-col-delete">
	                                    <i class="fas fa-trash-alt"></i> Xóa
	                                </div>
	                            </div>
                        	</c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col c-12 block-container">
                    <form action="${base}/cart/checkout" method="post" class="display-flex border-radius box-shadow">
                        <div class="col c-6 cart-customer-block">
                            <p class="title red font-600 text-15"> ĐỊA CHỈ NHẬN HÀNG </p>
                            <div class="display-flex">
                            	<c:choose>
                            		<c:when test="${isLogined}">
                            			<input type="text" class="form-customer-info" value="${userLogined.name }" disabled>
                                		<input type="text" class="form-customer-info" value="${userLogined.email }" disabled>
                            		</c:when>
                                	<c:otherwise>
                                		<input type="text" class="form-customer-info" placeholder="Họ và tên" name="user_info[name]" id="buyer_name" required>
                                		<input type="text" class="form-customer-info" placeholder="Email" name="user_info[email]" id="buyer_email" required>
                                	</c:otherwise>
                            	</c:choose>
                                <input type="text" class="form-customer-info" placeholder="Số điện thoại" name="user_info[phone]" id="buyer_mobile" required>
                                <input type="text" class="form-customer-info" placeholder="Địa chỉ" name="user_info[address]" id="buyer_address" required>
                            </div>
                        </div>

                        <div class="col c-6 cart-customer-block">
                            <p class="title red font-600 text-15"> HÌNH THỨC THANH TOÁN </p>

                            <div class="cart__payment-methods display-flex">
                                <label class="label-container">
                                    <input type="radio" id="pay_method_1" name="pay_method" value="1" checked="">
                                    <span>Thanh toán tiền mặt khi nhận hàng</span>
                                </label>
                                <label class="label-container">
                                    <input type="radio" id="pay_method_1" name="pay_method" value="1" checked="">
                                    <span>Thanh toán tiền mặt khi nhận hàng</span>
                                </label>
                                <label class="label-container">
                                    <input type="radio" id="pay_method_1" name="pay_method" value="1" checked="">
                                    <span>Thanh toán tiền mặt khi nhận hàng</span>
                                </label>
                                <label class="label-container">
                                    <input type="radio" id="pay_method_1" name="pay_method" value="1" checked="">
                                    <span>Thanh toán tiền mặt khi nhận hàng</span>
                                </label>
                            </div>

                            <!-- <div class="cart__voucher">
                                <div class="cart-voucher-holder position-relative">
                                    <input type="text" placeholder="Nhập mã giảm giá/quà tặng" id="discount_code" name="coupon_code" value=" " />
                                    <button type="button" class="submit-voucher js-apply-discount-code">Áp dụng</button>
                                </div>
                                <span class="red d-block" id="js-voucher-message"> </span>
                            </div> -->

                            <div class="">
                                Tổng tiền hàng: 
                                <b class="red text-22 font-600" id="js-total-cart-payment">
                                	<fmt:formatNumber value="${cart.totalPrice }" pattern="###,### ₫" type="number"/>
                                </b>
                            </div>

                            <div class="cart__button-buy">
                                <button type="button"> MUA TRẢ GÓP </button>
                                <button type="submit"> ĐẶT MUA NGAY </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
	
	<jsp:include page="/WEB-INF/views/khachhang/layout/footer.jsp"></jsp:include>

	<script src="${base}/assets/lib/jquery/jquery.min.js"></script>
	<script src="${base}/assets/lib/slick/js/slick.min.js"></script>
	<script src="${base}/assets/lib/slick/js/slick.script.js"></script>
	<script src="${base}/assets/lib/sweetalert/sweetalert2.all.min.js"></script>
	<script src="${base}/assets/js/product.js"></script>
	<script src="${base}/assets/js/script.js"></script>
</body>

</html>