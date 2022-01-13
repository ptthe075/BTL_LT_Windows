<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Chi tiết sản phẩm</title>
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
				<li class="breadcrumb-item"><a href="#" class="breadcrumb-link">
						<i class="fas fa-home breadcrumb-home"></i> Trang chủ
				</a> <i class="fas fa-angle-right"></i></li>
				<li class="breadcrumb-item"><a href="#" class="breadcrumb-link">
						Điện thoại </a> <i class="fas fa-angle-right"></i></li>
				<li class="breadcrumb-item"><a href="#" class="breadcrumb-link">
						Apple </a> <i class="fas fa-angle-right"></i></li>
				<li class="breadcrumb-item"><a href="#" class="breadcrumb-link">
						iPhone 13 Series </a> <i class="fas fa-angle-right"></i></li>
				<li class="breadcrumb-item">iPhone 13 Pro | Chính hãng VN/A</li>
			</ul>
		</div>
	</div>

	<div class="container-wrapper padding-top-0">
		<div class="block-product-detail box-shadow">
			<div class="product-detail__box">
				<div class="grid">
					<div class="row">
						<div class="col c-4">
							<div
								class="slider-single_wrapper border-radius border-1 overflow-hidden">
								<div class="prev" name="images-product">
									<i class="fas fa-chevron-left"></i>
								</div>
								<div class="next" name="images-product">
									<i class="fas fa-chevron-right"></i>
								</div>
								<div class="product__img-single">
									<div class="img-single__item-slider">
										<img src="${base}/Upload/Products/${product.avatar}" alt=""
											width="100%" height="100%" class="slide-show__img">
									</div>
									<c:forEach var="image" items="${productImages}">
										<div class="img-single__item-slider">
											<img src="${base}/Upload/Products/${image.path}" alt=""
												width="100%" height="100%" class="slide-show__img">
										</div>
									</c:forEach>
								</div>
							</div>
							<div class="product__img-nav">
								<div class="img-nav__item">
									<img src="${base}/Upload/Products/${product.avatar}" alt=""
										width="100%" height="100%" class="slide-show__img">
								</div>
								<c:forEach var="image" items="${productImages}">
									<div class="img-nav__item">
										<img src="${base}/Upload/Products/${image.path}" alt=""
											width="100%" height="100%" class="slide-show__img">
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="col c-4">
							<div class="product-detail__box-info">
								<div class="product-detail__name">
									<h1>${product.name }</h1>
								</div>
								<div class="box-info__options-content">
									<h3>Mô tả</h3>
									<div> ${product.shortDes } </div>
								</div>
								<div class="box-promotion border-radius">
									<div class="box-promotion__title">
										<i class="fas fa-gift"></i> Khuyến mãi
									</div>
									<div class="box-promotion__content">
										<ol class="box-promotion__list-promotions">
											<li class="box-promotion__item-promotion">Giảm 1 triệu
												khi thanh toán qua ví Moca, thẻ tín dụng ACB, BIDV,
												Sacombank, mPOS, Shinhan, Standard Charter (số lượng có hạn)
												<span class="color-red">(xem chi tiết)</span>
											</li>
											<li class="box-promotion__item-promotion">Ưu đãi giảm
												30% gói Bảo hành Apple Care+ <span class="color-red">(xem
													chi tiết)</span>
											</li>
										</ol>
									</div>
								</div>
								<div class="box-info__price">
									<c:choose>
										<c:when test="${empty product.priceSale}">
											<div class="special-price"><fmt:formatNumber value="${product.price}" pattern="###,### ₫" type="number"/></div>
										</c:when>
										<c:otherwise>
											<div class="special-price"><fmt:formatNumber value="${product.priceSale}" pattern="###,### ₫" type="number"/></div>
											<div class="old-price"><fmt:formatNumber value="${product.price}" pattern="###,### ₫" type="number"/></div>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="box-quantity">
									<p class="box-quantity__title">Số lượng:</p>
									<div class="box-quantity__buy border-1">
										<a href="" class="box-quantity__change disable"
											data-value="-1"><i class="fas fa-minus"></i></a> <input
											type="number" class="box-quantity__input" value="1">
										<a href="" class="box-quantity__change" data-value="1"><i
											class="fas fa-plus"></i></a>
									</div>
								</div>
								<div class="box-action-button">
									<div class="row">
										<div class="col c-12 margin-bottom-10">
											<a href="#" class="action-button__buy border-radius">
												<div class="action-button__name">Đặt mua ngay</div>
												<div class="action-button__desc">Giao hàng tận nơi
													nhanh chóng, thuận tiện</div>
											</a>
										</div>
										<div class="col c-6 margin-bottom-10 padding-right-6">
											<a class="action-button__item border-radius">
												<div class="action-button__name">Thêm giỏ hàng</div>
												<div class="action-button__desc">Thêm vào giỏ để chọn
													tiếp</div>
											</a>
										</div>
										<div class="col c-6 margin-bottom-10 padding-left-6">
											<a href="#" class="action-button__item border-radius">
												<div class="action-button__name">Trả góp</div>
												<div class="action-button__desc">Trả góp chỉ từ 0%</div>
											</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col c-4">
							<div class="box-promotion-more border-radius">
								<div class="box-promotion__title">ƯU ĐÃI THÊM</div>
								<div class="box-promotion__content">
									<ol class="box-promotion__list-promotions">
										<li class="box-promotion__item-promotion">Giảm thêm tới
											1% cho thành viên Smember</li>
										<li class="box-promotion__item-promotion">Giảm 5% tối đa
											500k khi thanh toán bằng ví Moca trên ứng dụng Grab (áp dụng
											đơn hàng từ 500k)</li>
										<li class="box-promotion__item-promotion">Giảm 500.000đ
											khi thanh toán hoặc trả góp từ 5 triệu trở lên bằng thẻ tín
											dụng FE Credit</li>
									</ol>
								</div>
							</div>
							<div class="box-promotion-more border-radius">
								<div class="box-promotion__title">Thông tin máy</div>
								<div class="box-promotion__content">
									<ol class="box-promotion__list-promotions">
										<li class="box-promotion__item-promotion">Thân máy, cáp
											sạc, sách HDSH</li>
										<li class="box-promotion__item-promotion">Bảo hành chính
											hãng 12 tháng tại trung tâm bảo hành ủy quyền, 1 đổi 1 trong
											30 ngày nếu có lỗi phần cứng từ NSX. Gia hạn bảo hành thời
											gian giãn cách <span class="color-red">(xem chi tiết)</span>
										</li>
									</ol>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="grid">
			<div class="row">
				<div class="col c-12 block-container">
					<div class="border-radius box-shadow">
						<div class="col c-4 box-title">
							<a class="box-title__title text-decoration-none">
								<h2>Sản phẩm tương tự</h2>
							</a>
						</div>
						<div class="col c-12 box-list__wrapper">
							<div class="btn-action__prev" name-category="similar">
								<i class="fas fa-chevron-left"></i>
							</div>
							<div class="btn-action__next" name-category="similar">
								<i class="fas fa-chevron-right"></i>
							</div>
							<div class="row">
								<div class="col c-12 list-product" name-category="similar">
									<!-- <div class="item-product border-radius border-1 margin-12-6">
										<div class="item-product__percent">
											<p>giảm 22%</p>
										</div>
										<div class="item-product__img">
											<a href="#"> <img
												src="${base}/assets/imgs/products/product1.jpg" width="100%"
												alt="">
											</a>
										</div>
										<div class="item-product__name">
											<a href="#">iPhone 13 Pro Max iPhone 13 Pro Max iPhone 13
												Pro Max iPhone 13 Pro Max</a>
										</div>
										<div class="item-product__price">
											<div class="special-price">35.990.000 ₫</div>
											<div class="old-price">36.990.000 ₫</div>
										</div>
										<div class="item-product__action">
											<div class="item-product__rating">
												<i class="fas fa-star start-checked"></i> <i
													class="fas fa-star start-checked"></i> <i
													class="fas fa-star start-checked"></i> <i
													class="fas fa-star start-checked"></i> <i
													class="fas fa-star start-checked"></i>
											</div>
											<a href="" class="item-product__cart"> <i
												class="fas fa-cart-plus"></i>
											</a>
										</div>
									</div> -->
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col c-8 block-container padding-right-6">
					<div class="border-radius box-shadow padding-10 overflow-hidden">
						<h2 class="product__detail-title" style="text-align: center;">Mô tả sản phẩm</h2>
						${product.details}
					</div>
				</div>
				<div class="col c-4 block-container" id="specifications">
					<div class="border-radius box-shadow padding-10">
						<h2 class="product__detail-title">Thông số kỹ thuật</h2>
						${product.specifications}
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/khachhang/layout/footer.jsp"></jsp:include>

	<script src="${base}/assets/lib/jquery/jquery.min.js"></script>
	<script src="${base}/assets/lib/slick/js/slick.min.js"></script>
	<script src="${base}/assets/lib/slick/js/slick.script.js"></script>
	<script src="${base}/assets/js/product.js"></script>
	<script src="${base}/assets/js/script.js"></script>
</body>

</html>