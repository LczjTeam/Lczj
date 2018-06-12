<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>修改头像</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/plugins/codemirror/codemirror.css" rel="stylesheet">
    <link href="css/plugins/codemirror/ambiance.css" rel="stylesheet">
    <link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="plugins/bootstrap-switch/css/bootstrap-switch.css" rel="stylesheet">
    <link href="plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet">
    <link href="plugins/css/components.css" rel="stylesheet">
    <link href="plugins/css/plugins.css" rel="stylesheet">
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">

</head>
    <!-- END HEAD -->
    <body>
    <div>
        <form id="headpic_form" class="center-block"  style="margin-left: 50px;margin-top: 50px;">
            <div class="fileinput fileinput-new" data-provides="fileinput">
                <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
                    <img src="../heads/${sessionScope.admin.t_admin.admin}.png" alt="" /> </div>
                <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;"> </div>
                <div>
                    <span class="btn default btn-file">
                        <span class="fileinput-new">选择图片</span>
                        <span class="fileinput-exists"> 修改 </span>
                        <input type="file" name="file"> </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 移除 </a>
                </div>
            </div>
            <div class="clearfix margin-top-10">
                <span class="label label-danger">注意!</span> 请使用 IE10+、 FF3.6+、 Safari6.0+、 Chrome6.0+ 、 Opera11.1+ 浏览器进行头像修改。
            </div>
            <div class="clearfix margin-top-10">
                <a id="btn_alter_headpic" class="btn btn-success">确认修改 </a>
            </div>
        </form>
    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/plugins/peity/jquery.peity.min.js"></script>
    <script src="js/plugins/codemirror/codemirror.js"></script>
    <script src="js/plugins/codemirror/mode/javascript/javascript.js"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
	<script src="plugins/bootstrap-switch/js/bootstrap-switch.js"></script>
    <script src="plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>

    <script src="custom-js/headpic.js"></script>

</body>

</html>
    </body>

</html>