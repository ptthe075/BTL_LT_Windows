<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<footer class="footer box-shadow">
	<div class="footer__subscribe">
		<div class="grid">
			<div class="row">
				<div class="col c-12 footer__subscribe-title">ĐĂNG KÝ NHẬN
					EMAIL THÔNG BÁO KHUYẾN MẠI HOẶC ĐỂ ĐƯỢC TƯ VẤN MIỄN PHÍ</div>
				<form action="${base }/subcrible" method="post"
					class="col  md-6 md-o-3 c-8 c-o-2 footer__subscribe-form border-radius">
					<input type="text" id="emailSubscribe"
						placeholder="Nhập email để nhận tin khuyến mãi">
					<button type="button" class="footer__subscribe-btn" onclick="subscribe('${base}');">Gửi</button>
				</form>
			</div>
		</div>
	</div>
	<div class="grid">
		<div class="row footer__about">
			<div class="col lg-6 c-12 footer__menu">
				<div class="row">
					<div class="col c-6 footer__menu-info">
						<a class="footer__menu-title">Về Cellphone</a>
						<ul class="footer__menu-list">
							<c:forEach var="info" items="${infomations }">
								<li class="footer__menu-item">
									<a href="/about/${info.seo }" class="footer__menu-link">${info.name }</a>
								</li>
							</c:forEach>
							<li class="footer__menu-item">
								<a href="#" class="footer__menu-link">Tin công nghệ</a>
							</li>
							<li class="footer__menu-item">
								<a href="${base }/contact" class="footer__menu-link">Liên hệ</a>
							</li>
						</ul>
					</div>
					<div class="col c-6 footer__menu-info">
						<div class="footer__menu-title">Sản phẩm</div>
						<ul class="footer__menu-list">
							<c:forEach var="category" items="${categoryParents }">
								<li class="footer__menu-item">
									<a href="${base}/category/${category.seo}" class="footer__menu-link">${category.name }</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="col lg-6 c12 footer__contact">
				<div class="footer__contact-address">
					<div class="footer__menu-title">Hệ thống cửa hàng</div>
					<ul class="footer__menu-list">
						<li class="footer__menu-item">Địa chỉ mua hàng: Số xx Cầu
							Giấy, Nam Từ Liêm, Hà Nội</li>
						<li class="footer__menu-item">Hotline: 0123456789 -
							0987654321</li>
					</ul>
				</div>
				<div class="footer__contact-payment">
					<div class="footer__menu-title">Phương thức thanh toán</div>
					<div class="footer__payment-list">
						<ul class="payment-list">
							<li class="payment-item"><a href="#" class="payment-link">
									<img src="${base}/assets/imgs/payments/pay1.png" width="100%"
									alt="">
							</a></li>
							<li class="payment-item"><a href="#" class="payment-link">
									<img src="${base}/assets/imgs/payments/pay2.png" width="100%"
									alt="">
							</a></li>
							<li class="payment-item"><a href="#" class="payment-link">
									<img src="${base}/assets/imgs/payments/pay3.png" width="100%"
									alt="">
							</a></li>
							<li class="payment-item"><a href="#" class="payment-link">
									<img src="${base}/assets/imgs/payments/pay4.png" width="100%"
									alt="">
							</a></li>
						</ul>
						<ul class="payment-list">
							<li class="payment-item"><a href="#" class="payment-link">
									<img src="${base}/assets/imgs/payments/pay5.png" width="100%"
									alt="">
							</a></li>
							<li class="payment-item"><a href="#" class="payment-link">
									<img src="${base}/assets/imgs/payments/pay6.png" width="100%"
									alt="">
							</a></li>
							<li class="payment-item"><a href="#" class="payment-link">
									<img src="${base}/assets/imgs/payments/pay7.png" width="100%"
									alt="">
							</a></li>
							<li class="payment-item"><a href="#" class="payment-link">
									<img src="${base}/assets/imgs/payments/pay8.png" width="100%"
									alt="">
							</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="footer__copyright">
			<i class="far fa-copyright"></i> Cellphone. All Rights Reserved
		</div>
	</div>
</footer>