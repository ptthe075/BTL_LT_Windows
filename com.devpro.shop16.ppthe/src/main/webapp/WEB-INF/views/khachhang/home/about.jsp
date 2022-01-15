<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- import SPRING-FORM -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Layout</title>
<jsp:include page="/WEB-INF/views/khachhang/layout/css.jsp"></jsp:include>
<link rel="stylesheet" href="${base}/assets/css/style.css">
<link rel="stylesheet" href="${base}/assets/css/contact.css">
<link rel="stylesheet" href="${base}/assets/css/reponsive.css">
</head>

<body>
	<jsp:include page="/WEB-INF/views/khachhang/layout/header.jsp"></jsp:include>

	<div class="block-breadcrumbs box-shadow">
		<div class="grid breadcrumbs">
			<ul class="breadcrumb-list">
				<li class="breadcrumb-item"><a href="${base}/" class="breadcrumb-link">
						<i class="fas fa-home breadcrumb-home"></i> Trang chủ
				</a> <i class="fas fa-angle-right"></i></li>
				<li class="breadcrumb-item">${information.name }</li>
			</ul>
		</div>
	</div>

	<div class="container-wrapper">
		<div class="grid">
			<div class="row">
				<div class="col c-12 block-container border-radius box-shadow">
					${information.description }
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/khachhang/layout/footer.jsp"></jsp:include>

	<script src="${base}/assets/lib/jquery/jquery.min.js"></script>
	<script src="${base}/assets/js/script.js"></script>
</body>

</html>