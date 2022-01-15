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
                <div class="col c-9 cart-wrapper block-container">
                	<div class="border-radius box-shadow  margin-bottom-10">
                        <div class="shopping-cart__title display-flex border-none no-border">
                            <div id="js-view-quantity-item">
                                Thông tin giỏ hàng (<span>${cart.cartItems.size()} sản phẩm </span>)
                            </div>
                            <a href="${base}/home" id="js-choose-new-item">
                                <i class="fas fa-chevron-left"></i> Chọn tiếp sản phẩm khác
                            </a>
                            <div id="js-delete-all-item" baseUrl="${base}">Xóa giỏ hàng</div>
                        </div>
                    </div>
                    <div id="list-cart-item" class="border-radius box-shadow overflow-hidden">
	                    <c:choose>
	                    	<c:when test="${not empty cart.cartItems}">
		                    	<div class="shopping-cart__title display-flex">
		                            <div class="cart-col-product">Sản phẩm</div>
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
	                    	</c:when>
	                    	<c:otherwise>
	                    		<div class="no-order" style="height: 284px;"><img src="${base}/assets/imgs/empty-order.png">
	                            	<p>Chưa có sản phẩm nào trong giỏ hàng của bạn</p>
	                            </div>
	                    	</c:otherwise>
	                    </c:choose>
                     </div>
                </div>
                <div class="col c-3 block-container padding-left-0">
                    <form action="${base}/cart/checkout" method="post" class="display-flex border-radius box-shadow">
                        <div class="col c-12 cart-customer-block">
                            <p class="cart-customer__title"> ĐỊA CHỈ NHẬN HÀNG </p>
                            <div class="display-flex">
                            	<c:choose>
                            		<c:when test="${isLogined}">
                            			<input type="text" class="form-customer-info" value="${userLogined.name }" disabled>
                                		<input type="text" class="form-customer-info" value="${userLogined.email }" disabled>
                                		<c:choose>
                                			<c:when test="${not empty userLogined.phone }">
                                				<input type="text" class="form-customer-info" value="${userLogined.phone }" disabled>
                                			</c:when>
                                			<c:otherwise>
                                				<input type="text" class="form-customer-info" placeholder="Số điện thoại" name="user_info[phone]" id="buyer_mobile" required>
                                			</c:otherwise>
                                		</c:choose>
                            		</c:when>
                                	<c:otherwise>
                                		<input type="text" class="form-customer-info" placeholder="Họ và tên" name="user_info[name]" id="buyer_name" required>
                                		<input type="text" class="form-customer-info" placeholder="Email" name="user_info[email]" id="buyer_email" required>
		                                <input type="text" class="form-customer-info" placeholder="Số điện thoại" name="user_info[phone]" id="buyer_mobile" required>
                                	</c:otherwise>
                            	</c:choose>
                                <input type="text" class="form-customer-info" placeholder="Địa chỉ" name="user_info[address]" id="buyer_address" required>
                            </div>
                        </div>
                        <div class="col c-12 cart-customer-block">
	                        <div class="cart__payment-total">
	                            Tổng tiền hàng: 
	                            <b id="js-total-cart-payment">
	                            	<c:choose>
		                            	<c:when test="${not empty cart.totalPrice}">
		                                	<fmt:formatNumber value="${cart.totalPrice }" pattern="###,### ₫" type="number"/>
		                            	</c:when>
										<c:otherwise>
											0 ₫
										</c:otherwise>	                            	
	                            	</c:choose>
	                            </b>
	                        </div>
	                        <button class="cart__button-buy" type="submit"> ĐẶT MUA NGAY </button>
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