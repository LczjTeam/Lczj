<%--
  Created by IntelliJ IDEA.
  User: 纷呈
  Date: 2018/5/13
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<!-- login -->
<div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content modal-info">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body modal-spa">
                <div class="login-grids">
                    <div class="login">
                        <div class="login-bottom">
                            <h3 class="login-bottom-sign">免费注册</h3>
                            <form action="" method="post">
                                <div class="sign-up">
                                    <h4>手机号：</h4>
                                    <input type="text" value="手机号码" onfocus="this.value = '';" onblur="<%



                   %>" required="required">

                                </div>
                                <div class="sign-up">
                                    <h4>密码：</h4>
                                    <input type="password" value="" onfocus="this.value = '';" onblur="" required="required">

                                </div>
                                <div class="sign-up">
                                    <h4>确认密码：</h4>
                                    <input type="password" value="" onfocus="this.value = '';" onblur="" required="required">
                                </div>
                                <div class="sign-up">
                                    <h4>验证码：</h4>
                                    <input type="text" value="请输入验证码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}" required="required">
                                </div>
                                <div class="sign-up">
                                    <input type="submit" value="免费注册" >
                                </div>

                            </form>
                        </div>
                        <div class="login-right">
                            <h3 class="login-right-sign">用户登陆</h3>
                            <form action="" method="post">
                                <div class="sign-in">
                                    <h4>手机号：</h4>
                                    <input type="text" value="Type here" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Type here';}" required="">
                                </div>
                                <div class="sign-in">
                                    <h4>密码：</h4>
                                    <input type="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}" required="">
                                    <a href="#">忘记密码？</a>
                                </div>
                                <div class="single-bottom">
                                    <input type="checkbox"  id="brand" value="">
                                    <label for="brand"><span></span>是否记住用户</label>
                                </div>
                                <div class="sign-in">
                                    <input type="submit" value="登陆" >
                                </div>
                            </form>
                        </div>
                        <div class="clearfix"></div>

                        <%--qq微信快捷登陆--%>
                        <div class="login-quick">
                            <span style="float: left">快捷登陆</span>
                            <span><div class = "qq"><img src = "images/QQ.png" style = "width:30px;height:30px"  ></div>
              <div class = "weixin"><img src = "images/weixin.png" style = "width:30px;height:30px"></div></span>
                        </div>
                        <%--//qq微信快捷登陆--%>
                    </div>


                    <p>登录即表示您同意我们的以及 <a href="#">条款和条件</a><a href="#">以及隐私政策</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- //login -->
</body>
</html>
