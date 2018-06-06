<%--
  Created by IntelliJ IDEA.
  User: 纷呈
  Date: 2018/5/13
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Back</title>
    <script type="text/javascript" src="js/global.js"></script>
    <link rel="stylesheet" href="DDLD_CSS/iconfont.css">
    <link rel="stylesheet" href="DDLD_CSS/global.css">
    <link rel="stylesheet" href="DDLD_CSS/styles.css">
</head>
<body>

<!-- 右侧菜单 -->
<div class="right-nav">
    <ul class="r-with-gotop">
        <li class="r-toolbar-item">
            <a href="../Backstage/index.html" class="r-item-hd">
                <i class="iconfont icon-user" data-badge="0"></i>
                <div class="r-tip__box"><span class="r-tip-text">用户中心</span></div>
            </a>
        </li>
        <li class="r-toolbar-item">
        <a href="#" class="r-item-hd">
            <i class="iconfont icon-cart"></i>
            <div class="r-tip__box"><span class="r-tip-text">购物车</span></div>
        </a>
    </li>
        <li class="r-toolbar-item">
            <a href="udai_collection.html" class="r-item-hd">
                <i class="iconfont icon-aixin"></i>
                <div class="r-tip__box"><span class="r-tip-text">我的收藏</span></div>
            </a>
        </li>
        <li class="r-toolbar-item">
            <a href="" class="r-item-hd">
                <i class="iconfont icon-liaotian"></i>
                <div class="r-tip__box"><span class="r-tip-text">联系客服</span></div>
            </a>
        </li>
        <li class="r-toolbar-item">
            <a href="issues.jsp" class="r-item-hd">
                <i class="iconfont icon-liuyan"></i>
                <div class="r-tip__box"><span class="r-tip-text">留言反馈</span></div>
            </a>
        </li>
        <li class="r-toolbar-item to-top">
            <i class="iconfont icon-top"></i>
            <div class="r-tip__box"><span class="r-tip-text">返回顶部</span></div>
        </li>
    </ul>
    <script>
        $(document).ready(function(){ $('.to-top').toTop({position:false}) });
    </script>
</div>
</body>
</html>
