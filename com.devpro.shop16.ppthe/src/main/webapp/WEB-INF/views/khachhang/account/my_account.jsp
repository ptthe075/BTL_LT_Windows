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
<title>Tài khoản của tôi</title>
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

	<div class="container-wrapper">
        <div class="grid">
            <div class="row">
                <div class="col col-5 block-container">
                    <div class="account-navbar box-shadow border-radius">
                        <a class="active" href="${base}/account/edit-account"><i class="fas fa-user"></i>Tài khoản của tôi</a>
                        <a href="${base}/account/orders"><i class="fas fa-list-alt"></i>Đơn hàng của tôi</a>
                        <c:if test="${isAdmin}">
                        	<a href="${base}/admin"><i class="fas fa-user-shield"></i>Trang admin</a>
						</c:if>
                        <a href="${base}/logout"><i class="fas fa-sign-out-alt"></i>Đăng xuất</a>
                    </div>
                </div>
                <div class="col col-5-4 padding-left-0 block-container">
                    <div class="account-container box-shadow border-radius">
                        <div class="account-info-container">
                            <h3>Tài khoản của tôi</h3>
                            <div class="account__form-container">
                            	<input type="hidden" id="idAccount" value="${myAccount.id}">
                                <div class="account__form-group">
                                    <label>Tài khoản</label>
                                    <input type="text" id="user_login" style="background: #ccc" name="user_login" placeholder="Tài khoản" readonly value="${myAccount.username}">
                                </div>
                                <div class="account__form-group">
                                    <label for="display_name">Họ tên</label>
                                    <input type="text" id="display_name" name="display_name" placeholder="Họ tên" value="${myAccount.name}">
                                </div>
                                <div class="account__form-group">
                                    <label for="user_email">Email</label>
                                    <input type="text" id="user_email" name="user_email" placeholder="Email" value="${myAccount.email}">
                                </div>
                                <div class="account__form-group">
                                    <label for="phone_number">Số điện thoại</label>
                                    <input type="text" id="phone_number" name="phone_number" placeholder="Số điện thoại" value="${myAccount.phone}">
                                </div>
                                <div class="account__form-group checkbox">
                                    <input type="checkbox" id="is_change_pass" name="is_change_pass">
                                    <label for="is_change_pass">Đổi mật khẩu</label>
                                </div>
                                <div id="change-password">
                                    <div class="account__form-group">
                                        <label for="current_pass">Mật khẩu hiện tại</label>
                                        <input type="password" id="current_pass" name="current_pass" placeholder="Mật khẩu hiện tại" value="">
                                    </div>
                                    <div class="account__form-group">
                                        <label for="new_pass">Mật khẩu mới</label>
                                        <input type="password" id="new_pass" name="new_pass" placeholder="Mật khẩu mới" value="">
                                    </div>
                                    <div class="account__form-group">
                                        <label for="re_new_pass">Nhập lại mật khẩu mới</label>
                                        <input type="password" id="re_new_pass" name="re_new_pass" placeholder="Nhập lại mật khẩu mới" value="">
                                    </div>
                                </div>
                                <div class="account__form-group">
                                    <label>&nbsp;</label>
                                    <button type="button" onclick="editMyAccount('${base}');">Cập nhật</button>
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