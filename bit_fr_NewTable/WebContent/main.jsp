
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/jquery-ui.min.css">
<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	margin: 0px;
	padding: 0px;
}
</style>
<title>BIT FR</title>


<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.7.0.min.js"></script>
<script type="text/javascript" src="./css/js/jquery-ui.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div style="position: relative; text-align: center;">

		<!-- 헤더영역 -->
		<div style="background: #ccc; height: 200px;">
			<%@ include file="template/header.jsp"%>
		</div>



		<!-- 바디영역 -->
		<div style="min_height: 100%; padding-bottom: 120px;">
			<jsp:include page="${viewPage }" />
		</div>



		<!-- 푸터영역 -->
		<div style="background: #ddd; height: 120px;">
			<%@ include file="template/footer.jsp"%>
		</div>

	</div>
</body>
</html>