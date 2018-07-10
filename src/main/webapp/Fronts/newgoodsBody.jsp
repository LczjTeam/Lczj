<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/8/008
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<div class="men-wear">
<div class="container">
    <div class="col-sm-4 products-left">
        <div class="css-treeview">
    <h4>眼镜类别</h4>
    <ul class="tree-list-pad">
        <li><input type="checkbox" checked="checked" id="item-0"><label for="item-0"><span></span>男款眼镜</label>
            <ul>
                <li><a href="mens.html">经典</a></li>
                <li><li><a href="mens.html">耐用</a></li>
                <li><li><a href="mens.html">时尚</a></li>
            </ul>
        </li>
        <li><input type="checkbox" id="item-1" checked="checked"><label for="item-1">女款眼镜</label>
            <ul>
                <li><li><a href="mens.html">百搭系列</a></li>
                <li><a href="mens.html">经典复古</a></li>
                <li><a href="mens.html">夏日爆款</a></li>
                <li><a href="mens.html">时尚潮流</a></li></li>
            </ul>
        </li>
        <li><input type="checkbox" checked="checked" id="item-2"><label for="item-2">太阳眼镜</label>
            <ul>
                <li><input type="checkbox" id="item-2-0"><label for="item-2-0">Summer Discount Sales</label>
                    <ul>
                        <li><a href="mens.html">Shirts</a></li>
                        <li><a href="mens.html">Shoes</a></li>
                        <li><a href="mens.html">Pants</a></li>
                    </ul>
                </li>
                <li><input type="checkbox" id="item-2-1"><label for="item-2-1">Exciting Offers</label>
                    <ul>
                        <li><a href="mens.html">Shirts</a></li>
                        <li><a href="mens.html">Shoes</a></li>
                        <li><a href="mens.html">Pants</a></li>
                        <li><a href="mens.html">SunGlasses</a></li>
                    </ul>
                </li>
                <li><input type="checkbox" id="item-2-2"><label for="item-2-2">Flat Discounts</label>
                    <ul>
                        <li><a href="mens.html">Shirts</a></li>
                        <li><a href="mens.html">Shoes</a></li>
                        <li><a href="mens.html">Pants</a></li>
                        <li><a href="mens.html">SunGlasses</a></li>
                    </ul>
                </li>
            </ul>
        </li>
        <li><input type="checkbox" checked="checked" id="item-3"><label for="item-3">光学镜片</label>
            <ul>
                <li><input type="checkbox" id="item-3-0"><label for="item-3-0">夏日爆款</label>
                    <ul>
                        <li><a href="mens.html">Shirts</a></li>
                        <li><a href="mens.html">Shoes</a></li>
                        <li><a href="mens.html">Pants</a></li>
                        <li><a href="mens.html">SunGlasses</a></li>
                    </ul>
                </li>
                <li><input type="checkbox" id="item-3-1"><label for="item-3-1">防紫外线+防尘</label>
                    <ul>
                        <li><a href="mens.html">Shirts</a></li>
                        <li><a href="mens.html">Shoes</a></li>
                        <li><a href="mens.html">Pants</a></li>
                        <li><a href="mens.html">SunGlasses</a></li>
                    </ul>
                </li>
                <li><input type="checkbox" id="item-3-2"><label for="item-3-2">防水雾</label>
                    <ul>
                        <li><a href="mens.html">Shirts</a></li>
                        <li><a href="mens.html">Shoes</a></li>
                        <li><a href="mens.html">Pants</a></li>
                        <li><a href="mens.html">SunGlasses</a></li>
                    </ul>
                </li>
            </ul>
        </li>
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
        <div class="men-wear-bottom">
            <div class="col-sm-4 men-wear-left">
                <img style="width: 233px;height: 226px" class="img-responsive" src="images/men3.jpg" alt=" ">
            </div>
            <div class="col-sm-8 men-wear-right">
                <h4>潮镜嘉年华（eg：特卖活动）</h4>
                <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem
                    accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae
                    ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt
                    explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut
                    odit aut fugit. </p>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="single-pro">
        <div class="col-md-3 product-men">
            <div class="men-pro-item simpleCart_shelfItem">
                <div class="men-thumb-item">
                    <img src="images/student04.jpg" alt="" class="pro-image-front">
                    <img src="images/student04.jpg" alt="" class="pro-image-back">
                    <div class="men-cart-pro">
                        <div class="inner-men-cart-pro">
                            <a href="single.html" class="link-product-add-cart">Quick View</a>
                        </div>
                    </div>
                    <span class="product-new-top">New</span>
                </div>
                <div class="item-info-product ">
                    <h4><a href="single.html">Blazers</a></h4>
                    <div class="info-product-price">
                        <span class="item_price">$45.99</span>
                        <del>$69.71</del>
                    </div>
                    <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 product-men">
            <div class="men-pro-item simpleCart_shelfItem">
                <div class="men-thumb-item">
                    <img src="images/student05.jpg" alt="" class="pro-image-front">
                    <img src="images/student05.jpg" alt="" class="pro-image-back">
                    <div class="men-cart-pro">
                        <div class="inner-men-cart-pro">
                            <a href="single.html" class="link-product-add-cart">Quick View</a>
                        </div>
                    </div>
                    <span class="product-new-top">New</span>
                </div>
                <div class="item-info-product ">
                    <h4><a href="single.html">Sandals</a></h4>
                    <div class="info-product-price">
                        <span class="item_price">$45.99</span>
                        <del>$69.71</del>
                    </div>
                    <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 product-men">
            <div class="men-pro-item simpleCart_shelfItem">
                <div class="men-thumb-item">
                    <img src="images/student06.jpg" alt="" class="pro-image-front">
                    <img src="images/student06.jpg" alt="" class="pro-image-back">
                    <div class="men-cart-pro">
                        <div class="inner-men-cart-pro">
                            <a href="single.html" class="link-product-add-cart">Quick View</a>
                        </div>
                    </div>
                    <span class="product-new-top">New</span>
                </div>
                <div class="item-info-product ">
                    <h4><a href="single.html">Watches</a></h4>
                    <div class="info-product-price">
                        <span class="item_price">$45.99</span>
                        <del>$69.71</del>
                    </div>
                    <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 product-men">
            <div class="men-pro-item simpleCart_shelfItem">
                <div class="men-thumb-item">
                    <img src="images/student07.jpg" alt="" class="pro-image-front">
                    <img src="images/student07.jpg" alt="" class="pro-image-back">
                    <div class="men-cart-pro">
                        <div class="inner-men-cart-pro">
                            <a href="single.html" class="link-product-add-cart">Quick View</a>
                        </div>
                    </div>
                    <span class="product-new-top">New</span>
                </div>
                <div class="item-info-product ">
                    <h4><a href="single.html">Shoes</a></h4>
                    <div class="info-product-price">
                        <span class="item_price">$45.99</span>
                        <del>$69.71</del>
                    </div>
                    <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 product-men yes-marg">
            <div class="men-pro-item simpleCart_shelfItem">
                <div class="men-thumb-item">
                    <img src="images/student08.jpg" alt="" class="pro-image-front">
                    <img src="images/student08.jpg" alt="" class="pro-image-back">
                    <div class="men-cart-pro">
                        <div class="inner-men-cart-pro">
                            <a href="single.html" class="link-product-add-cart">Quick View</a>
                        </div>
                    </div>
                    <span class="product-new-top">New</span>

                </div>
                <div class="item-info-product ">
                    <h4><a href="single.html">Shirts</a></h4>
                    <div class="info-product-price">
                        <span class="item_price">$45.99</span>
                        <del>$69.71</del>
                    </div>
                    <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 product-men yes-marg">
            <div class="men-pro-item simpleCart_shelfItem">
                <div class="men-thumb-item">
                    <img src="images/sunglass01.jpg" alt="" class="pro-image-front">
                    <img src="images/sunglass01.jpg" alt="" class="pro-image-back">
                    <div class="men-cart-pro">
                        <div class="inner-men-cart-pro">
                            <a href="single.html" class="link-product-add-cart">Quick View</a>
                        </div>
                    </div>
                    <span class="product-new-top">New</span>

                </div>
                <div class="item-info-product ">
                    <h4><a href="single.html">Watches</a></h4>
                    <div class="info-product-price">
                        <span class="item_price">$119.99</span>
                        <del>$150.71</del>
                    </div>
                    <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 product-men yes-marg">
            <div class="men-pro-item simpleCart_shelfItem">
                <div class="men-thumb-item">
                    <img src="images/sunglass02.jpg" alt="" class="pro-image-front">
                    <img src="images/sunglass02.jpg" alt="" class="pro-image-back">
                    <div class="men-cart-pro">
                        <div class="inner-men-cart-pro">
                            <a href="single.html" class="link-product-add-cart">Quick View</a>
                        </div>
                    </div>
                    <span class="product-new-top">New</span>

                </div>
                <div class="item-info-product ">
                    <h4><a href="single.html">T shirts</a></h4>
                    <div class="info-product-price">
                        <span class="item_price">$45.99</span>
                        <del>$69.71</del>
                    </div>
                    <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 product-men yes-marg">
            <div class="men-pro-item simpleCart_shelfItem">
                <div class="men-thumb-item">
                    <img src="images/sunglass03.jpg" alt="" class="pro-image-front">
                    <img src="images/sunglass03.jpg" alt="" class="pro-image-back">
                    <div class="men-cart-pro">
                        <div class="inner-men-cart-pro">
                            <a href="single.html" class="link-product-add-cart">Quick View</a>
                        </div>
                    </div>
                    <span class="product-new-top">New</span>

                </div>
                <div class="item-info-product ">
                    <h4><a href="single.html"> Shirts</a></h4>
                    <div class="info-product-price">
                        <span class="item_price">$45.99</span>
                        <del>$69.71</del>
                    </div>
                    <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
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

</html>