<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String sousuoUxinxipage = (String) request.getParameter("page");
	String sousuoUxinxirows = (String) request.getParameter("rows");
	String sousuoUxinxiName = (String) request.getParameter("uxinxiName");
	String sousuoUxinxiId = (String) request.getParameter("uxinxiId");
	String sousuoUxtypeId = (String) request.getParameter("uxtypeId");
	String uxinxiUserId = (String) request.getParameter("userId");
	String sousuouxinxiType = "0";
	
	StringBuffer sousuoUxinxiparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isEmpty(sousuoUxinxipage)) {
		sousuoUxinxipage = "1";
	}
	sousuoUxinxiparam.append("&page=" + sousuoUxinxipage);
	if (StringUtil.isEmpty(sousuoUxinxirows)) {
		sousuoUxinxirows = "12";
	}
	sousuoUxinxiparam.append("&rows=" + sousuoUxinxirows);
	if (StringUtil.isNotEmpty(sousuoUxinxiName)) {
		sousuoUxinxiparam.append("&uxinxiName=" + sousuoUxinxiName);
	}
	if (StringUtil.isNotEmpty(sousuoUxinxiId)) {
		sousuoUxinxiparam.append("&uxinxiId=" + sousuoUxinxiId);
	}
	if (StringUtil.isNotEmpty(sousuoUxtypeId)) {
		sousuoUxinxiparam.append("&uxtypeId=" + sousuoUxtypeId);
	}
	if (StringUtil.isNotEmpty(uxinxiUserId)) {
		sousuoUxinxiparam.append("&userId=" + uxinxiUserId);
	}
	if (StringUtil.isNotEmpty(sousuouxinxiType)) {
		sousuoUxinxiparam.append("&uxinxiType=" + sousuouxinxiType);
	}

	List<Uxinxi> sousuoUxinxis = new ArrayList<Uxinxi>();
	Uxinxi sousuoUxinxi = null;
	int sousuoUxinxisshuliang = 0;
	String sousuoUxinxipath = request.getContextPath();
	String sousuoUxinxibasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+sousuoUxinxipath+"/";
	
	String sousuoUxinxiurl = sousuoUxinxibasePath + "getUxinxis";
	JSONObject sousuoUxinxiresult = PostUtil.sendPost(sousuoUxinxiurl, sousuoUxinxiparam.toString());
	if(sousuoUxinxiresult!=null){
		JSONArray sousuoUxinxijsonArray = (JSONArray)sousuoUxinxiresult.get("rows");
		//System.out.println(sousuoUxinxijsonArray);
		sousuoUxinxis = JSONArray.toList(sousuoUxinxijsonArray, new Uxinxi(), new JsonConfig());
		if(sousuoUxinxis.size()>0){
			sousuoUxinxi = sousuoUxinxis.get(0);
		}
		//for(int i = 0;i < uxinxis.size();i++){
		//	System.out.println(uxinxis.get(i).getUxinxiName());
		//}
		sousuoUxinxisshuliang = (Integer)sousuoUxinxiresult.get("total");
		//System.out.println(uxinxisshuliang);
	}
	int uxinxirowCount = 1;
	uxinxirowCount = Integer.parseInt(sousuoUxinxirows);
	int uxinxicurrPage = 0;
	uxinxicurrPage = Integer.parseInt(sousuoUxinxipage);
	int uxinxitotalPage = 0;
	uxinxitotalPage = (sousuoUxinxisshuliang + uxinxirowCount - 1)/uxinxirowCount;
%>