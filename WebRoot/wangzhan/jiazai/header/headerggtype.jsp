<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String ggtypepage = "";
	String ggtyperows = "";
	
	StringBuffer ggtypeparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isNotEmpty(ggtypepage)) {
		ggtypeparam.append("&page=" + ggtypepage);
	}
	if (StringUtil.isNotEmpty(ggtyperows)) {
		ggtypeparam.append("&rows=" + ggtyperows);
	}

 %>
<%
	List<Ggtype> ggtypes = new ArrayList<Ggtype>();
	Ggtype ggtype = null;
	int ggtypesshuliang = 0;
	String ggtypepath = request.getContextPath();
	String ggtypebasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ggtypepath+"/";
	
	String ggtypeurl = ggtypebasePath + "getGgtypes";
	JSONObject ggtyperesult = PostUtil.sendPost(ggtypeurl, ggtypeparam.toString());
	if(ggtyperesult!=null){
		JSONArray ggtypejsonArray = (JSONArray)ggtyperesult.get("rows");
		//System.out.println(ggtypejsonArray);
		ggtypes = JSONArray.toList(ggtypejsonArray, new Ggtype(), new JsonConfig());
		if(ggtypes.size()>0){
			ggtype = ggtypes.get(0);
		}
		for(int i = 0;i < ggtypes.size();i++){
			System.out.println(ggtypes.get(i).getGgtypeName());
		}
		ggtypesshuliang = (Integer)ggtyperesult.get("total");
		System.out.println(ggtypesshuliang);
	}
%>