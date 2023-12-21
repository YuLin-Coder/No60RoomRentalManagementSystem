<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String sousuoSjxiaoxipage = (String) request.getParameter("page");
	String sousuoSjxiaoxirows = (String) request.getParameter("rows");
	String sousuoSjxiaoxiName = (String) request.getParameter("sjxiaoxiName");
	String sousuoSjxiaoxiId = (String) request.getParameter("sjxiaoxiId");
	
	StringBuffer sousuoSjxiaoxiparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isEmpty(sousuoSjxiaoxipage)) {
		sousuoSjxiaoxipage = "1";
	}
	sousuoSjxiaoxiparam.append("&page=" + sousuoSjxiaoxipage);
	if (StringUtil.isEmpty(sousuoSjxiaoxirows)) {
		sousuoSjxiaoxirows = "10";
	}
	sousuoSjxiaoxiparam.append("&rows=" + sousuoSjxiaoxirows);
	if (StringUtil.isNotEmpty(sousuoSjxiaoxiName)) {
		sousuoSjxiaoxiparam.append("&sjxiaoxiName=" + sousuoSjxiaoxiName);
	}
	if (StringUtil.isNotEmpty(sousuoSjxiaoxiId)) {
		sousuoSjxiaoxiparam.append("&sjxiaoxiId=" + sousuoSjxiaoxiId);
	}


	List<Sjxiaoxi> sousuoSjxiaoxis = new ArrayList<Sjxiaoxi>();
	Sjxiaoxi sousuoSjxiaoxi = null;
	int sousuoSjxiaoxisshuliang = 0;
	String sousuoSjxiaoxipath = request.getContextPath();
	String sousuoSjxiaoxibasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+sousuoSjxiaoxipath+"/";
	
	String sousuoSjxiaoxiurl = sousuoSjxiaoxibasePath + "getSjxiaoxis";
	JSONObject sousuoSjxiaoxiresult = PostUtil.sendPost(sousuoSjxiaoxiurl, sousuoSjxiaoxiparam.toString());
	if(sousuoSjxiaoxiresult!=null){
		JSONArray sousuoSjxiaoxijsonArray = (JSONArray)sousuoSjxiaoxiresult.get("rows");
		//System.out.println(sousuoSjxiaoxijsonArray);
		sousuoSjxiaoxis = JSONArray.toList(sousuoSjxiaoxijsonArray, new Sjxiaoxi(), new JsonConfig());
		if(sousuoSjxiaoxis.size()>0){
			sousuoSjxiaoxi = sousuoSjxiaoxis.get(0);
		}
		//for(int i = 0;i < sjxiaoxis.size();i++){
		//	System.out.println(sjxiaoxis.get(i).getSjxiaoxiName());
		//}
		sousuoSjxiaoxisshuliang = (Integer)sousuoSjxiaoxiresult.get("total");
		//System.out.println(sjxiaoxisshuliang);
	}
	int sjxiaoxirowCount = 1;
	sjxiaoxirowCount = Integer.parseInt(sousuoSjxiaoxirows);
	int sjxiaoxicurrPage = 0;
	sjxiaoxicurrPage = Integer.parseInt(sousuoSjxiaoxipage);
	int sjxiaoxitotalPage = 0;
	sjxiaoxitotalPage = (sousuoSjxiaoxisshuliang + sjxiaoxirowCount - 1)/sjxiaoxirowCount;
%>