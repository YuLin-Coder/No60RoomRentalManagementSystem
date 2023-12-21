<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String sjduochupage = "1";
	String sjduochurows = "18";
	String sjduochuName = "";
	String sjduochuId = (String) request.getParameter("sjduochuId");
	String sjxingtaiId = "";
	String sjduochuType = "0";
	
	StringBuffer sjduochuparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isNotEmpty(sjduochupage)) {
		sjduochuparam.append("&page=" + sjduochupage);
	}
	if (StringUtil.isNotEmpty(sjduochurows)) {
		sjduochuparam.append("&rows=" + sjduochurows);
	}
	if (StringUtil.isNotEmpty(sjduochuName)) {
		sjduochuparam.append("&sjduochuName=" + sjduochuName);
	}
	if (StringUtil.isNotEmpty(sjduochuId)) {
		sjduochuparam.append("&sjduochuId=" + sjduochuId);
	}
	if (StringUtil.isNotEmpty(sjxingtaiId)) {
		sjduochuparam.append("&sjxingtaiId=" + sjxingtaiId);
	}
	if (StringUtil.isNotEmpty(sjduochuType)) {
		sjduochuparam.append("&sjduochuType=" + sjduochuType);
	}

 %>
<%
	List<Sjduochu> sjduochus = new ArrayList<Sjduochu>();
	Sjduochu sjduochu = null;
	int sjduochusshuliang = 0;
	String sjduochupath = request.getContextPath();
	String sjduochubasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+sjduochupath+"/";
	
	String sjduochuurl = sjduochubasePath + "getSjduochus";
	JSONObject sjduochuresult = PostUtil.sendPost(sjduochuurl, sjduochuparam.toString());
	if(sjduochuresult!=null){
		JSONArray sjduochujsonArray = (JSONArray)sjduochuresult.get("rows");
		//System.out.println(sjduochujsonArray);
		sjduochus = JSONArray.toList(sjduochujsonArray, new Sjduochu(), new JsonConfig());
		if(sjduochus.size()>0){
			sjduochu = sjduochus.get(0);
		}
		//for(int i = 0;i < sjduochus.size();i++){
		//	System.out.println(sjduochus.get(i).getSjduochuName());
		//}
		sjduochusshuliang = (Integer)sjduochuresult.get("total");
		//System.out.println(sjduochusshuliang);
	}
%>