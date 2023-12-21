<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String sjxiaoxipage = "1";
	String sjxiaoxirows = "4";
	String sjxiaoxiName = "";
	String sjxiaoxiId = (String) request.getParameter("sjxiaoxiId");
	
	StringBuffer sjxiaoxiparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isNotEmpty(sjxiaoxipage)) {
		sjxiaoxiparam.append("&page=" + sjxiaoxipage);
	}
	if (StringUtil.isNotEmpty(sjxiaoxirows)) {
		sjxiaoxiparam.append("&rows=" + sjxiaoxirows);
	}
	if (StringUtil.isNotEmpty(sjxiaoxiName)) {
		sjxiaoxiparam.append("&sjxiaoxiName=" + sjxiaoxiName);
	}
	if (StringUtil.isNotEmpty(sjxiaoxiId)) {
		sjxiaoxiparam.append("&sjxiaoxiId=" + sjxiaoxiId);
	}

 %>
<%
	List<Sjxiaoxi> sjxiaoxis = new ArrayList<Sjxiaoxi>();
	Sjxiaoxi sjxiaoxi = null;
	int sjxiaoxisshuliang = 0;
	String sjxiaoxipath = request.getContextPath();
	String sjxiaoxibasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+sjxiaoxipath+"/";
	
	String sjxiaoxiurl = sjxiaoxibasePath + "getSjxiaoxis";
	JSONObject sjxiaoxiresult = PostUtil.sendPost(sjxiaoxiurl, sjxiaoxiparam.toString());
	if(sjxiaoxiresult!=null){
		JSONArray sjxiaoxijsonArray = (JSONArray)sjxiaoxiresult.get("rows");
		//System.out.println(sjxiaoxijsonArray);
		sjxiaoxis = JSONArray.toList(sjxiaoxijsonArray, new Sjxiaoxi(), new JsonConfig());
		if(sjxiaoxis.size()>0){
			sjxiaoxi = sjxiaoxis.get(0);
		}
		//for(int i = 0;i < sjxiaoxis.size();i++){
		//	System.out.println(sjxiaoxis.get(i).getSjxiaoxiName());
		//}
		sjxiaoxisshuliang = (Integer)sjxiaoxiresult.get("total");
		//System.out.println(sjxiaoxisshuliang);
	}
%>