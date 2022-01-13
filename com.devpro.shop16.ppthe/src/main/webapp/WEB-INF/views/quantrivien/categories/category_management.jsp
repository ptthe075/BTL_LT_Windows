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
<title>Category</title>

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
<link rel="stylesheet" href="${base}/areas/admin/css/item.style.css">
<link rel="stylesheet" href="${base}/areas/admin/css/style.css">
</head>

<body class="sidebar-mini layout-fixed sidebar-collapse">
	<div class="wrapper">
		<!-- Common -->
		<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
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
							<c:choose>
								<c:when test="${empty category.id}">
									<h1 class="m-0">Thêm mới danh mục</h1>
								</c:when>
								<c:otherwise>
									<h1 class="m-0">Sửa thông tin danh mục</h1>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card card-primary">
							<div class="card-body">
								<sf:form method="post"
									action="${base}/admin/categories/management"
									modelAttribute="category" enctype="multipart/form-data">
									<sf:hidden path="id" />
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label for="category">Danh mục cha (*)</label>
												<sf:select path="parent.id" class="form-control custom-select"
													id="category">
													<sf:option value="" label="Không có" />
													<sf:options items="${categories }" itemValue="id"
														itemLabel="name" />
												</sf:select>
											</div>
											<div class="form-group">
												<label for="name">Tên danh mục (*)</label>
												<sf:input path="name" type="text" id="name"
													class="form-control" required="required" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Ảnh icon (*)</label>
												<div class="display_img-avatar">
													<c:choose>
														<c:when test="${empty category.image}">
															<img src="${base}/areas/admin/dist/img/upload_img.png"
																class="upload-img">
														</c:when>
														<c:otherwise>
															<img src="${base }/Upload/Categories/${category.image}"
																class="upload-img">
														</c:otherwise>
													</c:choose>
													<label for="img-avatar" class="display_img-change ">Chọn
														ảnh</label> <input type="file" id="img-avatar" class="change-img"
														name="add_img-avatar" hidden>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<a href="${base}/admin/categories"
											class="btn btn-lg btn-secondary"> <i class="fas fa-reply"></i>
											Quay lại
										</a>
										<button type="submit"
											class="btn btn-lg btn-success float-right">
											<i class="fas fa-save"></i>
											<c:choose>
												<c:when test="${empty category.id}"> Tạo mới </c:when>
												<c:otherwise> Cập nhật </c:otherwise>
											</c:choose>
										</button>
									</div>
								</sf:form>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<footer class="main-footer">
			<strong>Copyright &copy; 2021 <a href="">Cellphone</a>.
			</strong> All rights reserved.
		</footer>

		<div id="select-nav" name-item="categories" style="display: none;"></div>
	</div>
	<!-- ./wrapper -->

	<!-- jQuery -->
	<script src="${base}/areas/admin/plugins/jquery/jquery.min.js "></script>
	<!-- Bootstrap 4 -->
	<script
		src="${base}/areas/admin/plugins/bootstrap/js/bootstrap.bundle.min.js "></script>
	<!-- AdminLTE App -->
	<script src="${base}/areas/admin/dist/js/adminlte.js "></script>

	<!-- Custom js -->
	<script src="${base}/areas/admin/js/item.script.js"></script>
	<script src="${base}/areas/admin/js/script.js"></script>

</body>

</html>