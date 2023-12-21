<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String sousuoUserpage = (String) request.getParameter("page");
	String sousuoUserrows = (String) request.getParameter("rows");
	String sousuoUserXingming = (String) request.getParameter("userXingming");
	String sousuoUserId = (String) request.getParameter("userId");
	String sousuoBuzhiId = (String) request.getParameter("buzhiId");
	String sousuouserType = "1";
	
	StringBuffer sousuoUserparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isEmpty(sousuoUserpage)) {
		sousuoUserpage = "1";
	}
	sousuoUserparam.append("&page=" + sousuoUserpage);
	if (StringUtil.isEmpty(sousuoUserrows)) {
		sousuoUserrows = "4";
	}
	sousuoUserparam.append("&rows=" + sousuoUserrows);
	if (StringUtil.isNotEmpty(sousuoUserXingming)) {
		sousuoUserparam.append("&userXingming=" + sousuoUserXingming);
	}
	if (StringUtil.isNotEmpty(sousuoUserId)) {
		sousuoUserparam.append("&userId=" + sousuoUserId);
	}
	if (StringUtil.isNotEmpty(sousuoBuzhiId)) {
		sousuoUserparam.append("&buzhiId=" + sousuoBuzhiId);
	}
	if (StringUtil.isNotEmpty(sousuouserType)) {
		sousuoUserparam.append("&userType=" + sousuouserType);
	}

	List<User> sousuoUsers = new ArrayList<User>();
	User sousuoUser = null;
	int sousuoUsersshuliang = 0;
	String sousuoUserpath = request.getContextPath();
	String sousuoUserbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+sousuoUserpath+"/";
	
	String sousuoUserurl = sousuoUserbasePath + "getUsers";
	JSONObject sousuoUserresult = PostUtil.sendPost(sousuoUserurl, sousuoUserparam.toString());
	if(sousuoUserresult!=null){
		JSONArray sousuoUserjsonArray = (JSONArray)sousuoUserresult.get("rows");
		//System.out.println(sousuoUserjsonArray);
		sousuoUsers = JSONArray.toList(sousuoUserjsonArray, new User(), new JsonConfig());
		if(sousuoUsers.size()>0){
			sousuoUser = sousuoUsers.get(0);
		}
		//for(int i = 0;i < users.size();i++){
		//	System.out.println(users.get(i).getUserXingming());
		//}
		sousuoUsersshuliang = (Integer)sousuoUserresult.get("total");
		//System.out.println(usersshuliang);
	}
	int userrowCount = 1;
	userrowCount = Integer.parseInt(sousuoUserrows);
	int usercurrPage = 0;
	usercurrPage = Integer.parseInt(sousuoUserpage);
	int usertotalPage = 0;
	usertotalPage = (sousuoUsersshuliang + userrowCount - 1)/userrowCount;
%>