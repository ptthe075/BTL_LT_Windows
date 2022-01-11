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
<title>Trang chủ</title>
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
				<div class="col c-12 block-container">
					<div class="row">
						<div class="col c-2 padding-right-0">
							<div class="menu box-shadow border-radius">
								<ul class="menu-list">
									${menu}
									<li class="menu-item">
										<a href="#" class="menu-item__link">
											<div class="menu-item__name">
												<i class="menu-item__img" style="background: url('${base}/assets/imgs/news.svg') no-repeat center / contain;"></i>
												Tin công nghệ
											</div>
										</a>
									</li>
									<li class="menu-item">
										<a href="#" class="menu-item__link">
											<div class="menu-item__name">
												<i class="menu-item__img" style="background: url('${base}/assets/imgs/promotion.svg') no-repeat center / contain;"></i>
												Khuyến mại
											</div>
										</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="col c-7 slide_bar">
							<div class="box-shadow border-radius overflow-hidden">
								<div class="slider-single_wrapper">
									<div class="prev" name-category="slider">
										<i class="fas fa-chevron-left"></i>
									</div>
									<div class="next" name-category="slider">
										<i class="fas fa-chevron-right"></i>
									</div>
									<div class="slider-single">
										<c:forEach var="slider" items="${sliders}">
											<div class="slide-single__item-slider">
												<img src="${base}/Upload/Sliders/${slider.image}" alt=""
													width="100%" height="100%" class="slide-show__img">
											</div>
										</c:forEach>
									</div>
								</div>
								<div class="slider-nav">
									<c:forEach var="slider" items="${sliders}">
										<div class="slider-nav__item">${slider.name}</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="col c-3 padding-left-0">
							<div class="banner-wrapper">
								<a href="#" class="banner-item border-radius"> <img
									src="${base}/assets/imgs/slides/banner1.webp"
									class="box-shadow border-radius" alt="" width="100%"
									height="100%">
								</a> <a href="#" class="banner-item border-radius"> <img
									src="${base}/assets/imgs/slides/banner2.webp"
									class="box-shadow border-radius" alt="" width="100%"
									height="100%">
								</a> <a href="#" class="banner-item border-radius"> <img
									src="${base}/assets/imgs/slides/banner3.webp"
									class="box-shadow border-radius" alt="" width="100%"
									height="100%">
								</a>
							</div>
						</div>
					</div>
				</div>

				<div class="col c-12 block-container">
					<div class="border-radius box-shadow">
						<div class="col c-4 box-title">
							<div class="box-title__title">
								<h2>Thương hiệu sản phẩm</h2>
							</div>
						</div>
						<div class="col c-12 box-list__wrapper">
							<div class="btn-action__prev" name-category="brand">
								<i class="fas fa-chevron-left"></i>
							</div>
							<div class="btn-action__next" name-category="brand">
								<i class="fas fa-chevron-right"></i>
							</div>
							<div class="row">
								<div class="col c-12 list-brand">
									<c:forEach var="brand" items="${brands}" varStatus="loop">
										<c:choose>
											<c:when test="${loop.index % 2 == 0}">
												<div class="item-brand">
													<a href="${brand.seo}" class="item-brand__link border-radius"> <img
														src="${base}/Upload/Brands/${brand.image}" width="100%" alt="">
													</a>
											</c:when>
											<c:otherwise>
													<a href="${brand.seo}" class="item-brand__link border-radius"> <img
														src="${base}/Upload/Brands/${brand.image}" width="100%" alt="">
													</a>
												</div>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${brands.size() % 2 != 0}"></div></c:if>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col c-12 block-container">
					<div class="border-radius box-shadow">
						<div class="col c-4 box-title">
							<a href="#" class="box-title__title">
								<h2>Điện thoại nổi bật</h2>
							</a>
						</div>
						<div class="col c-12 box-list__wrapper">
							<div class="btn-action__prev" name-category="phone">
								<i class="fas fa-chevron-left"></i>
							</div>
							<div class="btn-action__next" name-category="phone">
								<i class="fas fa-chevron-right"></i>
							</div>
							<div class="row">
								<div class="col c-12 list-product" name-category="phone">
									<c:forEach var="product" items="${products}" varStatus="loop">
										<div class="item-product border-radius border-1 margin-12-6">
											<c:if test="${not empty product.priceSale}">
												<div class="item-product__percent">
												<c:set var="discount" value="${(100 - (product.priceSale / product.price) * 100)}"/>
													<p>giảm  <fmt:formatNumber value="${discount}" maxFractionDigits="0" type="number"/>%</p>
												</div>
											</c:if>
											<div class="item-product__img">
												<a href="${base}/product/detail/${product.seo}"> <img
													src="${base}/Upload/Products/${product.avatar}" width="100%"
													alt="">
												</a>
											</div>
											<div class="item-product__name">
												<a href="${base}/product/detail/${product.seo}">${product.name}</a>
											</div>
											
											<div class="item-product__price">
												<c:choose>
													<c:when test="${empty product.priceSale}">
														<div class="special-price">
															<fmt:formatNumber value="${product.price}" pattern="###,### ₫" type="number"/>
														</div>
													</c:when>
													<c:otherwise>
														<div class="special-price">
															<fmt:formatNumber value="${product.priceSale}" pattern="###,### ₫" type="number"/>
														</div>
														<div class="old-price">
															<fmt:formatNumber value="${product.price}" pattern="###,### ₫" type="number"/>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="item-product__action">
												<div class="item-product__rating">
													<i class="fas fa-star start-checked"></i> <i
														class="fas fa-star start-checked"></i> <i
														class="fas fa-star start-checked"></i> <i
														class="fas fa-star start-checked"></i> <i
														class="fas fa-star start-checked"></i>
												</div>
												<a onclick="javascript:AddToCart('${base}', ${product.id}, 1);" class="item-product__cart"> <i
													class="fas fa-cart-plus"></i>
												</a>
											</div>
										</div>
									</c:forEach>
									
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
	<script src="${base}/assets/js/product.js"></script>
	<script src="${base}/assets/js/script.js"></script>
</body>

</html>