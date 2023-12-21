<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String shujupage = "1";
	String shujurows = "4";
	String shujuName = "";
	String shujuId = (String) request.getParameter("shujuId");
	
	StringBuffer shujuparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isNotEmpty(shujupage)) {
		shujuparam.append("&page=" + shujupage);
	}
	if (StringUtil.isNotEmpty(shujurows)) {
		shujuparam.append("&rows=" + shujurows);
	}
	if (StringUtil.isNotEmpty(shujuName)) {
		shujuparam.append("&shujuName=" + shujuName);
	}
	if (StringUtil.isNotEmpty(shujuId)) {
		shujuparam.append("&shujuId=" + shujuId);
	}

 %>
<%
	List<Shuju> shujus = new ArrayList<Shuju>();
	Shuju shuju = null;
	int shujusshuliang = 0;
	String shujupath = request.getContextPath();
	String shujubasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+shujupath+"/";
	
	String shujuurl = shujubasePath + "getShujus";
	JSONObject shujuresult = PostUtil.sendPost(shujuurl, shujuparam.toString());
	if(shujuresult!=null){
		JSONArray shujujsonArray = (JSONArray)shujuresult.get("rows");
		//System.out.println(shujujsonArray);
		shujus = JSONArray.toList(shujujsonArray, new Shuju(), new JsonConfig());
		if(shujus.size()>0){
			shuju = shujus.get(0);
		}
		//for(int i = 0;i < shujus.size();i++){
		//	System.out.println(shujus.get(i).getShujuName());
		//}
		shujusshuliang = (Integer)shujuresult.get("total");
		//System.out.println(shujusshuliang);
	}
%>