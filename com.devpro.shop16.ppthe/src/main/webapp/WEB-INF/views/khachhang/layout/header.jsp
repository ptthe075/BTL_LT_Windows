<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<header class="header">
	<nav class="grid header__wrapper">
		<div class="header__content">
			<div class="header__content-left">
				<div class="header__container-nav">
					<div class="header__container-nav-icon">
						<i class="fas fa-bars"></i>
					</div>
					<div class="menu header__container-menu box-shadow">
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
				<a href="${base}/" class="header__container-logo"> <img
					src="${base}/assets/imgs/logo.png" alt="Logo" width="100%">
				</a>
			</div>
			<div class="header__content-right">
				<div class="row align-items-center">
					<div class="col xl-7 lg-10 md-0 c-0 header__container">
						<div class="header__container-search border-radius">
							<input type="text" name="" id="input-search"
								placeholder="Bạn cần tìm gì?">
							<button type="button" class="header__icon-search border-radius">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</div>
					<div class="col xl-5 lg-2 md-12 c-12 header__container">
						<ul class="header__container-about">
							<li class="header__container-item header__item-search"><a
								href="#" class="header__container-link" id="icon-search"> <i
									class="fas fa-search header__item-icon"></i>
							</a>
								<div class="border-radius hidden" id="search">
									<a href="#" id="close-search"> <i class="fas fa-times"></i>
									</a> <input type="text" name="" id="input-search"
										placeholder="Bạn cần tìm gì?">
									<button type="button" class="header__icon-search border-radius">
										<i class="fas fa-search"></i>
									</button>
								</div></li>
							<li
								class="header__container-item border-radius header__container-hiden">
								<a href="/contact" class="header__container-link"> <i
									class="fas fa-phone-alt header__item-icon"></i> <span
									class="header__item-name">Liên hệ</span>
							</a>
							</li>
							<li class="header__container-item border-radius"><a href="${base}/cart"
								class="header__container-link cart"> <i
									class="fas fa-cart-plus header__item-icon"></i> <span
									class="header__item-name">Giỏ hàng</span>
									<span id="iconShowTotalItemsInCart">${totalItems}</span>
									</a>
									
								<!--<p class="box-shadow"></p>
								<div id="cart-price" class="header__hover-wrapper border-radius box-shadow cart">
									<ul class="header__hover-list">
										<c:forEach var="ci" items="${cart.cartItems}">
											<li class="header__hover-item">
	                                        	<div class="cart-price__item">
		                                            <img class="cart-price__img" src="${base}/Upload/Products/${ci.productAvatar}" width="100%" height="100%" alt="">
		                                            <div class="cart-price__name">${ci.productName}</div>
		                                            <div class="cart-price__price">
		                                                <span class="quantity" quantity="1">${ci.quantity}</span>
		                                                <span class="price" price="123000000">${ci.priceUnit}</span>
		                                            </div>
	                                            </div>
	                                        </li>
										</c:forEach>
									</ul>
									<div class="cart-price__total">
										<span>Tổng tiền: </span> <span id="header__price-total">
											85.733.000đ </span>
									</div>
									<div class="cart-price__url">
										<p>
											Bạn có <span class="cart-price__sum">2</span> sản phẩm trong
											giỏ hàng
										</p>
										<a href="#">Xem giỏ hàng</a>
									</div>
								</div>-->
							</li>
							<li class="header__container-item border-radius header__container-hiden">
								<c:choose>
									<c:when test="${isLogined}">
										<a href="#" class="header__container-link">
											<i class="fas fa-user-circle header__item-icon"></i>
											<span class="header__item-name"><div>Xin chào,</div>${userLogined.name}</span>
										</a>
										<p class="box-shadow"></p>
										<div class="header__hover-wrapper border-radius box-shadow account">
											<ul class="header__hover-list">
												<li class="header__hover-item">
													<a href="" class="header__hover-link"> <i class="fas fa-user header__hover-icon"></i>Tài khoản</a>
												</li>
												<li class="header__hover-item">
													<a href="" class="header__hover-link"> <i class="fas fa-list-alt header__hover-icon"></i>Đơn hàng</a>
												</li>
												<c:if test="${isAdmin}">
													<li class="header__hover-item">
														<a href="${base}/admin" class="header__hover-link"> <i class="fas fa-user-shield header__hover-icon"></i>Trang admin</a>
													</li>
												</c:if>
												<li class="header__hover-item">
													<a href="${base}/logout" class="header__hover-link"> <i class="fas fa-sign-out-alt header__hover-icon"></i>Đăng xuất</a>
												</li>
											</ul>
										</div>
									</c:when>
									<c:otherwise>
										<a href="/login" class="header__container-link">
											<i class="fas fa-user-circle header__item-icon"></i>
											<span class="header__item-name">Đăng nhập</span>
										</a>
									</c:otherwise>
								</c:choose>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>
</header>