<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String sjxingtaipage = "";
	String sjxingtairows = "";
	
	StringBuffer sjxingtaiparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isNotEmpty(sjxingtaipage)) {
		sjxingtaiparam.append("&page=" + sjxingtaipage);
	}
	if (StringUtil.isNotEmpty(sjxingtairows)) {
		sjxingtaiparam.append("&rows=" + sjxingtairows);
	}

 %>
<%
	List<Sjxingtai> sjxingtais = new ArrayList<Sjxingtai>();
	Sjxingtai sjxingtai = null;
	int sjxingtaisshuliang = 0;
	String sjxingtaipath = request.getContextPath();
	String sjxingtaibasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+sjxingtaipath+"/";
	
	String sjxingtaiurl = sjxingtaibasePath + "getSjxingtais";
	JSONObject sjxingtairesult = PostUtil.sendPost(sjxingtaiurl, sjxingtaiparam.toString());
	if(sjxingtairesult!=null){
		JSONArray sjxingtaijsonArray = (JSONArray)sjxingtairesult.get("rows");
		//System.out.println(sjxingtaijsonArray);
		sjxingtais = JSONArray.toList(sjxingtaijsonArray, new Sjxingtai(), new JsonConfig());
		if(sjxingtais.size()>0){
			sjxingtai = sjxingtais.get(0);
		}
		for(int i = 0;i < sjxingtais.size();i++){
			System.out.println(sjxingtais.get(i).getSjxingtaiName());
		}
		sjxingtaisshuliang = (Integer)sjxingtairesult.get("total");
		System.out.println(sjxingtaisshuliang);
	}
%>