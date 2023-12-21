<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.*,java.util.List"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<%
	// 权限验证
	User user = (User)session.getAttribute("user");
	if(user==null){
		System.out.println("没有得到userId");
		response.sendRedirect("shouye/index.jsp");
		return;
	}
	Jcpeizhi newJcpeizhi = (Jcpeizhi)session.getAttribute("jcpeizhi");
	List<Jcbiaoti> jcbiaotis = (List<Jcbiaoti>)session.getAttribute("jcbiaotis");
	List<List<Jcdaohang>> jcdaohangslist = (List<List<Jcdaohang>>)session.getAttribute("jcdaohangslist");
	String userName = user.getUserName();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><%=newJcpeizhi.getJcpeizhiName()%></title>
		<meta name="renderer" content="webkit">
  		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>houtai/muban13/static/admin/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>houtai/muban13/static/admin/css/admin.css"/>
	</head>
	<body>
		<div class="main-layout" id='main-layout'>
			<!--侧边栏-->
			<div class="main-layout-side">
				<div class="m-logo"><%=newJcpeizhi.getJcpeizhiName()%>
				</div>
				<ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
				  <li class="layui-nav-item layui-nav-itemed">
				    <a href="javascript:;"><i class="iconfont">&#xe607;</i>个人信息</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="<%=basePath%>user/user.jsp" data-id='1' data-text="个人信息"><span class="l-line"></span>个人信息</a></dd>
				      <dd><a href="javascript:;" data-url="<%=basePath%>user/usermima.jsp" data-id='2' data-text="修改密码"><span class="l-line"></span>修改密码</a></dd>
				    </dl>
				  </li>
				</ul>
			</div>
			<!--右侧内容-->
			<div class="main-layout-container">
				<!--头部-->
				<div class="main-layout-header">
					<div class="menu-btn" id="hideBtn">
						<a href="javascript:;">
							<span class="iconfont">&#xe60e;</span>
						</a>
					</div>
					
					<ul class="layui-nav" lay-filter="rightNav">
					  <li class="layui-nav-item"><a href="<%=basePath%>" target = "_blank">返回网站</a></li>
					  <li class="layui-nav-item">
					    <a href="javascript:;" data-url="<%=basePath%>user/usermima.jsp" data-id='5' data-text="修改密码">修改密码</a>
					  </li>
					  <li class="layui-nav-item"><a href="<%=basePath%>shouye/tuichu.jsp">退出</a></li>
					</ul>
				
					</div>
				<!--主体内容-->
				<div class="main-layout-body">
					<!--tab 切换-->
					<div class="layui-tab layui-tab-brief main-layout-tab" lay-filter="tab" lay-allowClose="true">
					  <ul class="layui-tab-title">
					    <li class="layui-this welcome">后台主页</li>
					  </ul>
					  <div class="layui-tab-content">
					    <div class="layui-tab-item layui-show" style="background: #f5f5f5;">
					    	<!--1-->
					    	<iframe src="<%=basePath%>shouye/neiye.jsp" width="100%" height="100%" name="iframe" scrolling="auto" class="iframe" framborder="0"></iframe>
					    	<!--1end-->
					    </div>
					  </div>
					</div>
				</div>
			</div>
			<!--遮罩-->
			<div class="main-mask">
				
			</div>
		</div>
		<script type="text/javascript">
			var scope={
				link:'./welcome.html'
			}
		</script>
		<script src="<%=basePath%>houtai/muban13/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>houtai/muban13/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>houtai/muban13/static/admin/js/main.js" type="text/javascript" charset="utf-8"></script>
		
	</body>
</html>