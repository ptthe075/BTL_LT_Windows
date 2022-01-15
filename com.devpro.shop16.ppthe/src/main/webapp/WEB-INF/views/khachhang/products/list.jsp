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
    <title>${title}</title>
	<jsp:include page="/WEB-INF/views/khachhang/layout/css.jsp"></jsp:include>
    <link rel="stylesheet" href="${base}/assets/lib/slick/css/slick.style.css">
    <link rel="stylesheet" href="${base}/assets/css/product.css">
    <link rel="stylesheet" href="${base}/assets/css/style.css">
    <link rel="stylesheet" href="${base}/assets/css/reponsive.css">
</head>

<body>
    <!-- Common -->
	<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/khachhang/layout/header.jsp"></jsp:include>

    ${breadcrumb}

    <div class="container-wrapper">
        <div class="grid">
            <div class="row">
                <div class="col c-12 block-container">
                    <div class="row">
                        <div class="col c-6 padding-slider__category">
                            <div class="box-shadow border-radius overflow-hidden">
                                <div class="slider-single_wrapper">
                                    <div class="prev slider-category" name-category="slider-left"><i class="fas fa-chevron-left"></i></div>
                                    <div class="next slider-category" name-category="slider-left"><i class="fas fa-chevron-right"></i></div>
                                    <div class="slider-single-left">
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide1.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide2.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide3.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide4.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide5.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide1.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide2.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide3.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col c-6 padding-slider__category">
                            <div class="box-shadow border-radius overflow-hidden">
                                <div class="slider-single_wrapper">
                                    <div class="prev slider-category" name-category="slider-right"><i class="fas fa-chevron-left"></i></div>
                                    <div class="next slider-category" name-category="slider-right"><i class="fas fa-chevron-right"></i></div>
                                    <div class="slider-single-right">
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide1.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide2.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide3.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide4.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide5.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide1.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide2.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                        <div class="slide-single__item">
                                            <img src="${base}/assets/imgs/slides/slide3.webp" alt="" width="100%" height="100%" class="slide-show__img">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col c-12 block-container">
                    <div class="row">
                        <div class="col c-12 block-filter__filter filtering-by">
                            <div class="block-filter__title">
                                Đang lọc theo
                            </div>
                            <div class="block-filter__list-filter">
                                <div class="btn-filter active__filter border-radius">
                                    Apple
                                    <i class="fas fa-times-circle btn-filter__icon"></i>
                                </div>
                                <div class="btn-filter sort active__filter border-radius">
                                    <i class="fas fa-times"></i> Bỏ chọn tất cả
                                </div>
                            </div>
                        </div>
                        <div class="col c-12 block-filter__filter">
                            <div class="block-filter__title">
                                Chọn theo tiêu chí
                            </div>
                            <div class="block-filter__list-filter">
                                <div class="btn-filter active__filter border-radius">
                                    Danh mục
                                    <i class="fas fa-angle-down btn-filter__icon"></i>
                                </div>
                                <div class="btn-filter border-radius">
                                    Thương hiệu
                                    <i class="fas fa-angle-down btn-filter__icon"></i>
                                </div>
                                <div class="btn-filter border-radius">
                                    Khoảng giá
                                    <i class="fas fa-angle-down btn-filter__icon"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col c-12 block-filter__sort">
                            <div class="block-filter__title">
                                Sắp xếp theo
                            </div>
                            <div class="block-filter__list-filter">
                                <div class="btn-filter sort active__filter border-radius">
                                    <i class="fas fa-sort-amount-up"></i>Giá cao
                                </div>
                                <div class="btn-filter sort border-radius">
                                    <i class="fas fa-sort-amount-up-alt"></i>Giá thấp
                                </div>
                                <div class="btn-filter sort border-radius">
                                    <i class="fas fa-percent"></i>Khuyễn mại hot
                                </div>
                                <div class="btn-filter sort border-radius">
                                    <i class="fas fa-eye btn-filter__icon"></i>Xem nhiều
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col c-12 block-container margin-bottom-0">
                    <div class="row margin-right-0">
                    	<c:forEach var="product" items="${listProduct}">
                        	<div class="col col-5 padding-right-0">
								<div class="item-product border-radius box-shadow">
									<c:if test="${not empty product.priceSale}">
										<div class="item-product__percent">
											<c:set var="discount" value="${(100 - (product.priceSale / product.price) * 100)}"/>
											<p>giảm  <fmt:formatNumber value="${discount}" maxFractionDigits="0" type="number"/>%</p>
										</div>
									</c:if>
									<div class="item-product__img">
										<a href="${base}/product/detail/${product.seo}"> <img
										src="${base}/Upload/Products/${product.avatar}" width="100%" alt="">
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
										<div class="item-product__rating"> </div>
										<a onclick="javascript:AddToCart('${base}', ${product.id}, 1);" class="item-product__cart"> <i
											class="fas fa-cart-plus"></i>
										</a>
									</div>
								</div>
	                        </div>
						</c:forEach>
                    </div>
                </div>

                <!-- <div class="col c-12 block-container block-paging">
                    <a href="" class="block-paging-item border-1 paging-active">
                        <i class="fas fa-chevron-left"></i>
                        <i class="fas fa-chevron-left"></i>
                    </a>
                    <a href="" class="block-paging-item border-1">
                        <i class="fas fa-chevron-left"></i>
                    </a>
                    <a href="" class="block-paging-item border-1">1</a>
                    <a href="" class="block-paging-item border-1">...</a>
                    <a href="" class="block-paging-item border-1">10</a>
                    <a href="" class="block-paging-item border-1">
                        <i class="fas fa-chevron-right"></i>
                    </a>
                    <a href="" class="block-paging-item border-1">
                        <i class="fas fa-chevron-right"></i>
                        <i class="fas fa-chevron-right"></i>
                    </a>
                </div> -->
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