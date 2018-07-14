<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jx.lczj.model.T_goods" %><%--
  Created by IntelliJ IDEA.
  User: 14260
  Date: 2018/7/8
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- banner -->

<link rel="stylesheet" type="text/css" href="css/Ranking.css">
<script type="text/javascript" src="js/j-accordin.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('.accordion').jaccordion();
    });
</script>

<div class="banner-grid">
    <div id="visual">
        <div class="slide-visual">
            <!-- Slide Image Area (1000 x 424) -->
            <ul class="slide-group">
                <li><img class="img-responsive" src="images/ba1.jpg" alt="Dummy Image" /></li>
                <li><img class="img-responsive" src="images/ba2.jpg" alt="Dummy Image" /></li>
                <li><img class="img-responsive" src="images/ba3.jpg" alt="Dummy Image" /></li>
            </ul>

            <!-- Slide Description Image Area (316 x 328) -->
            <div class="script-wrap">
                <ul class="script-group">
                    <li><div class="inner-script"><img class="img-responsive" src="images/baa1.jpg" alt="Dummy Image" /></div></li>
                    <li><div class="inner-script"><img class="img-responsive" src="images/baa2.jpg" alt="Dummy Image" /></div></li>
                    <li><div class="inner-script"><img class="img-responsive" src="images/baa3.jpg" alt="Dummy Image" /></div></li>
                </ul>
                <div class="slide-controller">
                    <a href="#" class="btn-prev"><img src="images/btn_prev.png" alt="Prev Slide" /></a>
                    <a href="#" class="btn-play"><img src="images/btn_play.png" alt="Start Slide" /></a>
                    <a href="#" class="btn-pause"><img src="images/btn_pause.png" alt="Pause Slide" /></a>
                    <a href="#" class="btn-next"><img src="images/btn_next.png" alt="Next Slide" /></a>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="clearfix"></div>
    </div>
    <script type="text/javascript" src="js/pignose.layerslider.js"></script>
    <script type="text/javascript">
        //<![CDATA[
        $(window).load(function() {
            $('#visual').pignoseLayerSlider({
                play    : '.btn-play',
                pause   : '.btn-pause',
                next    : '.btn-next',
                prev    : '.btn-prev'
            });
        });
        //]]>
    </script>

</div>
<!-- //banner -->
<!-- content -->
<div class="new_arrivals">
    <div class="container">
        <h3><span>新品 </span>不一样的视觉</h3>
        <p>当你流转在人世间，最大的幸运就是我们，给你带来不一样的视觉</p>

    </div>
</div>

<div class="content-bottom">

    <div class="col-sm-4 content-img-left text-center">
        <div class="content-grid-effect slow-zoom vertical">
            <div class="img-box"><img src="images/NewCenter1.jpg" alt="image" class="img-responsive zoom-img"></div>
            <div class="info-box">
                <div class="info-content simpleCart_shelfItem">
                    <h4>雷迪森</h4>
                    <span class="separator"></span>
                    <p><span class="item_price">500RMB</span></p>
                    <span class="separator"></span>
                    <a class="item_add hvr-outline-out button2" href="#">add to cart </a>
                </div>
            </div>
        </div>
        <div class="col-sm-6-left content-img-right">
            <h3>来自不一样50%<a href="Mens.jsp" ><span >新品上市</span></a>雷迪森</h3>
        </div>
    </div>

    <div class="col-sm-4 content-img-left text-center">
        <div class="col-sm-6-center content-img-right">
            <h3>买一赠一 <span> 活动</span> 暴龙</h3>
        </div>
        <div class="content-grid-effect slow-zoom vertical">
            <div class="img-box"><img src="images/NewCenter2.jpg" alt="image" class="img-responsive zoom-img"></div>
            <div class="info-box">
                <div class="info-content simpleCart_shelfItem">
                    <h4>暴龙</h4>
                    <span class="separator"></span>
                    <p><span class="item_price">1500RMB</span></p>
                    <span class="separator"></span>
                    <a class="item_add hvr-outline-out button2" href="#">add to cart </a>
                </div>
            </div>
        </div>

    </div>

    <div class="col-sm-4 content-img-left text-center">
        <div class="content-grid-effect slow-zoom vertical">
            <div class="accordion_container">

                <div class="buttomtitle">
                    <h2 style="color: #d41a11">热销榜</h2>
                </div>
                <div class="accordion">

            <c:if test="${t_goods!=null}">
                    <c:forEach  items="${t_goods}" var="item" varStatus="status">
                        <c:if test="${status.index==0}">
                            <c:set var="a" value="first"></c:set>
                        </c:if>
                        <c:if test="${status.index==1}">
                            <c:set var="a" value="second"></c:set>
                        </c:if>
                        <c:if test="${status.index==2}">
                            <c:set var="a" value="third"></c:set>
                        </c:if>
                        <c:if test="${status.index==3}">
                            <c:set var="a" value="four"></c:set>
                        </c:if>
                        <c:if test="${status.index==4}">
                            <c:set var="a" value="file"></c:set>
                        </c:if>
                    <div class="${a} ">
                        <div class="content">
                            <img style="width:100%;min-height: 300px;" src="../goods/${item.t_attachments[0].path}"/>
                            <div class="word" style="width:100%;height:20px;padding-left: 30px;text-align: left;" >
                                <p>价格：<em style="color:#F00;"><strong>￥${item.t_goods.price}</strong></em>&nbsp;&nbsp; <a href="#">${item.t_goods.name}</a></p>
                            </div>
                        </div>
                        <div class="tab">
                            <span><img  src="images/tea${status.index+1}.jpg" /></span>

                            <strong><a href="#" target="_blank">${item.t_goods.name}</a></strong>

                        </div>
                    </div>

                    </c:forEach>
            </c:if>
                </div>
            </div>


        </div>

    </div>

</div>

<div class="product-easy">
    <div class="container">
        <script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
        <script type="text/javascript">
        $(document).ready(function () {
            $('#horizontalTab').easyResponsiveTabs({
                type: 'default', //Types: default, vertical, accordion
                width: 'auto', //auto or any width like 600px
                fit: true   // 100% fit in a container
            });

        });

        </script>
        <%--三个眼镜展示--%>
        <%--未对他们类别一一判断是否属于--%>
        <div class="sap_tabs">
            <div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
                <ul class="resp-tabs-list">
                    <c:if test="${t_categories != null}">
                    <c:forEach  items="${t_categories}" var ="shop_item" varStatus="status">
                    <li class="resp-tab-item" aria-controls="tab_item-${status.index}" role="tab"><span>${shop_item.name}</span></li>
                    </c:forEach>
                    </c:if>
                   <a href="../Fronts/newgoods?type=1"> <li class="resp-tab-item" aria-controls="tab_item-3" role="tab"><span>更多</span></li></a>
                </ul>

                <div class="resp-tabs-container">
                    <c:forEach items="${t_shop}" var="shop">
                    <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-${status.index}">
                        <c:forEach items="${shop}" var="sp">
                        <div class="col-md-3 product-men yes-marg">
                            <div class="men-pro-item simpleCart_shelfItem">
                                <div class="men-thumb-item" onclick="">
                                    <img src="../goods/${sp.t_attachments[0].path}" alt="" class="pro-image-front">
                                    <img src="../goods/${sp.t_attachments[0].path}" alt="" class="pro-image-back">
                                    <div class="men-cart-pro">
                                        <div class="inner-men-cart-pro">
                                            <a href="Single.jsp" class="link-product-add-cart">Quick View</a>
                                        </div>
                                    </div>
                                    <span class="product-new-top">New</span>
                                </div>
                                <div class="item-info-product ">
                                    <h4><a href="Single.jsp">${sp.t_goods.name}</a></h4>
                                    <div class="info-product-price">
                                        <span class="item_price">${sp.t_goods.price}</span>
                                    </div>
                                    <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                                </div>
                            </div>
                        </div>
                        </c:forEach>
                        <div class="clearfix"></div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <%--//三个眼镜展示--%>
    </div>
</div>
