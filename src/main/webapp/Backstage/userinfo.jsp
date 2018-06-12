<%--
  Created by IntelliJ IDEA.
  User: 14260
  Date: 2018/6/12
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>修改个人资料及密码</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">


    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>修改个人资料及密码</h5>
                </div>
                <div class="ibox-content " style="height: auto;width: 100%;height: 450px;" >
                    <form class="form-horizontal col-md-8 col-sm-12 col-md-offset-2" id="signupForm"  style="background-color: white;">
                        <div class="form-group" >
                            <label class="col-sm-12 control-label" style="text-align: left;">账号：</label>
                            <div class="col-sm-12 col"  >
                                <input id="admin" readonly name="admin" value="${sessionScope.admin.t_admin.admin}" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-12 control-label text-left" style="text-align: left;">名称：</label>
                            <div class="col-sm-12">
                                <input id="admin_name" name="admin_name" value="${sessionScope.admin.t_admin.name}" class="form-control" type="text" aria-required="true" aria-invalid="false" class="valid">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-12 control-label text-left" style="text-align: left;">密码：</label>
                            <div class="col-sm-12">
                                <input id="password" name="password"  class="form-control" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-12 control-label text-left" style="text-align: left;">确认密码：</label>
                            <div class="col-sm-12">
                                <input id="confirm_password" name="confirm_password" class="form-control" type="password">
                                <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请再次输入您的密码</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12  text-center">
                                <a id="btn_alter_info" class="btn btn-primary" >确认修改</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.5"></script>
<script src="js/content.min.js?v=1.0.0"></script>
<script src="js/plugins/validate/jquery.validate.min.js"></script>
<script src="js/plugins/validate/messages_zh.min.js"></script>
<script src="js/plugins/sweetalert/sweetalert.min.js"></script>

<script src="custom-js/userinfo.js"></script>
</body>

</html>
