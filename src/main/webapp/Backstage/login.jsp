<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>登录</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div class="text-center" >
            <div class="text-center" >
                <h1 class="logo-name  text-center" >Vs</h1>
            </div>
            <h3>欢迎使用 </h3>

            <form class="m-t" role="form" action="#">
                <div class="form-group">
                    <input type="text" id="admin" class="form-control" placeholder="用户名" required="">
                </div>
                <div class="form-group">
                    <input type="password" id="pwd" class="form-control" placeholder="密码" required="">
                </div>
                <a class="btn btn-primary block full-width m-b" id="btn_login">登 录</a>


                <p class="text-muted text-center"> <a href="login.jsp#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a>
                </p>

            </form>
        </div>
    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    <script >

        $("#btn_login").click(function(){
            var params={};
            params.admin =$("#admin").val();
            params.pwd =$("#pwd").val();
            $.ajax({
                async: false,
                type: "POST",
                url: "../admin/login",//注意路径
                data: params,
                dataType: "json",
                success: function (data) {
                    console.log(JSON.stringify(data,null,4));
                    if(data.t_admin == null){
                        alert("账号不存在或无效 ！");
                    }else if (data.menuVos==null){
                        alert("密码错误 ！");
                    }else{
                        window.location.href="index.jsp";
                    }
                },
                error: function (data) {
                    console.log(JSON.stringify(data,null,4));
                    alert("登陆出错 ！");
                }
            });
        });


    </script>

</body>

</html>