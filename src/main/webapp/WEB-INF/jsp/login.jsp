<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
%>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Log in</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="<%=path%>/static/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=path%>/static/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="<%=path%>/static/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=path%>/static/dist/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="<%=path%>/static/plugins/iCheck/square/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="../../index2.html"><b>Admin</b>LTE</a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">请先登录系统</p>

    <form action="account/login" method="post" id="loginForm">
      <div class="form-group has-feedback warning-div" id="usernameDiv">
      	<label class="control-label warning-label" style="display:none;" for="username" id="usernameLabel"><i class="fa fa-bell-o"></i>请输入用户名</label>
        <input type="text" class="form-control" name="username" id="username" placeholder="账号">
        <!-- <span class="glyphicon glyphicon-envelope form-control-feedback"></span> -->
      </div>
      <div class="form-group has-feedback warning-div" id="passwordDiv">
        <label class="control-label warning-label" style="display:none;" for="password" id="passwordLabel"><i class="fa fa-bell-o"></i>请输入密码</label>
        <input type="password" class="form-control" name="password" id="password" placeholder="密码">
        <!-- <span class="glyphicon glyphicon-lock form-control-feedback"></span> -->
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <input type="checkbox"> 记住我
            </label>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="button" id="loginBtn" class="btn btn-primary btn-block btn-flat">登 录</button>
        </div>
        <!-- /.col -->
      </div>
    </form>

    

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3 -->
<script src="<%=path%>/static/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="<%=path%>/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="<%=path%>/static/plugins/iCheck/icheck.min.js"></script>
<script src="<%=path%>/js/util/jquery.serializejson.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
    
    $("#loginBtn").click(function(){
    	$(".warning-div").removeClass("has-error");
    	$(".warning-label").hide();
    	var username = $("#username").val();
    	var password = $("#password").val();
    	var hasError = false;
    	if(username === "")
   		{
   			$("#usernameDiv").addClass("has-error");
   			$("#usernameLabel").show();
   			hasError = true;
   		}
    	if(password === "")
   		{
    		$("#passwordDiv").addClass("has-error");
   			$("#passwordLabel").show();
   			hasError = true;
   		}
    	if(hasError)
    	{
    		return;
    	}
    	$.ajax({
            type: "post",
            url: '../account/login',
            async: false, // 使用同步方式
            data: JSON.stringify($('#loginForm').serializeJSON()),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
                if(data.code==1)
               	{
               		window.location.href="../main/index";
               	}
                else{
                	alert(data.message);
                }
            }
        });
    	
    });
  });
</script>
</body>
</html>
