<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/jquery-ui.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.7.0.min.js"></script>
<script type="text/javascript" src = "./css/js/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(function(){
	    $("#loginDialog").dialog({
	       autoOpen:false,
	       modal:true,
	       resizable:false,
	       buttons:{
	          "로그인":function(){
	             var id = $("#login_member_id").val()
	             var pwd = $("#login_pwd").val()
	             var data = {"member_id":id,"pwd":pwd};
	             $.ajax({
	                url:"login/loginAjax.jsp",
	                data:data,
	                success:function(data){
	                   data = eval("("+data+")");
	                   if(data.str==""){
	                      location.href="";
	                      $("#loginDialog").dialog("close")
	                   }else{
	                      $("#msg").html("*"+data.str);
	                   }
	                }
	             })
	          },
	          "취소":function(){
	             $("#loginDialog").dialog("close")
	          },
	          "회원가입":function(){
	             location.href = "join.do"
	          }
	       }
	    });
	})
</script>
<title>Insert title here</title>
</head>
<body>
	<div style="margin: 60px 28% 0 28%; padding: 10px 0 10px 0; ">
		<div style="width: 65%; float: left; ">
			<img  src="img/logo.png" style="width:70%; height: auto;">
		</div>
		<div style="width: 35%; float: right; text-align: left;">
			BIR FR Company<br>
			주소 : 서울시 마포구 신수동<br>
			고객센터 : 02-1234-5678<br>
			이메일 : admin@bitfr.com<br>
		</div>
	</div>
	
	<div id="loginDialog" title="로그인">
		<form id="loginForm">
			<table>
				<tr>
					<td>ID:</td>
					<td><input type="text" id="login_member_id"	name="login_member_id"></td>
				</tr>
				<tr>
					<td>PW:</td>
					<td><input type="password" id="login_pwd" name="login_pwd"></td>
				</tr>
				<tr>
					<td colspan="2" style="color: red" id="msg"></td>
				</tr>
			</table>
		</form>
		<br>
	</div>
</body>
</html>