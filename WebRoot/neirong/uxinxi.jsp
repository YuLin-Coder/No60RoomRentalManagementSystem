<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String uxinxipage = "1";
	String uxinxirows = "4";
	String uxinxiName = "";
	String uxinxiId = (String) request.getParameter("uxinxiId");
	
	StringBuffer uxinxiparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isNotEmpty(uxinxipage)) {
		uxinxiparam.append("&page=" + uxinxipage);
	}
	if (StringUtil.isNotEmpty(uxinxirows)) {
		uxinxiparam.append("&rows=" + uxinxirows);
	}
	if (StringUtil.isNotEmpty(uxinxiName)) {
		uxinxiparam.append("&uxinxiName=" + uxinxiName);
	}
	if (StringUtil.isNotEmpty(uxinxiId)) {
		uxinxiparam.append("&uxinxiId=" + uxinxiId);
	}

 %>
<%
	List<Uxinxi> uxinxis = new ArrayList<Uxinxi>();
	Uxinxi uxinxi = null;
	int uxinxisshuliang = 0;
	String uxinxipath = request.getContextPath();
	String uxinxibasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+uxinxipath+"/";
	
	String uxinxiurl = uxinxibasePath + "getUxinxis";
	JSONObject uxinxiresult = PostUtil.sendPost(uxinxiurl, uxinxiparam.toString());
	if(uxinxiresult!=null){
		JSONArray uxinxijsonArray = (JSONArray)uxinxiresult.get("rows");
		//System.out.println(uxinxijsonArray);
		uxinxis = JSONArray.toList(uxinxijsonArray, new Uxinxi(), new JsonConfig());
		if(uxinxis.size()>0){
			uxinxi = uxinxis.get(0);
		}
		//for(int i = 0;i < uxinxis.size();i++){
		//	System.out.println(uxinxis.get(i).getUxinxiName());
		//}
		uxinxisshuliang = (Integer)uxinxiresult.get("total");
		//System.out.println(uxinxisshuliang);
	}
%>