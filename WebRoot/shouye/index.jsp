<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../jiazai/jcpeizhibaohan.jsp"%>
<%
	Jcpeizhi newJcpeizhi = (Jcpeizhi)session.getAttribute("jcpeizhi");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=newJcpeizhi.getJcpeizhiName()%></title>
<style>
body{background: #fff url(<%=basePath%>shouye/image/shouye.jpg) 50% 0 no-repeat;margin:0;padding:0;}
#login{width:420px;height:380px;position:absolute;left:50%;top:50%;font-size:12px;line-height:24px;margin:-222px auto auto -210px;background:#fbfbfb;}
#login .logo{height:38px; text-align:center; font-size:30px; font-weight:bold; color:#4c91d1; margin-top:40px;}
#login .main{height:330px;background:#fff;overflow:hidden; border:#CCC 1px solid;box-shadow: 0 0 3px #ccc;}
#login .copyright{height:26px;line-height:20px;text-align:center;font-family:arial;color:#bbb;}
#login .copyright a{color:#bbb;text-decoration:none;}
#login .copyright a:hover{color:#f60;}

#login .main form{margin:50px 62px 0 62px;}
#login .main form ul{margin:0;padding:0;list-style:none;}
#login .main form ul li{font-size:14px;color:#555;margin-bottom:18px;padding:0;position:relative;}
#login .main form ul li img{vertical-align:middle;cursor:pointer;}
#login .main form ul li input{border:1px solid #e5e5e5;padding:12px;border-radius:10px;color:#444;vertical-align:middle;outline:none;}
#login .main form ul li select{border:1px solid #e5e5e5;padding:12px;border-radius:10px;color:#444;vertical-align:middle;outline:none;}
#login .main form ul li .wa{width:160px;}
#login .main form ul li .wb{width:70px;}
#login .main form ul li .bnt{width:292px;border:0;background:#4C91D1;color:#fff;border-radius:4px;padding:12px 0;margin:0;font-size:16px;font-family:microsoft yahei;cursor:pointer;}
#login .main form ul li .bnt:hover{background:#09A3DC;}

#login .main form i{width:20px;height:24px;display:block;position:absolute;top:10px;left:74px;border-right:1px solid #e5e5e5;padding-right:8px;font-size:18px;color:#999;}


#login .main .api{text-align:center;color:#999;margin-top:-5px;}
#login .main .api:hover{color:#4C91D1;}
#login .main .api a{color:#666;text-decoration:none;height:16px;line-height:16px;overflow:hidden;font-size:14px;}
#login .main .api a:hover{color:#4C91D1;}
.msg-wrap{margin-left:50px;}</style>
</head>
<script language="javascript">
function checklogin()
{
  if(document.login.userName.value=='')
     {alert('请输入帐户');
      document.login.userName.focus();
      return false
    }
  if (document.login.password.value=='')
   {alert('请输入密码');
    document.login.password.focus();
    return false
   }
}
</script>
<body>
<div id="login">
        <div class="logo"><%=newJcpeizhi.getJcpeizhiName()%></div>
        <div class="main">
            <form class="formname" action="<%=basePath%>login" name="login" method="post" onSubmit="return checklogin();">
            <ul>
                <li>用户名： <input class="wa" type="text" name="userName" id="userName"/></li>
                <li>密　码： <input class="wa" type="password" name="password" id="password"/></li>
                <li>类　型： <select name="loginType" class="wa">
							<option value="admin">管理员</option>
							<option value="user"><%=newJcpeizhi.getUserBieming()%></option>
							<option value="yonghu"><%=newJcpeizhi.getYonghuBieming()%></option>
						</select>
               	</li>
               	<li><input type="submit" value="登 录" class="bnt" style="width:30%"  onclick = "return validate()"/>
               	&nbsp;<a href="<%=basePath%>user/zhuceuser.jsp"><input type="button" value="房东注册" class="bnt" style="width:30%" /></a>
               	&nbsp;<a href="<%=basePath%>yonghu/zhuceyonghu.jsp"><input type="button" value="用户注册" class="bnt" style="width:30%" /></a>
               	</li>
                <li><font color="red">${error }</font></li>
            </ul>
            </form>
            
    </div>
    <div class="copyright"><br><%=newJcpeizhi.getJcpeizhiName()%></div>
</div>
<script>

</script>
</body>
</html>