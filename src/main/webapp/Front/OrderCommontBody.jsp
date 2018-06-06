<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/19/019
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>评论</title>
</head>
<body>
<div class="main findStore">
    <div class="left">
        <form onsubmit="return false;" id="form1">
            <div class="comTitle">
                <input type="hidden" value="10019" name="shop_id">
                <span>点评&nbsp;<a class="da1e3a">南昌梦时代</a></span>
                <select>
                    <option value="0">选择其他门店</option>
                    <option value="10019">文晖路店</option>
                    <option value="10000">新塘路店</option>
                    <option value="10006">浙大紫金港店</option>
                    <option value="10002">高技街店</option>
                    <option value="10023">近江店</option>
                    <option value="10020">滨江店</option>
                    <option value="10004">天一店</option>
                    <option value="10005">徐家汇店</option>
                    <option value="10007">福田店</option>
                    <option value="10008">天河店</option>
                    <option value="10015">五道口店</option>
                    <option value="10142">长安长福店</option>
                    <option value="10141">城西店</option>
                    <option value="10140">长安西路店</option>
                    <option value="10139">东湖店</option>
                    <option value="10138">丰城店</option>
                    <option value="10137">乐清虹桥店</option>
                    <option value="10136">滨江贺田尚城店</option>
                    <option value="10135">浦东新区店</option>
                    <option value="10134">崇福店</option>
                    <option value="10133">敦煌店</option>
                    <option value="10132">河北区店</option>
                    <option value="10131">仙居县店</option>
                    <option value="10130">海天城店</option>
                    <option value="10129">瓶窑店</option>
                    <option value="10128">蒋村花园店</option>
                    <option value="10127">西溪北苑店</option>
                    <option value="10126">桂花西路店</option>
                    <option value="10125">椒江店</option>
                    <option value="10122">下关二小店</option>
                    <option value="10121">鱼台县店</option>
                    <option value="10120">成华店</option>
                    <option value="10118">武隆店</option>
                    <option value="10117">闲林山水店</option>
                    <option value="10116">格尔木店</option>
                    <option value="10115">梁园区店</option>
                    <option value="10114">厚街镇店</option>
                    <option value="10112">恭城县店</option>
                    <option value="10111">禄劝县店</option>
                    <option value="10110">西溪花园店</option>
                    <option value="10109">南浔店</option>
                    <option value="10107">三里亭店</option>
                    <option value="10106">宝安店</option>
                    <option value="10105">星桥店</option>
                    <option value="10104">三墩北甲来路店</option>
                    <option value="10103">罗湖店</option>
                    <option value="10102">江南摩尔店</option>
                    <option value="10101">仓前店</option>
                    <option value="10099">怀化店</option>
                    <option value="10098">江门店</option>
                    <option value="10097">喜洲店</option>
                    <option value="10096">滨江春晓路店</option>
                    <option value="10095">香格里拉店</option>
                    <option value="10094">塘栖镇店</option>
                    <option value="10093">怀仁店</option>
                    <option value="10092">水岸城店</option>
                    <option value="10091">洛阳店</option>
                    <option value="10090">五华店</option>
                    <option value="10089">乐清店</option>
                    <option value="10088">亲亲家园店</option>
                    <option value="10087">金沙店</option>
                    <option value="10086">霞浦店</option>
                    <option value="10084">桐乡店</option>
                    <option value="10078">扬中店</option>
                    <option value="10076">长治店</option>
                    <option value="10075">临安店</option>
                    <option value="10074">高新店</option>
                    <option value="10067">揭阳店</option>
                    <option value="10065">九堡店</option>
                    <option value="10063">邹平店</option>
                    <option value="10062">范江岸店</option>
                    <option value="10061">宜春店</option>
                    <option value="10060">温江店</option>
                    <option value="10058">海口店</option>
                    <option value="10057">昌宁店</option>
                    <option value="10056">闲林店</option>
                    <option value="10053">下关泰安店</option>
                    <option value="10052">下关苍山店</option>
                    <option value="10051">余杭总部店</option>
                    <option value="10050">下沙文渊店</option>
                    <option value="10049">江城店</option>
                    <option value="10046">祥云店</option>
                    <option value="10045">北景园店</option>
                    <option value="10043">伊宁店</option>
                    <option value="10041">长沙店</option>
                    <option value="10040">袍江店</option>
                    <option value="10039">高新店</option>
                    <option value="10038">文三西路店</option>
                    <option value="10035">三墩店</option>
                    <option value="10034">柯桥店</option>
                    <option value="10033">德清店</option>
                    <option value="10032">老余杭店</option>
                    <option value="10026">萧山店</option>
                    <option value="10017">长兴店</option>
                    <option value="10014">富阳店</option>
                    <option value="10013">临平店</option>
                </select>

            </div>
            <p class="tiyan">您的体验对其他人有很大的帮助，请详细描述一下。</p>
            <ul class="star">
                <li>
                    <input type="radio" name="reviews_grade" checked="checked" value="5"><span>5分</span><img src="/images/order_comment_img/icon_star_5.gif" alt="" data-img="/images/order_comment_img/icon_star_5.gif"></li>
                <li>
                    <input type="radio" name="reviews_grade" value="4"><span>4分</span><img src="/images/order_comment_img/icon_star_4.gif" alt="" data-img="/images/order_comment_img/icon_star_4.gif"></li>
                <li>
                    <input type="radio" name="reviews_grade" value="3"><span>3分</span><img src="/images/order_comment_img/icon_star_3.gif" alt="" data-img="/images/order_comment_img/icon_star_3.gif"></li>
                <li>
                    <input type="radio" name="reviews_grade" value="2"><span>2分</span><img src="/images/order_comment_img/icon_star_2.gif" alt="" data-img="/images/order_comment_img/icon_star_2.gif"></li>
                <li>
                    <input type="radio" name="reviews_grade" value="1"><span>1分</span><img src="/images/order_comment_img/icon_star_1.gif" alt="" data-img="/images/order_comment_img/icon_star_1.gif"></li>
            </ul>
            <textarea class="area" name="reviews_content"> </textarea>
            <p class="imgMsg">上传晒单图片（最多可传5张，每张大小不超过1M）</p>
            <p class="upload">
                <span><img src="/images/order_comment_img/yanjing1.jpg" data-img=""></span>
                <span><img src="/images/order_comment_img/yanjing2.jpg" data-img=""></span>
                <span><img src="/images/order_comment_img/yanjing3.jpg" data-img=""></span>
                <span><img src="/images/order_comment_img/yanjing4.jpg" data-img=""></span>
                <span><img src="/images/order_comment_img/yanjing5.jpg" data-img=""></span>

                <input type="file" id="upload" name="imagesup" multiple="multiple" style="left: 350px;">
                <input type="button" value="上传照片" style="left: 350px;">
                <input name="images" class="img" type="hidden">
                <input name="imagelist" class="img" type="hidden">
                <input name="imagelist" class="img" type="hidden">
                <input name="imagelist" class="img" type="hidden">
                <input name="imagelist" class="img" type="hidden">
            </p>

            <button class="btn-post">提交评价</button>
        </form>
    </div>
    <div class="storeTop">
        <a href="/shop/shop-10019.html">
            <p><img src="https://img.yichao.cn:442/uploads/shop/F5C3383E268FDF5BA1A2C3AD74929AAEC9F96A3E.jpg"
                    alt="" data-img="https://img.yichao.cn:442/uploads/shop/F5C3383E268FDF5BA1A2C3AD74929AAEC9F96A3E.jpg"></p>
            <p>南昌梦时代</p>
            <p>服务电话：400-675-2788</p>
            <p>营业时间：10:00-20:00</p>
            <p>门店地址：南昌梦时代B栋</p>
        </a>
    </div>
    <div class="right">
        <p>78056人</p>
        <p>已预约免费验光试戴</p>
        <p>南昌实体店</p>
        <p><button data-city="5" id="10019">预约到店</button></p>
        <p>免费验光再减10</p>
    </div>
</div>
<div class="findShopTitle main">
    南昌梦时代
</div>
</body>
</html>
