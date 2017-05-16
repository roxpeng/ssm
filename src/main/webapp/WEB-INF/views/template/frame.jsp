<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>  
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<html>
<head>
<title><decorator:title /> </title>
 <decorator:head /> 
 
    <!--Basic Styles-->
    <link href="static/css/bootstrap.min.css"rel="stylesheet" />
    <link href="static/css/font-awesome.min.css" rel="stylesheet" />
    <!--Beyond styles-->
    <link id="beyond-link" href="static/css/beyond.min.css"  rel="stylesheet" type="text/css" />
    <!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
    <script src="static/js/skins.min.js"></script>
</head>

<body>
	 <!-- Loading Container -->
    <div class="loading-container">
        <div class="loader"></div>
    </div>
    <!--  /Loading Container -->
    <!-- Navbar -->
    <div class="navbar">
        <div class="navbar-inner">
            <div class="navbar-container">
                <!-- Navbar Barnd -->
                <div class="navbar-header pull-left">
                    <a href="#" class="navbar-brand">
                        <small>
                            <img src="static/img/logo.png" alt="" />
                        </small>
                    </a>
                </div>
                <!-- /Navbar Barnd -->
                <!-- Sidebar Collapse -->
                <div class="sidebar-collapse" id="sidebar-collapse">
                    <i class="collapse-icon fa fa-bars"></i>
                </div>
                <!-- /Sidebar Collapse -->
                <!-- Account Area and Settings --->
                <div class="navbar-header pull-right">
                    <div class="navbar-account">
                        <ul class="account-area">
                           
                            <li>
                                <a class="login-area dropdown-toggle" data-toggle="dropdown">
                                    <div class="avatar" title="View your public profile">
                                        <img src="static/img/avatars/divyia.jpg" >
                                    </div>
                                    <section>
                                        <h2><span class="profile"><span><shiro:principal/></span></span></h2>
                                    </section>
                                </a>
                              <ul class="pull-right dropdown-menu dropdown-arrow dropdown-login-area">
	                                <!-- <li class="dropdown-footer">
	                                	 <a href="javascript:modifyPwd()">修改密码</a>
	                               	</li> -->
                                    <li class="dropdown-footer">
                                        <a href="${ctx}/logout">注销</a>
                                    </li>
                                </ul>
                            </li>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="main-container container-fluid">
        <div class="page-container">

            <div class="page-sidebar" id="sidebar">
                <div class="sidebar-header-wrapper">
                    <input type="text" class="searchinput" />
                    <i class="searchicon fa fa-search"></i>
                    <div class="searchhelper">查询</div>
                </div>
                <ul class="nav sidebar-menu">
                    <li class="active">
                        <a href="${ctx}/welcome.jsp" >
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 首页 </span>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="menu-dropdown">
                            <i class="menu-icon fa fa-desktop"></i>
                            <span class="menu-text"> 系统 </span>
                            <i class="menu-expand"></i>
                        </a>

                        <ul class="submenu">
                            <li>
                                <a href="${ctx}/system/user/showUser" >
                                    <span class="menu-text">用户</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="menu-dropdown">
                                    <span class="menu-text">
                                        	角色
                                    </span>
                                    <i class="menu-expand"></i>
                                </a>

                                <ul class="submenu">
                                    <li>
                                        <a href="${ctx}/system/role/add" >
                                            <i class="menu-icon fa fa-rocket"></i>
                                            <span class="menu-text">新增角色</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="${ctx}/system/role/add" >
                                            <i class="menu-icon glyphicon glyphicon-stats"></i>
                                            <span class="menu-text">修改角色</span>
                                        </a>
                                    </li>
                                  
                                </ul>
                            </li>
                            <li>
                                <a href="${ctx}/system/resource" >
                                    <span class="menu-text">资源</span>
                                </a>
                            </li>
                           
                           
                            
                        </ul>
                    </li>
                    <!--Tables-->
                    <li>
                        <a href="#" class="menu-dropdown">
                            <i class="menu-icon fa fa-table"></i>
                            <span class="menu-text"> 展示页面 </span>

                            <i class="menu-expand"></i>
                        </a>

                        <ul class="submenu">
                            <li>
                                <a href="${ctx}/system/role/add" >
                                    <span class="menu-text">基础</span>
                                </a>
                            </li>
                            <li>
                                <a href="${ctx}/system/role/add" >
                                    <span class="menu-text">功能</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                   
                </ul>
            </div>
         
            <div class="page-content">
                <div class="page-breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">首页</a>
                        </li>
                        <li class="active">模板</li>
                    </ul>
                </div>
                <div class="page-header position-relative">
                    <div class="header-title">
                        <h1>
                            	模板页面
                        </h1>
                    </div>
                    <!--Header Buttons-->
                    <div class="header-buttons">
                        <a class="sidebar-toggler" href="#">
                            <i class="fa fa-arrows-h"></i>
                        </a>
                        <a class="refresh" id="refresh-toggler" href="">
                            <i class="glyphicon glyphicon-refresh"></i>
                        </a>
                        <a class="fullscreen" id="fullscreen-toggler" href="#">
                            <i class="glyphicon glyphicon-fullscreen"></i>
                        </a>
                    </div>
                </div>
                <decorator:body />
            </div>

        </div>

    </div>

    <!--Basic Scripts-->
    <script src="static/js/jquery.min.js" ></script>
    <script src="static/js/slimscroll/jquery.slimscroll.min.js" ></script>
    
    <script src="static/js/bootstrap.min.js" ></script>

    <!--Beyond Scripts-->
    <script src="static/js/beyond.js"></script>

    
</body>
</html>  