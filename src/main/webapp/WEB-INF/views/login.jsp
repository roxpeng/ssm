<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<title>后台管理系统:用户登陆</title>
	
	
	<!--Basic Styles-->
	<link id="bootstrap-rtl-link" href="" rel="stylesheet" />
	<!--Beyond styles-->
	<link id="skin-link" href="" rel="stylesheet" type="text/css" />
	
	<!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
	</head>
	<body>
	<form id="lg-form" method="post" action="/ssm/login" >
		<div class="login-container animated fadeInDown">
			<div class="loginbox bg-white">
				
				<div class="loginbox-textbox">
					<input type="text" class="form-control" placeholder="用户名" id="username" name="username"/>
				</div>
				<div class="loginbox-textbox">
					<input type="password" class="form-control" placeholder="密码" id="password" name="password"/>
				</div>
				<div class="loginbox-forgot">
					<a href="">Forgot Password?</a>
				</div>
				<div class="loginbox-submit">
					<input type="submit" class="btn btn-primary btn-block" value="Login">
				</div>
				<!-- <div class="loginbox-signup">
					<a href="register.html">Sign Up With Email</a>
				</div> -->
				
			</div>
<!-- 			<div class="logobox"></div>
 -->		</div>
	</form>
		<!--Basic Scripts-->
	
	
	
	</body>
</html>