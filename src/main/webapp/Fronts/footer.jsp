<%--
  Created by IntelliJ IDEA.
  User: 14260
  Date: 2018/7/8
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="coupons">
    <div class="container">
        <div class="coupons-grids text-center">
            <div class="col-md-3 coupons-gd">
                <h3>以简单的方式购买您的产品</h3>
            </div>
            <div class="col-md-3 coupons-gd">
                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                <h4>微信小程序登陆您的个人中心</h4>
            </div>
            <div class="col-md-3 coupons-gd">
                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                <h4>选择合适的商品</h4>
                <p>简单生活</p>
            </div>
            <div class="col-md-3 coupons-gd">
                <span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span>
                <h4>开始你的购买</h4>
                <p>拒绝等待，每天都有它的意义</p>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>

<!-- footer -->
<div class="footer">
    <div class="container">
        <div class="col-md-3 footer-left">
            <h2><a href="index.html"><img src="images/logo.jpg" alt=" " /></a></h2>
            <ul>
                <a href="single.html"><img src="images/lczj.png" alt=" " class="img-responsive"    height: 150px;
                                           width: 150px;/></a>
            </ul>
        </div>
        <div class="col-md-9 footer-right">
            <div class="clearfix"></div>
            <div class="sign-grds">
                <div class="col-md-3 sign-gd">
                    <h4>服务</h4>
                    <ul>
                        <li><a href="mens.html">反馈</a></li>
                        <li><a href="womens.html">侵权投诉</a></li>
                        <li><a href="electronics.html">免广告合作</a></li>
                        <li><a href="codes.html">客服</a></li>
                        <li><a href="contact.html">联系</a></li>
                    </ul>
                </div>

                <div class="col-md-5 sign-gd-two">
                    <h4>店面信息</h4>
                    <ul>

                        <c:forEach items="${stores}" var="shopAddress">
                        <li  id="${shopAddress.shop}" data-toggle="tooltip" data-placement="right" title="<div style='width:100px;color:#fda30e ;font-family: 华文楷体;font-size: 14px ;background-color: #4c7671c9;'>
                        <p>店名：${shopAddress.name}</p>
                        <p>地址：${shopAddress.address}</p>
                        <p>电话：${shopAddress.phone}</p>
                        <div>" ><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>地点 : ${shopAddress.name} : ${shopAddress.address}</li>
                       <%-- <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i>Email : <a href="mailto:info@example.com">info@example.com</a></li>
                        <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>电话 : +1234 567 567</li>--%>
                        <script>
                            $(function () { $("#${shopAddress.shop}").tooltip({html : true });});
                        </script>
                        </c:forEach>
                    </ul>
                    <ul>
                        <li><a href="#" class="btn btn-warning" data-toggle="modal" data-target="#myModal4"><span>更多</span></a>
                        </li>
                        <%--<button  id="more-shop"  data-target="#myModal4" >--更多--</button>--%>
                    </ul>
                </div>
                <div class="col-md-4 sign-gd flickr-post">
                    <h4>微信购买</h4>

                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="clearfix"></div>
        <p class="copy-right">Copyright © 2018 - 2019 Lczj. All Rights Reserved. <a href="" target="_blank" title="乐潮之境">乐潮之境</a> </p>
    </div>
</div>
<!-- //footer -->
<!-- login -->
<div class="modal fade" id="myModal4" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content modal-info" style="border: 2px solid rgba(244, 67, 54, 0.39);width: 500px;margin-left: 13%;height: 500px;overflow: scroll; overflow-x: hidden;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body modal-spa">
                <div class="login-grids">
                    <div class="login">
                        <c:forEach items="${shops}" var="shopall">
                        <div class="login-bottom">
                           <h3 > <span class="glyphicon glyphicon-home">&nbsp;</span>${shopall.name}</h3>
                            <form>
                                <div class="sign-up" style="height: 40px;">

                                    <span style="font-size: 16px; " > <span class="glyphicon glyphicon-map-marker">&nbsp;</span>地址：</span>
                                    <span type="text" readonly   style="color: #FDA30E; font-size: 16px; height: 40px;">${shopall.address}</span>
                                </div>
                                <div class="sign-up" style="height: 40px;">

                                    <span style="font-size: 16px; "  > <span class="glyphicon glyphicon-earphone">&nbsp; </span>电话： </span>
                                    <span type="text" readonly style="color: #FDA30E;font-size: 16px; height: 40px;">${shopall.phone}</span>
                                </div>
                            </form>

                        </div> <hr style="    border-top: 1px solid #b4a9a9;">
                        </c:forEach>
                        <%--<div class="login-right">
                            <h3>Sign in with your account</h3>
                            <form>
                                <div class="sign-in">
                                    <h4>Email :</h4>
                                    <input type="text" value="Type here" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Type here';}" required="">
                                </div>
                                <div class="sign-in">
                                    <h4>Password :</h4>
                                    <input type="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}" required="">
                                    <a href="#">Forgot password?</a>
                                </div>
                                <div class="single-bottom">
                                    <input type="checkbox"  id="brand" value="">
                                    <label for="brand"><span></span>Remember Me.</label>
                                </div>
                                <div class="sign-in">
                                    <input type="submit" value="SIGNIN" >
                                </div>
                            </form>
                        </div>--%>
                        <div class="clearfix"></div>
                    </div>
                   <%-- <p>各个分店<a href="#"></a> and <a href="#">Privacy Policy</a></p>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- //login -->
<script>
</script>
</body>
</html>