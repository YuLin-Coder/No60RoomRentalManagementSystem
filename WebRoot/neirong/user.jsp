<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String userpage = "1";
	String userrows = "4";
	String userName = "";
	String userId = (String) request.getParameter("userId");
	
	StringBuffer userparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isNotEmpty(userpage)) {
		userparam.append("&page=" + userpage);
	}
	if (StringUtil.isNotEmpty(userrows)) {
		userparam.append("&rows=" + userrows);
	}
	if (StringUtil.isNotEmpty(userName)) {
		userparam.append("&userName=" + userName);
	}
	if (StringUtil.isNotEmpty(userId)) {
		userparam.append("&userId=" + userId);
	}

 %>
<%
	List<User> users = new ArrayList<User>();
	User user = null;
	int usersshuliang = 0;
	String userpath = request.getContextPath();
	String userbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+userpath+"/";
	
	String userurl = userbasePath + "getUsers";
	JSONObject userresult = PostUtil.sendPost(userurl, userparam.toString());
	if(userresult!=null){
		JSONArray userjsonArray = (JSONArray)userresult.get("rows");
		//System.out.println(userjsonArray);
		users = JSONArray.toList(userjsonArray, new User(), new JsonConfig());
		if(users.size()>0){
			user = users.get(0);
		}
		//for(int i = 0;i < users.size();i++){
		//	System.out.println(users.get(i).getUserName());
		//}
		usersshuliang = (Integer)userresult.get("total");
		//System.out.println(usersshuliang);
	}
%>