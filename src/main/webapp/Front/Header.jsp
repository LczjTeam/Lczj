<%--
  Created by IntelliJ IDEA.
  User: 纷呈
  Date: 2018/5/13
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        window.onload=function(){
            setInterval(function(){
                var date=new Date();
                var year=date.getFullYear(); //获取当前年份
                var mon=date.getMonth()+1; //获取当前月份
                var da=date.getDate(); //获取当前日
                var day=date.getDay(); //获取当前星期几
                var h=date.getHours(); //获取小时
                var m=date.getMinutes(); //获取分钟
                var s=date.getSeconds(); //获取秒
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


        .top-container{
            padding-left: 25%;
        }

    </style>

</head>
<body>
<!-- header -->
<div class="header">
    <div class="container">
        <ul>
            <li><div id="Date"></div></li>
            <li>包邮</li>
            <li><a href="" style="color: #b78282;">138765612626</a></li>
        </ul>
    </div>
</div>
<!-- //header -->
<!-- header-bot -->
<div class="header-bot">
    <div class="container">
        <div class="col-md-3 header-left">
            <h1><a href="index.jsp"><img src="images/logo.jpg"></a></h1>
        </div>
        <div class="col-md-6 header-middle">
            <form>
                <div class="search">
                    <input type="search" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}" required="">
                </div>
                <!--查找的时候在修改值-->
                <div class="section_room">
                    <select id="country" onchange="change_country(this.value)" class="frm-field required">
                        <option value="null">太阳镜</option>
                        <option value="null">近视镜</option>
                        <option value="null">镜框</option>
                        <option value="null">镜片</option>
                        <option value="null">镜盒</option>
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
                <li><a href="Backstage/index.html" class="use1" <%--data-toggle="modal" data-target="#myModal4"--%>><span>Login</span></a>

                </li>
                <li><a class="fb" href="#"></a></li>
                <li><a class="twi" href="#"></a></li>
                <li><a class="insta" href="#"></a></li>
            </ul>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<!-- //header-bot -->
<!-- banner -->
<div class="ban-top">
    <div class="top-container">
        <div class="top_nav_left">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
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
                    <div class="collapse navbar-collapse menu--shylock" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav menu__list">
                            <li class="active menu__item menu__item--current"><a class="menu__link" href="index.jsp">主页 <span class="sr-only">(current)</span></a></li>
                            <li class="dropdown menu__item">
                                <a class="menu__link" href="Mens.jsp">新品上市</a>
                            </li>
                            <li class=" menu__item"><a class="menu__link" href="TeamInfo.jsp">知识百科</a></li>
                            <li class=" menu__item"><a class="menu__link" href="issues.jsp">晒单评论</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
      <%--  <div class="top_nav_right">
            <div class="cart box_1">
                <a href="Checkout.jsp">
                    <h3> <div class="total">
                        <i class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></i>
                        <span class="simpleCart_total"></span> (<span id="simpleCart_quantity" class="simpleCart_quantity"></span> items)</div>

                    </h3>
                </a>
                <p><a href="javascript:;" class="simpleCart_empty">Empty Cart</a></p>

            </div>
        </div>--%>
        <div class="clearfix"></div>
    </div>
</div>
<!-- //banner-top -->
</body>
</html>
