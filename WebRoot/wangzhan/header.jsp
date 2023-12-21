<%@ page language="java" contentType="text/html; charset=utf-8"  import="com.model.*,com.util.*,java.util.List,java.util.ArrayList"  pageEncoding="utf-8"%>
   
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	// 权限验证
	Yonghu yonghu = (Yonghu)session.getAttribute("yonghu");
	int yonghuId = 0;
	if(yonghu!=null){
		yonghuId = yonghu.getYonghuId();
	}
%>
<%@ include file="../jiazai/jcpeizhibaohan.jsp"%>
<%
	Jcpeizhi newJcpeizhi = (Jcpeizhi)session.getAttribute("jcpeizhi");
%>
<%@ include file="../jiazai/headerbaohan.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=newJcpeizhi.getJcpeizhiName()%></title>
<script type="text/javascript" src="<%=basePath %>jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath %>wangzhan/js/jquery.glide.js"></script>
<script type="text/javascript" src="<%=basePath %>wangzhan/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>wangzhan/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>wangzhan/css/glide.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>wangzhan/css/style.css">
</head>
<body>
    
    <header>

        <div class="topBox">
            <div class="borderBottom">
                <div class="container">
                    <div class="welcomeBox">欢迎光临<%=newJcpeizhi.getJcpeizhiName()%></div>
                    <div class="languageBox">
                        <%if(yonghuId==0){%>
    					<a href="<%=basePath %>shouye/index.jsp">登录注册</a>
					    <%}else{%>
					    <a href="<%=basePath %>houtai/yonghuMain.jsp">个人中心</a>
					    <a href="<%=basePath %>wangzhan/shuju/shujubiaoqian.jsp?yonghuId=<%=yonghuId %>">推荐<%=newJcpeizhi.getShujuBieming() %></a>
					    <%}%>
                        <a href="<%=basePath %>wangzhan/sjxiaoxishow.jsp?sjxiaoxiId=1">关于我们</a>
                        <a href="<%=basePath %>">网站首页</a>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-8 logo">
                    <a href="<%=basePath %>">
                    <%=newJcpeizhi.getJcpeizhiName()%>
                    </a></div>

                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="address"></div>
                    </div>

                    <div class="col-xs-6 col-sm-3 col-md-2">
                        <div class="tel">
                            <img src="<%=basePath %>wangzhan/images/tel.gif" alt="" /><br />400-8888-8888
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <nav class="navbar navbar-static-top navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<%=basePath %>"></a>             
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                      <li><a href="<%=basePath %>">网站首页</a></li>
                      <li><a href="<%=basePath %>wangzhan/sjxiaoxi/sjxiaoxishow.jsp?sjxiaoxiId=1">关于我们</a></li>
			          <li><a href="<%=basePath %>wangzhan/user/userlist.jsp"><%=newJcpeizhi.getUserBieming() %>信息</a></li>
			          <li><a href="<%=basePath %>wangzhan/shuju/shujulist.jsp"><%=newJcpeizhi.getShujuBieming() %>信息</a></li>
			          <li><a href="<%=basePath %>wangzhan/gonggao/gonggaolist.jsp"><%=newJcpeizhi.getGonggaoBieming() %>信息</a></li>
			          <li><a href="<%=basePath %>wangzhan/sjxiaoxi/sjxiaoxishow.jsp?sjxiaoxiId=2">联系我们</a></li>
			          <%if(yonghuId==0){%>
				      <li><a href="<%=basePath %>shouye/index.jsp">登录注册</a></li>
				      <%}else{%>
				      <li><a href="<%=basePath %>houtai/yonghuMain.jsp">个人中心</a></li>
				      <%}%>

                    </ul>
                </div>
            </div>
        </nav>

    </header>
    
    <!-- Banner -->
    <div class="banner homeBanner">
        <div class="slider">
            <ul class="slider__wrapper">
                <li class="slider__item"><a title="1" href="<%=basePath %>" style="background-image:url(<%=basePath %>wangzhan/images/home_banner01.png)"><img src="<%=basePath %>wangzhan/images/banner-2.png" /></a></li>
                <li class="slider__item"><a title="1" href="<%=basePath %>" style="background-image:url(<%=basePath %>wangzhan/images/home_banner02.png)"><img src="<%=basePath %>wangzhan/images/banner-2.png" /></a></li>
                <li class="slider__item"><a title="2" href="<%=basePath %>" style="background-image:url(<%=basePath %>wangzhan/images/home_banner03.png)"><img src="<%=basePath %>wangzhan/images/banner-2.png" /></a></li>
            </ul>
        </div>
    </div>
    
    <script type="text/javascript">
        var glide = $('.slider').glide();
    </script>