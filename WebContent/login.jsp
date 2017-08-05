<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap3/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath }/bootstrap3/js/jquery-3.2.1.js">
</script>
<script
	src="${pageContext.request.contextPath }/bootstrap3/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
	  body {
        padding-top: 200px;
        padding-bottom: 40px;
        background-size: 100% 100%;
        background-image: url('images/login_bg.jpg');
        
      }
      
      .form-signin-heading{
      	text-align: center;
      }

      .form-signin {
        max-width: 300px;
        padding: 30px;
        margin: 0 auto 20px;
        background-color: #3399CC;
        border: 1px solid #99CCFF;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

</style>
<script type="text/javascript">
	function checkInput(){
		var userName = document.getElementById("userName").value;
		var password = document.getElementById("password").value;
		if(userName == null || userName==""){
			document.getElementById("error").innerHTML="用户名不能为空";
			return false;
		}
		if(password == null || password==""){
			document.getElementById("error").innerHTML="密码不能为空";
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<div class="container">
      <form name="myForm" class="form-signin" action="login" method="post" onsubmit="return checkInput()">
        <h2 class="form-signin-heading">召唤师笔记</h2>
        <input id="userName" name="userName" value="${user.userName }"  type="text" class="input-block-level" placeholder="用户名">
        <input id="password" name="password" value="${user.password }"   type="password" class="input-block-level" placeholder="密码" >
        <label class="checkbox" style="padding-left: 22px">
          <input id="remember" name="remember" type="checkbox" value="remember-me" >记住我 &nbsp;&nbsp;&nbsp;&nbsp; <font id="error" color="red">${error }</font>  
        </label>
        <button class="btn btn-large btn-primary" type="submit">登录</button>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn btn-large btn-primary" type="button" >重置</button>

      </form>
</div>
</body>
</html>