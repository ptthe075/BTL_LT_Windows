<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="${base}/" class="brand-link"> <img
		src="${base}/areas/admin/dist/img/AdminLTELogo.png" alt="AdminLTE Logo"
		class="brand-image img-circle elevation-3" style="opacity: .8">
		<span class="brand-text font-weight-light">Cellphone</span>
	</a>

	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image">
				<img src="${base}/areas/admin/dist/img/avatar5.png"
					class="img-circle elevation-2" alt="User Image">
			</div>
			<div class="info">
				<a href="#" class="d-block">${userLogined.name}</a>
			</div>
		</div>

		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
				<li class="nav-item"><a href="${base}/admin" class="nav-link"
					name-item="home"> <i class="nav-icon fas fa-home"></i>
						<p>Trang chủ</p>
				</a></li>
				<li class="nav-item"><a href="${base}/admin/categories" class="nav-link"
					name-item="categories"> <i class="nav-icon fas fa-sitemap"></i>
						<p>Danh mục</p>
				</a></li>
				<li class="nav-item"><a href="${base}/admin/brands" class="nav-link"
					name-item="brands"><i class="nav-icon fas fa-code-branch"></i>
						<p>Thương hiệu</p>
				</a></li>
				<li class="nav-item"><a href="${base}/admin/products" class="nav-link"
					name-item="products"> <i class="nav-icon fab fa-product-hunt"></i>
						<p>Sản phẩm</p>
				</a></li>
				<li class="nav-item"><a href="${base}/admin/orders" class="nav-link"
					name-item="orders"> <i class="nav-icon fas fa-shopping-cart"></i>
						<p>Đơn hàng</p>
				</a></li>
				<li class="nav-item"><a href="${base}/admin/accounts" class="nav-link"
					name-item="accounts"> <i class="nav-icon fas fa-users"></i>
						<p>Tài khoản</p>
				</a></li>
				<li class="nav-item"><a href="${base}/admin/contacts" class="nav-link"
					name-item="contacts"> <i class="nav-icon far fa-address-book"></i>
						<p>
							Liên hệ <span class="badge badge-info right">6</span>
						</p>
				</a></li>
				<li class="nav-item"><a href="${base}/admin/sliders" class="nav-link"
					name-item="sliders"> <i class="nav-icon fas fa-sliders-h"></i>
						<p>Slider</p>
				</a></li>
				<li class="nav-item"><a href="${base}/admin/info" class="nav-link"
					name-item="info"> <i class="nav-icon fas fa-info-circle"></i>
						<p>Thông tin</p>
				</a></li>
			</ul>
		</nav>
		<!-- /.sidebar-menu -->
	</div>
	<!-- /.sidebar -->
</aside>