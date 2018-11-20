<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xiaoyi
  Date: 2018/5/16
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

  <%--  <style>
        body{
            color: black;
        }
        div {
            display: block;
        }
        a{
            color: black!important;
            text-decoration: none!important;
        }
    </style>--%>
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" href=" css/normalize.css" />
    <link rel="stylesheet" href=" css/styles.min.css" />
    <link href="images/favicon.png" rel="shortcut icon" />
    <link href="css/jplist.min.css" rel="stylesheet" type="text/css" />

    <!-- js -->
    <script src=" js/jquery-1.10.0.min.js"></script>
    <script src=" js/modernizr.min.js"></script>

    <script src="js/jplist.min.js"></script>
    <script>
        $('document').ready(function(){
            $('#demo').jplist({

                itemsBox: '.list'
                ,itemPath: '.list-item'
                ,panelPath: '.jplist-panel'

                //save plugin state
                ,storage: 'localstorage' //'', 'cookies', 'localstorage'
                ,storageName: 'jplist-div-layout'
            });
        });
    </script>


<!-- main content -->
<div id="main-content" class="box" style="background-color: #F0F0F0;margin-top: 0px;" >
    <div class="center" >
        <div id="page-content" class="box" >

            <!-- demo -->
            <div id="demo"  class="box jplist" >

                <!-- data -->
                <div class="list box text-shadow"  id="list1"  >
                    <c:if test="${news != null}">
                        <c:forEach items="${news}" var="item1" varStatus="status">

                            <!-- item -->
                            <div class="list-item box">
                                <!-- img -->
                                <a href="../stories/${item1.t_news.filename}"  target="_blank" >
                                    <div class="img left" >
                                        <img src="../stories/${item1.t_news.photo}" alt="" title=""/>
                                    </div></a>

                                <!-- data -->
                                <div class="block right">

                                    <p class="date" id="date1${status.index}" >${item1.t_news.issue_date}</p>
                                    <script>
                                        $(document).ready(function () {
                                            $("#date1${status.index}").text(formatDateTime($("#date1${status.index}").text()));
                                            function formatDateTime(inputTime) {
                                                var date = new Date(inputTime);
                                                var y = date.getFullYear();
                                                var m = date.getMonth() + 1;
                                                m = m < 10 ? ('0' + m) : m;
                                                var d = date.getDate();
                                                d = d < 10 ? ('0' + d) : d;
                                                var h = date.getHours();
                                                h = h < 10 ? ('0' + h) : h;
                                                var minute = date.getMinutes();
                                                var second = date.getSeconds();
                                                minute = minute < 10 ? ('0' + minute) : minute;
                                                second = second < 10 ? ('0' + second) : second;
                                                return  m + '/' + d + '/'+y ;/*+' '+h+':'+minute+':'+second*/
                                            };
                                        })

                                    </script>
                                    <a href="../stories/${item1.t_news.filename}"  target="_blank" style="text-decoration: none" ><p class="title">${item1.t_news.title}</p></a>
                                    <p class="desc">${item1.t_news.keyword} </p>
                                    <br>
                                    <span>发布人：</span><span class="like"style="font-size: 13px;">${item1.t_admin.name}</span>
                                </div>
                            </div>

                        </c:forEach>
                    </c:if>

                </div>

                <div class="box jplist-no-results text-shadow align-center">
                    <p>No results found</p>
                </div>
                <div style="height: 50px;width: 100%;">
                    <!-- ios button: show/hide panel -->
                    <div class="jplist-ios-button">
                        <i class="fa fa-sort"></i>
                        jPList Actions
                    </div>

                    <!-- panel -->
                    <div class="jplist-panel box panel-bottom">

                        <!-- items per page dropdown -->
                        <div
                                class="jplist-drop-down"
                                data-control-type="drop-down"
                                data-control-name="paging"
                                data-control-action="paging"
                                data-control-animate-to-top="true">

                            <ul>
                                <li><span data-number="3"> 3条 每页 </span></li>
                                <li><span data-number="5"> 5条 每页 </span></li>
                                <li><span data-number="10" data-default="true"> 10条 每页 </span></li>
                                <li><span data-number="all"> 全部 </span></li>
                            </ul>
                        </div>

                        <!-- pagination results -->
                        <div
                                class="jplist-label"
                                data-type="{start} - {end} of {all}"
                                data-control-type="pagination-info"
                                data-control-name="paging"
                                data-control-action="paging">
                        </div>

                        <!-- pagination -->
                        <div
                                class="jplist-pagination"
                                data-control-animate-to-top="true"
                                data-control-type="pagination"
                                data-control-name="paging"
                                data-control-action="paging">
                        </div>

                    </div>
                </div>
            </div>
            <!-- end of demo -->
        </div>
    </div>
</div>




