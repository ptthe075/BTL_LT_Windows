<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đăng nhập tài khoản</title>

<link rel="stylesheet" href="${base}/assets/css/google.font.css">
<link rel="stylesheet" href="${base}/assets/fonts/css/all.min.css">
<link rel="stylesheet" href="${base}/assets/css/login.style.css">
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
	<div class="container">
		<input type="checkbox" id="flip">
		<div class="cover">
			<div class="front">
				<img src="${base}/assets/imgs/log.svg" alt="">
			</div>
			<div class="back">
				<img class="backImg" src="${base}/assets/imgs/register.svg" alt="">
			</div>
		</div>
		<div class="forms">
			<div class="form-content">
				<div class="login-form">
					<div class="title">Đăng nhập</div>
					<form method="POST" action="${base}/perform_login">
						<div class="input-boxes">
							<div class="input-box">
								<i class="fas fa-user"></i> <input type="text" name="username"
									placeholder="Tên tài khoản" required>
							</div>
							<div class="input-box">
								<i class="fas fa-lock"></i> <input type="password"
									name="password" placeholder="Mật khẩu" required>
							</div>
							<c:if test="${not empty param.login_error}">
								<div class="input-box alert-danger" role="alert">Đăng nhập
									không thành công. Vui lòng thử lại !!!</div>
							</c:if>
							<div class="text">
								<a onclick="forgotPassword('${base}');">Quên mật khẩu?</a>
							</div>
							<div class="button input-box">
								<button type="submit">Đăng nhập</button>
							</div>
							<p class="social-text">
								<span>Hoặc</span>
							</p>
							<div class="social-media">
								<a href="#" class="social-icon"> <i
									class="fab fa-facebook-f"></i>
								</a> <a href="#" class="social-icon"> <i class="fab fa-google"></i>
								</a>
							</div>
							<div class="text sign-up-text">
								Bạn chưa có tài khoản? <label for="flip">Đăng ký ngay</label>
							</div>
						</div>
					</form>
				</div>
				<div class="signup-form">
					<div class="title">Đăng ký tài khoản</div>
					<form action="${base}/signup" method="post">
						<div class="input-boxes">
							<div class="input-box">
								<i class="fas fa-user"></i> <input type="text" id="username"
									placeholder="Tên tài khoản" required>
							</div>
							<div class="input-box">
								<i class="fas fa-user-tie"></i> <input type="text" id="name"
									placeholder="Họ tên" required>
							</div>
							<div class="input-box">
								<i class="fas fa-envelope"></i> <input type="text" id="email"
									placeholder="Email" required>
							</div>
							<div class="input-box">
								<i class="fas fa-lock"></i> <input type="password" id="password"
									placeholder="Mật khẩu" required>
							</div>
							<div class="input-box">
								<i class="fas fa-lock"></i> <input type="password"
									id="password_confirm" placeholder="Mật khẩu xác nhận" required>
							</div>
							<div class="button input-box">
								<button type="button" onclick="signup('${base}');">Đăng
									ký</button>
							</div>
							<p class="social-text">
								<span>Hoặc</span>
							</p>
							<div class="social-media">
								<a href="#" class="social-icon"> <i
									class="fab fa-facebook-f"></i>
								</a> <a href="#" class="social-icon"> <i class="fab fa-google"></i>
								</a>
							</div>
							<div class="text sign-up-text">
								Bạn đã có tài khoản? <label for="flip">Đăng nhập ngay</label>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="${base}/assets/lib/jquery/jquery.min.js"></script>
	<script src="${base}/assets/lib/sweetalert/sweetalert2.all.min.js"></script>
	<script src="${base}/assets/js/login.script.js"></script>
</body>

</html>