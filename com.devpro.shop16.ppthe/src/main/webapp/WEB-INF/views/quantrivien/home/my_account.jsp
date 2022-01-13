<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- SPRING FORM -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>My account</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${base}/areas/admin/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${base}/areas/admin/dist/css/adminlte.min.css">
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
							<h1 class="m-0">Tài khoản của tôi</h1>
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<
			<section class="content">
				<div class="container-fluid">
					<div class="card">
						<div class="card-header p-2">
							<ul class="nav nav-pills">
								<li class="nav-item"><a class="nav-link active"
									href="#info" data-toggle="tab">Thông tin</a></li>
								<li class="nav-item"><a class="nav-link"
									href="#changePassword" data-toggle="tab">Đổi mật khẩu</a></li>
							</ul>
						</div>
						<!-- /.card-header -->
						<div class="card-body">
							<div class="tab-content">
								<div class="active tab-pane fade show active" id="info">
									<sf:form method="post" action="${base}/admin/my-account" modelAttribute="user" class="form-horizontal">
										<sf:hidden path="id" />
										<div class="form-group row">
											<label for="username" class="col-sm-2 col-form-label">Tên tài khoản</label>
											<div class="col-sm-6">
												<sf:input path="username" type="text" class="form-control" id="username" readonly="true"/>
											</div>
										</div>
										<div class="form-group row">
											<label for="name" class="col-sm-2 col-form-label">Họ và tên</label>
											<div class="col-sm-6">
												<sf:input path="name" type="text" class="form-control" id="name"/>
											</div>
										</div>
										<div class="form-group row">
											<label for="email" class="col-sm-2 col-form-label">Email</label>
											<div class="col-sm-6">
												<sf:input path="email" type="email" class="form-control" id="email"/>
											</div>
										</div>
										<div class="form-group row">
											<label for="phone" class="col-sm-2 col-form-label">Số điện thoại</label>
											<div class="col-sm-6">
												<sf:input path="phone" type="text" class="form-control" id="phone"/>
											</div>
										</div>
										<div class="form-group row">
											<div class="offset-sm-2 col-sm-10">
												<button type="submit" class="btn bg-gradient-success">Cập nhật</button>
											</div>
										</div>
									</sf:form>
								</div>
								<div class="tab-pane fade" id="changePassword">
									<form class="form-horizontal">
										<div class="form-group row">
											<label for="passOld" class="col-sm-2 col-form-label">Mật khẩu cũ</label>
											<div class="col-sm-6">
												<input type="password" class="form-control" id="passOld" placeholder="Nhập mật khẩu cũ">
											</div>
										</div>
										<div class="form-group row">
											<label for="passNew" class="col-sm-2 col-form-label">Mật khẩu mới</label>
											<div class="col-sm-6">
												<input type="password" class="form-control" id="passNew" placeholder="Nhập mật khẩu mới">
											</div>
										</div>
										<div class="form-group row">
											<label for="passNewConfirm" class="col-sm-2 col-form-label">Nhập lại mật khẩu mới</label>
											<div class="col-sm-6">
												<input type="password" class="form-control" id="passNewConfirm" placeholder="Nhập lại mật khẩu mới">
											</div>
										</div>
										<div class="form-group row">
											<div class="offset-sm-2 col-sm-10">
												<button type="button" onclick="updatePassword('${base}');" class="btn bg-gradient-success">Đổi mật khẩu</button>
											</div>
										</div>
									</form>
								</div>
								<!-- /.tab-pane -->
							</div>
							<!-- /.tab-content -->
						</div>
						<!-- /.card-body -->
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<footer class="main-footer">
			<strong>Copyright &copy; 2021 <a href="">Cellphone</a>.
			</strong> All rights reserved.
		</footer>
	</div>
	<!-- ./wrapper -->

	<!-- jQuery -->
	<script src="${base}/areas/admin/plugins/jquery/jquery.min.js "></script>
	<!-- Bootstrap 4 -->
	<script
		src="${base}/areas/admin/plugins/bootstrap/js/bootstrap.bundle.min.js "></script>
	<!-- Sweetalert 2 -->
	<script src="${base}/assets/lib/sweetalert/sweetalert2.all.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${base}/areas/admin/dist/js/adminlte.js "></script>
	<!-- Custom JS -->
	<script src="${base}/areas/admin/js/script.js"></script>
	
	<c:if test="${not empty TB}">
		<script>
			$(function () {
			    Swal.fire(
			        'Thành công',
			        'Cập nhật thông tin thành công',
			        'success'
			      )
			});
		</script>
	</c:if>
</body>

</html>