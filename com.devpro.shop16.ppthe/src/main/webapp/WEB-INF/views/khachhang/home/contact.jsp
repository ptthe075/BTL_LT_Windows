<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- import SPRING-FORM -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Layout</title>
<jsp:include page="/WEB-INF/views/khachhang/layout/css.jsp"></jsp:include>
<link rel="stylesheet" href="${base}/assets/css/style.css">
<link rel="stylesheet" href="${base}/assets/css/contact.css">
<link rel="stylesheet" href="${base}/assets/css/reponsive.css">
</head>

<body>
	<jsp:include page="/WEB-INF/views/khachhang/layout/header.jsp"></jsp:include>

	<div class="block-breadcrumbs box-shadow">
		<div class="grid breadcrumbs">
			<ul class="breadcrumb-list">
				<li class="breadcrumb-item"><a href="${base}/" class="breadcrumb-link">
						<i class="fas fa-home breadcrumb-home"></i> Trang chủ
				</a> <i class="fas fa-angle-right"></i></li>
				<li class="breadcrumb-item">Liên hệ</li>
			</ul>
		</div>
	</div>

	<div class="container-wrapper">
		<div class="grid">
			<div class="row">
				<div class="col c-12 contact-form__wrapper border-radius box-shadow">
					<div class="contact-info">
						<c:if test="${not empty TB }">
							<div
								style="background: var(--background-color); padding: 10px; color: white">${TB }</div>
						</c:if>
						<h3 class="title">Liên hệ với chúng tôi</h3>
						<div class="info">
							<div class="information">
								<i class="fas fa-map-marker-alt icon-contact"></i> <span>Số
									xx Cầu Giấy, Nam Từ Liêm, Hà Nội</span>
							</div>
							<div class="information">
								<i class="far fa-envelope icon-contact"></i> <span>cellphone@gmail.com</span>
							</div>
							<div class="information">
								<i class="fas fa-phone-volume icon-contact"></i> <span>0123456789
									- 0987654321</span>
							</div>
						</div>
						<div class="social-media">
							<p>Kết nối</p>
							<div class="social-icons">
								<a href="#"> <i class="fab fa-facebook-f"></i>
								</a> <a href="#"> <i class="fab fa-twitter"></i>
								</a> <a href="#"> <i class="fab fa-instagram"></i>
								</a> <a href="#"> <i class="fab fa-linkedin-in"></i>
								</a>
							</div>
						</div>
					</div>

					<div class="contact-form">
						<sf:form modelAttribute="contact" method="post"
							action="${base}/contact" autocomplete="off">
							<h3 class="title">Contact us</h3>
							<div class="input-container">
								<sf:input path="fullName" type="text" name="name"
									class="contact__input" required="required"/>
								<label for="">Họ tên</label> <span>Họ tên</span>
							</div>
							<div class="input-container">
								<sf:input path="email" type="email" name="email"
									class="contact__input" required="required"/>
								<label for="">Email</label> <span>Email</span>
							</div>
							<div class="input-container textarea">
								<sf:textarea path="message" rows="6" name="message"
									class="contact__input" required="required"/>
								<label for="">Nội dung</label> <span>Nội dung</span>
							</div>
							<input type="submit" value="Gửi" class="btn-contact" />
						</sf:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/khachhang/layout/footer.jsp"></jsp:include>

	<script src="${base}/assets/lib/jquery/jquery.min.js"></script>
	<script src="${base}/assets/js/script.js"></script>
</body>

</html>