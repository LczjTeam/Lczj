<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
  <script src="js/jquery.easing.min.js"></script>
  <% request.setCharacterEncoding("utf-8");%>
  <%--自己新加的--%>
  <link href="css/NewStyle.css" rel="stylesheet" type="text/css" media="all">
  <script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
  <script type="text/javascript" src="js/Newbootstrap.js"></script>

  <%--自己新加的--%>
  <style>

  </style>
</head>
<body>
<%--头部--%>
<jsp:include page="Header.jsp"></jsp:include>
<%--头部--%>
<%--homeBody--%>
<jsp:include page="HomeBody.jsp"></jsp:include>
<%--homeBody--%>
<!-- //footer -->
<jsp:include page="Footer.jsp"></jsp:include>
<!-- login -->
<jsp:include page="Login.jsp"></jsp:include>
<!-- //login -->
<%--返回顶部--%>
<jsp:include page="BackToTop.jsp"></jsp:include>
<%--返回顶部--%>
</body>
</html>
