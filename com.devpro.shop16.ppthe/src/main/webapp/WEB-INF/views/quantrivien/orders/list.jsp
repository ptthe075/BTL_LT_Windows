<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- import tagLib JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orders</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${base}/areas/admin/plugins/fontawesome-free/css/all.min.css">
<!-- DataTables -->
<link rel="stylesheet"
	href="${base}/areas/admin/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="${base}/areas/admin/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="${base}/areas/admin/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${base}/areas/admin/dist/css/adminlte.min.css">
<!-- Custom CSS -->
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
							<h1 class="m-0">Quản lý đơn hàng</h1>
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card card-primary card-outline card-outline-tabs">
								<div class="card-header p-0 border-bottom-0">
									<ul class="nav nav-tabs" id="custom-tabs-four-tab"
										role="tablist">
										<li class="nav-item"><a class="nav-link active"
											data-toggle="pill" href="#tabs-list-orders" role="tab"
											aria-controls="tabs-list-orders" aria-selected="true">
												Danh sách đơn hàng </a></li>
									</ul>
								</div>
								<div class="card-body">
									<div class="tab-content">
										<div class="tab-pane fade show active" id="tabs-list-orders"
											role="tabpanel">
											<table name-table="table" id="orders"
												class="table table-bordered table-striped">
												<thead>
													<tr>
														<th class="text-align-center" style="width: 30px;">#</th>
														<th style="width: 110px;">Mã đơn hàng</th>
														<th style="width: 150px;">Ngày tạo</th>
														<th style="width: 150px;">Tên khách hàng</th>
														<th style="width: 175px;">Tình trạng đơn hàng</th>
														<th style="width: 110px;">Tổng tiền</th>
														<th>Ghi chú</th>
														<th class="text-align-center" style="width: 90px;">Hành động</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="order" items="${orders }" varStatus="loop">
														<tr>
															<td class="text-align-center">${loop.index + 1}</td>
															<td>${order.code }</td>
															<td><fmt:formatDate pattern = "dd/MM/yyyy HH:mm:ss" value = "${order.createdDate }" /></td>
															<td>${order.customerName }</td>
															<c:choose>
																<c:when test="${order.saleOrderStatus.id == 4}">
																	<td style="color: red">${order.saleOrderStatus.name }</td>
																</c:when>
																<c:otherwise>
																	<td id="status-${order.id}" style="color: green">${order.saleOrderStatus.name }</td>
																</c:otherwise>
															</c:choose>
															<td style="text-align: right;"><fmt:formatNumber value="${order.total }" pattern="###,### ₫" type="number"/></td>
															<td id="note-${order.id}">${order.note }</td>
															<td id="action-${order.id}" class="text-align-center">
																<a href="${base }/admin/orders/detail/${order.id}" style="width: 100%;" class="btn bg-gradient-primary btn-sm">
																	<i class="fas fa-eye"></i>
																</a>
																<c:if test="${order.saleOrderStatus.id == 1}">
																	<a class="btn bg-gradient-success btn-sm" onclick="updateOrderStatus(${order.id}, 2);" style="margin-top: 10px; width: 100%;"> Giao hàng </a>
																	<a class="btn bg-gradient-danger btn-sm" onclick="updateOrderStatus(${order.id}, 4);" style="margin-top: 10px; width: 100%;"> Hủy đơn </a>
																</c:if>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!-- /.card -->
							</div>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
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

		<div id="select-nav" name-item="orders" style="display: none;"></div>
	</div>
	<!-- ./wrapper -->

	<!-- jQuery -->
	<script src="${base}/areas/admin/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="${base}/areas/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Sweetalert 2 -->
	<script src="${base}/assets/lib/sweetalert/sweetalert2.all.min.js"></script>
	<!-- DataTables  & Plugins -->
	<script src="${base}/areas/admin/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${base}/areas/admin/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="${base}/areas/admin/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="${base}/areas/admin/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<script
		src="${base}/areas/admin/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="${base}/areas/admin/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
	<script src="${base}/areas/admin/plugins/jszip/jszip.min.js"></script>
	<script src="${base}/areas/admin/plugins/pdfmake/pdfmake.min.js"></script>
	<script src="${base}/areas/admin/plugins/pdfmake/vfs_fonts.js"></script>
	<script
		src="${base}/areas/admin/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
	<script
		src="${base}/areas/admin/plugins/datatables-buttons/js/buttons.print.min.js"></script>
	<script
		src="${base}/areas/admin/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${base}/areas/admin/dist/js/adminlte.min.js"></script>
	<!-- Custom JS -->
	<script src="${base}/areas/admin/js/script.js"></script>
</body>

</html>