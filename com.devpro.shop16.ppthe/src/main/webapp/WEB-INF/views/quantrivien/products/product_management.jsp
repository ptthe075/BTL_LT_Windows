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
<title>Product</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${base}/areas/admin/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${base}/areas/admin/dist/css/adminlte.min.css">
<!-- summernote -->
<link rel="stylesheet"
	href="${base}/areas/admin/plugins/summernote/summernote-bs4.min.css">

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
								<c:when test="${empty product.id}">
									<h1 class="m-0">Thêm mới sản phẩm</h1>
								</c:when>
								<c:otherwise>
									<h1 class="m-0">Sửa thông tin sản phẩm</h1>
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
								<sf:form method="post" action="${base}/admin/products/management" modelAttribute="product" enctype="multipart/form-data">
									<sf:hidden path="id" />
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label for="category">Danh mục (*)</label>
												<sf:select path="category.id"
													class="form-control custom-select" id="category">
													<sf:options items="${categories }" itemValue="id"
														itemLabel="name" />
												</sf:select>
											</div>
											<div class="form-group">
												<label for="brand">Thương hiệu (*)</label>
												<sf:select path="brand.id"
													class="form-control custom-select" id="brand">
													<sf:options items="${brands }" itemValue="id"
														itemLabel="name" />
												</sf:select>
											</div>
											<div class="form-group">
												<label for="name">Tên sản phẩm (*)</label>
												<sf:input path="name" type="text" id="name"
													class="form-control" required="required" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Ảnh hiển thị (*)</label>
												<div class="display_img-avatar">
													<c:choose>
														<c:when test="${empty product.avatar}">
															<img src="${base}/areas/admin/dist/img/upload_img.png"
																class="upload-img">
														</c:when>
														<c:otherwise>
															<img src="${base }/Upload/Products/${product.avatar}"
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
										<label for="shortDes">Mô tả ngắn (*)</label>
										<sf:textarea autocomplete="off" path="shortDes"
											class="form-control" id="shortDes"
											required="required"></sf:textarea>
									</div>
									<div class="form-group">
										<label for="details">Mô tả chi tiết (*)</label>
										<sf:textarea autocomplete="off" path="details"
											class="form-control summernote" id="details"
											required="required"></sf:textarea>
									</div>
									<div class="form-group">
										<label for="specifications">Thông số kỹ thuật (*)</label>
										<sf:textarea path="specifications"
											class="form-control summernote" id="specifications"
											required="required"></sf:textarea>
									</div>
									<div class="form-group">
										<label for="price">Đơn giá(*)</label>
										<sf:input path="price" type="number" id="price"
											class="form-control" required="required" />
									</div>
									<div class="form-group">
										<label for="priceSale">Giá khuyến mại</label>
										<sf:input path="priceSale" type="number" id="priceSale"
											class="form-control" />
									</div>
									<div class="form-group form-check">
										<sf:checkbox path="isHot" class="form-check-input" id="isHot" checked="checked"/>
										<label for="isHot">Là sản phẩm Hot?</label>
									</div>
									<div class="form-group">
										<div class="form-list-wrapper">
											<button class="btn bg-gradient-primary add-img-button">
												<i class="fas fa-plus"></i> Thêm ảnh
											</button>
											<div class="display_img-list">
												<c:forEach var="picture" items="${product.productImages }"
													varStatus="loop">
													<div class="display_img-item">
														<img src="${base }/Upload/Products/${picture.path}"
															class="upload-img">
														<div class="display_img-remove picture-path">x</div>
													</div>
												</c:forEach>
											</div>
										</div>
									</div>
									<div class="form-group">
										<a href="${base}/admin/products"
											class="btn btn-lg btn-secondary"> <i class="fas fa-reply"></i>
											Quay lại
										</a>
										<button type="submit"
											class="btn btn-lg btn-success float-right">
											<i class="fas fa-save"></i>
											<c:choose>
												<c:when test="${empty product.id}"> Tạo mới </c:when>
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

		<div id="select-nav" name-item="products" style="display: none;"></div>
	</div>
	<!-- ./wrapper -->

	<!-- jQuery -->
	<script src="${base}/areas/admin/plugins/jquery/jquery.min.js "></script>
	<!-- Bootstrap 4 -->
	<script
		src="${base}/areas/admin/plugins/bootstrap/js/bootstrap.bundle.min.js "></script>
	<!-- Summernote -->
	<script src="${base}/areas/admin/plugins/summernote/summernote-bs4.min.js "></script>

	<!-- AdminLTE App -->
	<script src="${base}/areas/admin/dist/js/adminlte.js "></script>

	<!-- Custom js -->
	<script src="${base}/areas/admin/js/item.script.js"></script>
	<script src="${base}/areas/admin/js/script.js"></script>
</body>

</html>