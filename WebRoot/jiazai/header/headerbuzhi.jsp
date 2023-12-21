<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String buzhipage = "";
	String buzhirows = "";
	
	StringBuffer buzhiparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isNotEmpty(buzhipage)) {
		buzhiparam.append("&page=" + buzhipage);
	}
	if (StringUtil.isNotEmpty(buzhirows)) {
		buzhiparam.append("&rows=" + buzhirows);
	}

 %>
<%
	List<Buzhi> buzhis = new ArrayList<Buzhi>();
	Buzhi buzhi = null;
	int buzhisshuliang = 0;
	String buzhipath = request.getContextPath();
	String buzhibasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+buzhipath+"/";
	
	String buzhiurl = buzhibasePath + "getBuzhis";
	JSONObject buzhiresult = PostUtil.sendPost(buzhiurl, buzhiparam.toString());
	if(buzhiresult!=null){
		JSONArray buzhijsonArray = (JSONArray)buzhiresult.get("rows");
		//System.out.println(buzhijsonArray);
		buzhis = JSONArray.toList(buzhijsonArray, new Buzhi(), new JsonConfig());
		if(buzhis.size()>0){
			buzhi = buzhis.get(0);
		}
		for(int i = 0;i < buzhis.size();i++){
			System.out.println(buzhis.get(i).getBuzhiName());
		}
		buzhisshuliang = (Integer)buzhiresult.get("total");
		System.out.println(buzhisshuliang);
	}
%>