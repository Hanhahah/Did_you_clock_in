<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><html lang="zxx">
<!DOCTYPE html><!--[if IE 8]><html lang="en" class="ie8 no-js"><![endif]--><!--[if IE 9]><html lang="en" class="ie9 no-js"><![endif]--><!--[if!IE]><!-->
<html lang="zxx">
<!--<![endif]--><!-- Begin Head -->
<head>
  <title>clock</title>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="author" content="">
  <meta name="MobileOptimized" content="320">
  <!--Start Style -->
  <link rel="stylesheet" type="text/css" href="assets/css/fonts.css">
  <link rel="stylesheet" href="https://www.jq22.com/jquery/bootstrap-4.2.1.css">
  <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="assets/css/icofont.min.css">
  <link rel="stylesheet" href="assets/css/swiper.min.css">
  <!--Page Specific -->
  <link rel="stylesheet" type="text/css" href="assets/css/nice-select.css">
  <!--Custom Style -->
  <link rel="stylesheet" type="text/css" href="assets/css/style.css">
  <link rel="stylesheet" id="theme-change" type="text/css" href="#">
  <!-- Favicon Link -->
  <link rel="shortcut icon" type="image/png" href="assets/images/favicon.png">
</head>
<body>
<div class="loader">
  <div class="spinner"><img src="assets/images/loader.gif" alt=""/></div>
</div>
<!-- Main Body -->
<div class="page-wrapper"><!-- Header Start -->
  <header class="header-wrapper main-header">
    <div class="header-inner-wrapper">
      <div class="logo-wrapper"><a href="if_clockServlet" class="admin-logo"><img src="assets/images/logo.png" alt=""></a></div>

    </div>
  </header>
  <!-- Sidebar Start -->
  <aside class="sidebar-wrapper">
    <div class="side-menu-wrap">
      <ul class="main-menu">
        <li><a href="javascript:void(0);" class="active"><span class="icon-menu feather-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home">
            <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
            <polyline points="9 22 9 12 15 12 15 22"></polyline>
          </svg>
          </span><span class="menu-text">打卡 </span></a>
          <ul class="sub-menu">
            <li><a href="if_clockServlet"><span class="icon-dash"></span><span class="menu-text">今日打卡 </span></a></li>
            <li><a href="detailServlet"><span class="icon-dash"></span><span class="menu-text">打卡详情 </span></a></li>
          </ul>
        </li>
        <li><a href="javascript:void(0);"><span class="icon-menu feather-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-map">
            <polygon points="1 6 1 22 8 18 16 22 23 18 23 2 16 6 8 2 1 6"></polygon>
            <line x1="8" y1="2" x2="8" y2="18"></line>
            <line x1="16" y1="6" x2="16" y2="22"></line>
          </svg>
          </span><span class="menu-text">加入/创建组织</span></a>
          <ul class="sub-menu">
            <li><a href="join.jsp"><span class="icon-dash"></span><span class="menu-text">加入组织 </span></a></li>
            <li><a href="CreateServlet"><span class="icon-dash"></span><span class="menu-text">创建组织 </span></a></li>
          </ul>
        </li>
        <li><a href="SelectOrgServlet"><span class="icon-menu feather-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-wind">
            <path d="M9.59 4.59A2 2 0 1 1 11 8H2m10.59 11.41A2 2 0 1 0 14 16H2m15.73-8.27A2.5 2.5 0 1 1 19.5 12H2"></path>
          </svg>
          </span><span class="menu-text">我创建的 </span></a>

        </li>
        <li><a href="MyjoinServlet"><span class="icon-menu feather-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-send">
            <line x1="22" y1="2" x2="11" y2="13"></line>
            <polygon points="22 2 15 22 11 13 2 9 22 2"></polygon>
          </svg>
          </span><span class="menu-text">我加入的 </span></a>

        </li>
        <li><a href="inboxServlet"><span class="icon-menu feather-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-package">
            <line x1="16.5" y1="9.4" x2="7.5" y2="4.21"></line>
            <path d="M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z"></path>
            <polyline points="3.27 6.96 12 12.01 20.73 6.96"></polyline>
            <line x1="12" y1="22.08" x2="12" y2="12"></line>
          </svg>
         </span><span class="menu-text">收件箱 </span></a></li>
        <li><a href="UserServlet"><span class="icon-menu feather-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-calendar">
            <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
            <line x1="16" y1="2" x2="16" y2="6"></line>
            <line x1="8" y1="2" x2="8" y2="6"></line>
            <line x1="3" y1="10" x2="21" y2="10"></line>
          </svg>
         </span><span class="menu-text">我的 </span></a></li>


      </ul>
    </div>
  </aside>
  <!-- Container Start -->
  <div class="page-wrapper">
    <div class="main-content"><!-- Page Title Start -->
     <div class="rg_center">
          <div class="card-header">
              <h4 class="card-title mb-0">加入组织</h4>
              <div class="card-options"><a class="card-options-collapse" href="javascript:;" data-bs-toggle="card-collapse" data-bs-original-title="" title=""><i class="fe fe-chevron-up"></i></a><a class="card-options-remove" href="javascript:;" data-bs-toggle="card-remove" data-bs-original-title="" title=""><i class="fe fe-x"></i></a></div>
            </div>
            <div class="card-body">
              <form action="JoinServlet" method="post">
                <div class="mb-3">
                  <label class="form-label">邀请码</label>
                  <input type="text" name="invite" class="form-control"  data-bs-original-title="" title="">
                </div>
                <div class="form-footer">
                  <button type="submit" class="btn btn-primary squer-btn" data-bs-original-title="" title="" >确定</button>
                </div>
              </form>
            </div>
    </div>
      <!-- Products view Start -->
      <div class="ad-footer-btm">
        <p>Copyright 2021 © SplashDash All Rights By <a href="www.jq22.com/index.html">jq22</a>.</p>
      </div>
    </div>
  </div>
</div>
<!-- Script Start -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/popper.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/swiper.min.js"></script>
<!-- Page Specific -->
<script src="assets/js/nice-select.min.js"></script>
<!-- Custom Script --><script src="assets/js/custom.js"></script>
</body>
</html>