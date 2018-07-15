<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
    function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //for-mobile-apps -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <!-- pignose css -->
    <link href="css/pignose.layerslider.css" rel="stylesheet" type="text/css" media="all" />


    <!-- //pignose css -->
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <!-- js -->
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <!-- //js -->
    <!-- cart -->
    <script src="js/simpleCart.min.js"></script>
    <!-- cart -->
    <!-- for bootstrap working -->
    <script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
    <!-- //for bootstrap working -->
    <link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,900,900italic,700italic' rel='stylesheet' type='text/css'>
    <script src="js/jquery.easing.min.js"></script>


    <link href="css/common.css" rel="stylesheet" type="text/css" media="all">
    <link href="css/commontDrying.css" rel="stylesheet" type="text/css" media="all">
    <script type="text/javascript">
        window.onload=function(){
            setInterval(function(){
                var date=new Date();
                var year=date.getFullYear(); //获取当前年份
                var mon=date.getMonth()+1; //获取当前月份
                if (mon<10){
                    mon = '0'+mon;
                }
                var da=date.getDate(); //获取当前日
                if(da<10){
                    da='0'+da;
                }
                var day=date.getDay(); //获取当前星期几
                var h=date.getHours(); //获取小时
                if(h<10){
                    h='0'+h;
                }
                var m=date.getMinutes(); //获取分钟
                if(m<10){
                    m='0'+m;
                }
                var s=date.getSeconds(); //获取秒
                if (s<10){
                    s='0'+s;
                }
                var d=document.getElementById('Date');
                switch(day){
                    case 0:
                        day = "日";
                        break;
                    case 1:
                        day = "一";
                        break;
                    case 2:
                        day = "二";
                        break;
                    case 3:
                        day = "三";
                        break;
                    case 4:
                        day = "四";
                        break;
                    case 5:
                        day = "五";
                        break;
                    case 6:
                        day = "六";
                        break;
                }
                d.innerHTML='  '+year+'年'+mon+'月'+da+'日'+'    '+'星期'+day+' '+h+':'+m+':'+s;  },1000)  }
    </script>
    <style>

        .multi-column-dropdown li:hover{
            background-color:#000000;

            border-left: 3px solid #fda30e;
        }
        .multi-column-dropdown li{
            background-color: #FDFCF3;
        }


    </style>
</head>
<body>
<!-- header -->
<div class="header">
    <div class="container">
        <ul>
            <li><span class="glyphicon glyphicon-time" aria-hidden="true"></span><a id="Date"></a></li>
            <li><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Free shipping On all orders</li>
            <li><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span><a href="mailto:info@example.com">info@example.com</a></li>
        </ul>
    </div>
</div>
<!-- //header -->
<!-- header-bot -->
<div class="header-bot">
    <div class="container">
        <div class="col-md-3 header-left">
            <h1><a href="index.html"><img src="images/logo.jpg"></a></h1>
        </div>
        <div class="col-md-6 header-middle">
            <form>
                <div class="search">
                    <input type="search" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}" required="">
                </div>
                <div class="section_room">
                    <select id="country" onchange="change_country(this.value)" class="frm-field required">
                        <option value="null">All categories</option>
                        <option value="AX">Men's Wear</option>
                        <option value="AX">Women's Wear</option>
                        <option value="AX">Watches</option>
                    </select>
                </div>
                <div class="sear-sub">
                    <input type="submit" value=" ">
                </div>
                <div class="clearfix"></div>
            </form>
        </div>
        <div class="col-md-3 header-right footer-bottom">
            <ul>
                <li><a class="use1" href="../Backstage/index.jsp" ><span>登录</span></a>

                </li>
                <li><a class="fb" href="#"></a></li>
                <li><a class="twi" href="#"></a></li>
                <li><a class="insta" href="#"></a></li>
                <li><a class="you" href="#"></a></li>
            </ul>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<!-- //header-bot -->
<!-- banner -->
<div class="ban-top">
    <div class="container"  style="text-align:center;">
<%--
        <div class="top_nav_left" style="text-align:center;">
--%>
            <nav class="navbar navbar-default" style="margin-left: 22%;"  >
                <div class="container-fluid" >
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="coll collapse navbar-collapse menu--shylock" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav menu__list ">
                            <%--<li>
                                <span class="glyphicon glyphicon-home" aria-hidden="true" style="font-size: 42px;color: #f8faf9;margin: 18px"></span>
                            </li>--%>
                            <li class="menu__item menu__item--current"><a class="menu__link" href="../Fronts/index"><span class="glyphicon glyphicon-home" ></span>&nbsp;&nbsp;&nbsp;首&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp; <span class="sr-only">(current)</span></a></li>
                            <li class="menu__item"><a class="menu__link" href="../Fronts/newgoods?type=1">&nbsp;&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;品&nbsp;&nbsp;&nbsp;上&nbsp;&nbsp;&nbsp;市&nbsp;&nbsp;&nbsp;</a></li>
                            <li id="drop_a" class="dropdown menu__item">
                                <a href="#" class="dropdown-toggle menu__link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">&nbsp;&nbsp;&nbsp;知&nbsp;&nbsp;&nbsp;识&nbsp;&nbsp;&nbsp;百&nbsp;&nbsp;&nbsp;科&nbsp;&nbsp;&nbsp; <span class="caret"></span></a>
                                <ul class="dropdown-menu multi-column columns-2">
                                    <div class="row">
                                        <div class="coll2 col-sm-12 multi-gd-img">
                                            <ul class="multi-column-dropdown">
                                                <li id="one" style="text-align: center;" ><a style="font-size:15px;"  href="../Fronts/knowledge?c=0" >乐潮眼镜</a></li>
                                                <li id="tow" style="text-align: center; "><a style="font-size: 15px; " href="../Fronts/knowledge?c=1" >眼镜二三事</a></li>
                                            </ul>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </ul>
                            </li>
                            <li class=" menu__item" ><a class="menu__link" href="../Fronts/issues.jsp">&nbsp;&nbsp;&nbsp;晒&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;&nbsp;评&nbsp;&nbsp;&nbsp;论</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
<%--
        </div>
--%>
       <%-- <div class="top_nav_right">
            <div class="cart box_1">
                <a href="checkout.html">
                    <h3> <div class="total">
                        <i class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></i>
                        <span class="simpleCart_total"></span> (<span id="simpleCart_quantity" class="simpleCart_quantity"></span> items)</div>

                    </h3>
                </a>
                <p><a href="javascript:;" class="simpleCart_empty">Empty Cart</a></p>

            </div>
        </div>--%>
<%--
        <div class="clearfix"></div>
--%>
    </div>
</div>

<script type="text/javascript">

    $(".coll a").each(function(){

        $this = $(this);
        var a_href = $this[0].href.substring(0,$this[0].href.length-1);
        var win_href = String(window.location).substring(0,String(window.location).length-1);
        if(a_href==win_href){

            $this.parent().addClass("menu__item--current");

        }else {
            $this.parent().removeClass("menu__item--current");
        }
    });


    $(".coll2 a").each(function(){

        $this = $(this);
        var a_href = $this[0].href.substring(0,$this[0].href.length-1);
        var win_href = String(window.location).substring(0,String(window.location).length-1);
        if(a_href==win_href){

            $("#drop_a").addClass("menu__item--current");

        }else {
            $("#drop_a").removeClass("menu__item--current");
        }
    });

</script>

</body>