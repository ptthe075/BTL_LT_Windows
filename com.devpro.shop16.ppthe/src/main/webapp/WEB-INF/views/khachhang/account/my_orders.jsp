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
<title>Đơn hàng của tôi</title>
<jsp:include page="/WEB-INF/views/khachhang/layout/css.jsp"></jsp:include>
<link rel="stylesheet"
	href="${base}/assets/lib/slick/css/slick.style.css">
<link rel="stylesheet" href="${base}/assets/css/product.css">
<link rel="stylesheet" href="${base}/assets/css/style.css">
<link rel="stylesheet" href="${base}/assets/css/reponsive.css">
<style type="text/css">
	ul.tabs-orders {
		margin: 0px;
		padding: 0px;
		list-style: none;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
        
	li.tab-order__link {
		width: 100%;
		color: var(--text-color);
		font-weight: bold;
		text-align: center;
		padding: 10px 0;
		cursor: pointer;
		border-bottom: 3px solid transparent;
	}
        
	li.tab-order__link.current {
		color: var(--background-color);
		border-bottom-color: var(--background-color);
	}
        
	.tab-content {
		display: none;
		opacity: 0;
		transform: translateX(100%);
		animation: fadeIn 0.5s ease 1 forwards;
	}
        
	.tab-content.current {
		display: inherit;
	}
        
	@keyframes fadeIn {
		100% {
			opacity: 1;
			transform: none;
		}
	}
</style>
</head>

<body>
	<!-- Common -->
	<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/khachhang/layout/header.jsp"></jsp:include>

	<div class="container-wrapper">
        <div class="grid">
            <div class="row">
                <div class="col col-5 block-container">
                    <div class="account-navbar box-shadow border-radius">
                        <a href="${base}/account/edit-account"><i class="fas fa-user"></i>Tài khoản của tôi</a>
                        <a class="active"  href="${base}/account/orders"><i class="fas fa-list-alt"></i>Đơn hàng của tôi</a>
                        <c:if test="${isAdmin}">
                        	<a href="${base}/admin"><i class="fas fa-user-shield"></i>Trang admin</a>
						</c:if>
                        <a href="${base}/logout"><i class="fas fa-sign-out-alt"></i>Đăng xuất</a>
                    </div>
                </div>
                <div class="col col-5-4 padding-left-0 block-container">
                    <div class="account-container box-shadow border-radius">
                    	<div class="account-info-container">
                            <h3>Đơn hàng của tôi</h3>
                            <div class="account__form-container order" style="overflow: hidden;">
                                <ul class="tabs-orders">
                                    <li class="tab-order__link current" data-tab="#all-menus">Tất cả đơn</li>
                                    <li class="tab-order__link" data-tab="#processing">Đang xử lý</li>
                                    <li class="tab-order__link" data-tab="#delivering">Đang giao</li>
                                    <li class="tab-order__link" data-tab="#delivered">Đã giao</li>
                                    <li class="tab-order__link" data-tab="#cancelled">Đã hủy</li>
                                </ul>
								
								<div class="account__form-orders">
									${buildOrders}
								</div>
                            </div>
                        </div>
                    </div>
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