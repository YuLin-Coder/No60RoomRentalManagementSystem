<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String sjlianjiepage = "";
	String sjlianjierows = "";
	
	StringBuffer sjlianjieparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isNotEmpty(sjlianjiepage)) {
		sjlianjieparam.append("&page=" + sjlianjiepage);
	}
	if (StringUtil.isNotEmpty(sjlianjierows)) {
		sjlianjieparam.append("&rows=" + sjlianjierows);
	}

 %>
<%
	List<Sjlianjie> sjlianjies = new ArrayList<Sjlianjie>();
	Sjlianjie sjlianjie = null;
	int sjlianjiesshuliang = 0;
	String sjlianjiepath = request.getContextPath();
	String sjlianjiebasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+sjlianjiepath+"/";
	
	String sjlianjieurl = sjlianjiebasePath + "getSjlianjies";
	JSONObject sjlianjieresult = PostUtil.sendPost(sjlianjieurl, sjlianjieparam.toString());
	if(sjlianjieresult!=null){
		JSONArray sjlianjiejsonArray = (JSONArray)sjlianjieresult.get("rows");
		//System.out.println(sjlianjiejsonArray);
		sjlianjies = JSONArray.toList(sjlianjiejsonArray, new Sjlianjie(), new JsonConfig());
		if(sjlianjies.size()>0){
			sjlianjie = sjlianjies.get(0);
		}
		for(int i = 0;i < sjlianjies.size();i++){
			System.out.println(sjlianjies.get(i).getSjlianjieName());
		}
		sjlianjiesshuliang = (Integer)sjlianjieresult.get("total");
		System.out.println(sjlianjiesshuliang);
	}
%>