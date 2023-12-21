<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% Jcpeizhi newJcpeizhi = (Jcpeizhi)session.getAttribute("jcpeizhi"); %>   
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="user.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=newJcpeizhi.getJcpeizhiName()%></title>

</head>
<body>
<table width="1000" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:10px auto;">
  <tr>
    <td width="10" valign="top">&nbsp;</td>
    <td valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
		<%if(user!=null) {%>
        <td height="" valign="top" bgcolor="#FFFFFF" style="border:1px solid #e0e0e0; padding:10px; line-height:25px;">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="30" align="center" class="line"><%=user.getUserName()%></td>
                  </tr>
                  <tr>
                    <td height="30" align="center" class="line"><%=DateUtil.formatDate(user.getUserDate(),"yyyy-MM-dd HH:mm:ss") %></td>
                  </tr>
                  <tr>
                    <td align="left">
                    <p style="margin-top:10px; text-indent:2em;"><%=user.getUserMark()%></p>
                    </td>
                  </tr>
			</table>
        </td>
		<%}else{ %>
		<td>没有选择<%=newJcpeizhi.getUserBieming() %></td>
		<%} %>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>