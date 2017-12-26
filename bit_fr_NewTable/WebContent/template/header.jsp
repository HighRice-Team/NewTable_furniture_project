<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
a {
	font-family: 고딕;
	font-size: 15pt;
	font-weight: bold;
	text-decoration: none;
	color: #444444;
	cursor: pointer;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.7.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		var url2
		$("#login").click(function() { //footer.jsp에 있는 loginDialog를 활성화한다.
			$("#loginDialog").dialog("open");
		})

		$("#header_logout").click(function() { //session의 있는 값 들을 모두 제거.
			if (confirm("로그아웃 하시겠습니까?")) {
				$.ajax({
					url : "ajax/login/logoutAjax.jsp",
					success : function() {
						location.href = "";
					}

				})
			}
		})
	})
</script>
<title>Insert title here</title>
</head>
<body>
	<div style="margin: 0 15% 0 15%; padding: 40px 0 40px 0;">
		<div style="width: 40%; float: left;">
			<a href="main.jsp"><img src="img/logo.png"
				style="width: 100%; height: 100px;" id="logoImg"></a>
		</div>
		<div style="width: 60%; float: right; text-align: left; color: white;">
			<div style="width: 20%; float: right;">

				<c:if test="${sessionScope.id==null}">
					<a id="login" value="login">LOGIN</a>
					<br>
				</c:if>

				<c:if test="${sessionScope.id!=null}">
					<a id="header_logout">LOGOUT</a>
					<br>
				</c:if>

				<c:if test="${sessionScope.id!=null}">
					<br>
				</c:if>

				<c:if test="${sessionScope.id==null}">
					<a href="join.do">JOIN</a>
					<br>
				</c:if>
				<a>MYPAGE</a><br> <a>CART</a><br>
				<c:if test="${sessionScope.grade=='관리자' }">
					<a href="">ADMIN</a>
				</c:if>
			</div>
			<div style="width: 20%; float: right;">
				<a>SELL</a><br> <br> <a href="aboutUs.do">ABOUT US</a><br>
				<a href="faq.do">FAQ</a><br> <a>QNA</a><br>
			</div>
			<div style="width: 20%; float: right;">
				<a href="customize.do">CUSTMIZE</a><br> <a
					href="product.do?item=BED">BED</a><br> <a
					href="product.do?item=SOFA">SOFA</a><br> <a
					href="product.do?item=CLOSET">CLOSET</a><br> <a
					href="product.do?item=DESK">DESK</a><br>
			</div>
		</div>
	</div>
</body>
</html>