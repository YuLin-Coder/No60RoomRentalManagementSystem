<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String uxtypepage = "";
	String uxtyperows = "";
	
	StringBuffer uxtypeparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isNotEmpty(uxtypepage)) {
		uxtypeparam.append("&page=" + uxtypepage);
	}
	if (StringUtil.isNotEmpty(uxtyperows)) {
		uxtypeparam.append("&rows=" + uxtyperows);
	}

 %>
<%
	List<Uxtype> uxtypes = new ArrayList<Uxtype>();
	Uxtype uxtype = null;
	int uxtypesshuliang = 0;
	String uxtypepath = request.getContextPath();
	String uxtypebasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+uxtypepath+"/";
	
	String uxtypeurl = uxtypebasePath + "getUxtypes";
	JSONObject uxtyperesult = PostUtil.sendPost(uxtypeurl, uxtypeparam.toString());
	if(uxtyperesult!=null){
		JSONArray uxtypejsonArray = (JSONArray)uxtyperesult.get("rows");
		//System.out.println(uxtypejsonArray);
		uxtypes = JSONArray.toList(uxtypejsonArray, new Uxtype(), new JsonConfig());
		if(uxtypes.size()>0){
			uxtype = uxtypes.get(0);
		}
		for(int i = 0;i < uxtypes.size();i++){
			System.out.println(uxtypes.get(i).getUxtypeName());
		}
		uxtypesshuliang = (Integer)uxtyperesult.get("total");
		System.out.println(uxtypesshuliang);
	}
%>