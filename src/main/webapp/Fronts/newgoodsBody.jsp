<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/8/008
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<style type="text/css">

    .bg2:hover{
        font-size: 18px;
    }

    .ul-default {
        color: #333;
        background-color: #fff;
        border-color: rgba(255, 159, 52, 0);
    }
    .ul-default:hover,
    .ul-default:focus,
    .ul-default.focus,
    .ul-default:active,
    .ul-default.active,
    .open > .dropdown-toggle.ul-default {
        color: #333;
        background-color: #e6e6e6;
        border-color: #adadad;
    }

</style>

<div class="men-wear">
<div class="container">

    <div class="col-sm-4 products-left">
        <div id="verticalTab" class="css-treeview">
            <h4>眼镜类别</h4>
            <ul class="tabs-list tree-list-pad">
                <c:if test="${categories != null}">
                    <c:forEach  items="${categories}" var ="item" varStatus="status">
                        <li class="tab-item" aria-controls="tab_item-${status.index}" role="tab">
                            <div class="ul-default"><a class="bg2"  href="newgoods?type=${item.category}" style="text-decoration: none"><span>${item.name}</span></a></div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="col-sm-8 products-right">
        <div class="men-wear-top">
            <script src="js/responsiveslides.min.js"></script>
            <script>
                // You can also use "$(window).load(function() {"
                $(function () {
                    // Slideshow 4
                    $("#slider3").responsiveSlides({
                        auto: true,
                        pager: true,
                        nav: false,
                        speed: 500,
                        namespace: "callbacks",
                        before: function () {
                            $('.events').append("<li>before event fired.</li>");
                        },
                        after: function () {
                            $('.events').append("<li>after event fired.</li>");
                        }
                    });
                });
            </script>
            <div id="top" class="callbacks_container">
                <ul class="rslides callbacks callbacks1" id="slider3">
                    <li id="callbacks1_s0" class="" style="display: block; float: none; position: absolute; opacity: 0; z-index: 1; transition: opacity 500ms ease-in-out;">
                        <img style="width: 730px;height: 365px" class="img-responsive" src="images/glasses01.jpg" alt=" ">
                    </li>
                    <li id="callbacks1_s1" class="" style="display: block; float: none; position: absolute; opacity: 0; z-index: 1; transition: opacity 500ms ease-in-out;">
                        <img style="width: 730px;height: 365px" class="img-responsive" src="images/glasses02.jpg" alt=" ">
                    </li>
                    <li id="callbacks1_s2" class="callbacks1_on" style="display: block; float: left; position: relative; opacity: 1; z-index: 2; transition: opacity 500ms ease-in-out;">
                        <img style="width: 730px;height: 365px" class="img-responsive" src="images/glasses03.jpg" alt=" ">
                    </li>
                    <li id="callbacks1_s3" class="" style="display: block; float: none; position: absolute; opacity: 0; z-index: 1; transition: opacity 500ms ease-in-out;">
                        <img style="width: 730px;height: 365px" class="img-responsive" src="images/glasses04.jpg" alt=" ">
                    </li>
                </ul>
                <ul class="callbacks_tabs callbacks1_tabs">
                    <li class="callbacks1_s1">
                        <a href="#" class="callbacks1_s1">1</a>
                    </li>
                    <li class="callbacks1_s2">
                        <a href="#" class="callbacks1_s2">2</a>
                    </li>
                    <li class="callbacks1_s3 callbacks_here">
                        <a href="#" class="callbacks1_s3">3</a>
                    </li>
                    <li class="callbacks1_s4">
                        <a href="#" class="callbacks1_s4">4</a>
                    </li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="clearfix"></div>
    <div id="tabBox" class="single-pro tabs-container">
             <div class="tab-content" aria-labelledby="tab_item-${status.index}">
                <c:forEach items="${shops}" var="shop">
                    <div class="col-md-3 product-men yes-marg">
                        <div class="men-pro-item simpleCart_shelfItem">
                            <div class="men-thumb-item">
                                <img src="../goods/${shop.t_attachments[0].path}" alt="" class="pro-image-front">
                                <img src="../goods/${shop.t_attachments[0].path}" alt="" class="pro-image-back">
                                <div class="men-cart-pro">
                                    <div class="inner-men-cart-pro">
                                        <a href="single.html" class="link-product-add-cart">Quick View</a>
                                    </div>
                                </div>
                                <span class="product-new-top">New</span>

                            </div>
                            <div class="item-info-product ">
                                <h4><a href="single.html">${shop.t_goods.name}</a></h4>
                                <div class="info-product-price">
                                    <span class="item_price">${shop.t_goods.price}</span>
                                    <del>$69.71</del>
                                </div>
                                <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="clearfix"></div>
            </div>
     </div>
    <div class="pagination-grid text-right">
        <ul class="pagination paging">
            <li><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
            <li class="active"><a href="#">1<span class="sr-only">(current)</span></a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
        </ul>
    </div>
</div>
</div>