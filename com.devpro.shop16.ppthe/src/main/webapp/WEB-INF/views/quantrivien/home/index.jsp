<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cellphone</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${base}/areas/admin/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${base}/areas/admin/dist/css/adminlte.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="${base}/areas/admin/css/style.css">
</head>

<body class="sidebar-mini layout-fixed sidebar-collapse">
	<div class="wrapper">
		<!-- Navbar -->
		<jsp:include page="/WEB-INF/views/quantrivien/layout/navbar.jsp"></jsp:include>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<jsp:include page="/WEB-INF/views/quantrivien/layout/sidebar.jsp"></jsp:include>
		<!-- /.Main Sidebar Container -->

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Trang chủ</h1>
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<!-- Small boxes (Stat box) -->
				<div class="row">
					<div class="col-lg-3 col-6">
						<!-- small box -->
						<div class="small-box bg-info">
							<div class="inner">
								<h3>${countProduct }</h3>
								<p>Sản phẩm</p>
							</div>
							<div class="icon">
								<i class="ion ion-bag"></i>
							</div>
							<a href="${base}/admin/products" class="small-box-footer">Xem chi tiết <i
								class="fas fa-arrow-circle-right"></i></a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-6">
						<!-- small box -->
						<div class="small-box bg-success">
							<div class="inner">
								<h3>${countOrder }</h3>
								<p>Đơn hàng</p>
							</div>
							<div class="icon">
								<i class="ion ion-stats-bars"></i>
							</div>
							<a href="${base}/admin/orders" class="small-box-footer">Xem chi tiết <i
								class="fas fa-arrow-circle-right"></i></a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-6">
						<!-- small box -->
						<div class="small-box bg-warning">
							<div class="inner">
								<h3>${countUser }</h3>
								<p>Tài khoản</p>
							</div>
							<div class="icon">
								<i class="ion ion-person-add"></i>
							</div>
							<a href="${base}/admin/accounts" class="small-box-footer">Xem chi tiết <i
								class="fas fa-arrow-circle-right"></i></a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-6">
						<!-- small box -->
						<div class="small-box bg-danger">
							<div class="inner">
								<h3><fmt:formatNumber value="${totalOrder }" pattern="###,### ₫" type="number"/></td></h3>
								<p>Tổng tiền</p>
							</div>
							<div class="icon">
								<i class="ion ion-pie-graph"></i>
							</div>
							<a href="${base}/admin/orders" class="small-box-footer">Xem chi tiết <i
								class="fas fa-arrow-circle-right"></i></a>
						</div>
					</div>
					<!-- ./col -->
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<footer class="main-footer">
			<strong>Copyright &copy; 2021 <a href="">Cellphone</a>.
			</strong> All rights reserved.
		</footer>
		<div id="select-nav" name-item="home" style="display: none;"></div>
	</div>
	<!-- ./wrapper -->

	<!-- jQuery -->
	<script src="${base}/areas/admin/plugins/jquery/jquery.min.js "></script>
	<!-- Bootstrap 4 -->
	<script
		src="${base}/areas/admin/plugins/bootstrap/js/bootstrap.bundle.min.js "></script>
	<!-- AdminLTE App -->
	<script src="${base}/areas/admin/dist/js/adminlte.js "></script>
	<!-- Custom JS -->
	<script src="${base}/areas/admin/js/script.js"></script>
</body>

</html>