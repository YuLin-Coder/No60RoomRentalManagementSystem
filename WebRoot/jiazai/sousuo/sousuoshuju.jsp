<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String sousuoShujupage = (String) request.getParameter("page");
	String sousuoShujurows = (String) request.getParameter("rows");
	String sousuoShujuName = (String) request.getParameter("shujuName");
	String sousuoShujuId = (String) request.getParameter("shujuId");
	String sousuoSjleixingId = (String) request.getParameter("sjleixingId");
	String sousuoShujuUserId = (String) request.getParameter("userId");
	String sousuoshujuType = "0";
	String sousuoSjleixingName = (String) request.getParameter("sjleixingName");
	String sousuoShujuMark1 = (String) request.getParameter("shujuMark1");
	String sousuoShujuMark2 = (String) request.getParameter("shujuMark2");
	String sousuoShujuDouble3 = (String) request.getParameter("shujuDouble3");
	String sousuoShujuDouble4 = (String) request.getParameter("shujuDouble4");
	
	StringBuffer sousuoShujuparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isEmpty(sousuoShujupage)) {
		sousuoShujupage = "1";
	}
	sousuoShujuparam.append("&page=" + sousuoShujupage);
	if (StringUtil.isEmpty(sousuoShujurows)) {
		sousuoShujurows = "16";
	}
	sousuoShujuparam.append("&rows=" + sousuoShujurows);
	if (StringUtil.isNotEmpty(sousuoShujuName)) {
		sousuoShujuparam.append("&shujuName=" + sousuoShujuName);
	}
	if (StringUtil.isNotEmpty(sousuoShujuId)) {
		sousuoShujuparam.append("&shujuId=" + sousuoShujuId);
	}
	if (StringUtil.isNotEmpty(sousuoSjleixingId)) {
		sousuoShujuparam.append("&sjleixingId=" + sousuoSjleixingId);
	}
	if (StringUtil.isNotEmpty(sousuoShujuUserId)) {
		sousuoShujuparam.append("&userId=" + sousuoShujuUserId);
	}
	if (StringUtil.isNotEmpty(sousuoshujuType)) {
		sousuoShujuparam.append("&shujuType=" + sousuoshujuType);
	}
	if (StringUtil.isNotEmpty(sousuoSjleixingName)) {
		sousuoShujuparam.append("&sjleixingName=" + sousuoSjleixingName);
	}
	if (StringUtil.isNotEmpty(sousuoShujuMark1)) {
		sousuoShujuparam.append("&shujuMark1=" + sousuoShujuMark1);
	}
	if (StringUtil.isNotEmpty(sousuoShujuMark2)) {
		sousuoShujuparam.append("&shujuMark2=" + sousuoShujuMark2);
	}
	if (StringUtil.isNotEmpty(sousuoShujuDouble3)) {
		sousuoShujuparam.append("&shujuDouble3=" + sousuoShujuDouble3);
	}
	if (StringUtil.isNotEmpty(sousuoShujuDouble4)) {
		sousuoShujuparam.append("&shujuDouble4=" + sousuoShujuDouble4);
	}

	List<Shuju> sousuoShujus = new ArrayList<Shuju>();
	List<Sjpinglun> sousuoSjpingluns = new ArrayList<Sjpinglun>();
	Shuju sousuoShuju = null;
	int sousuoShujusshuliang = 0;
	String sousuoShujupath = request.getContextPath();
	String sousuoShujubasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+sousuoShujupath+"/";
	
	String sousuoShujuurl = sousuoShujubasePath + "getShujus";
	JSONObject sousuoShujuresult = PostUtil.sendPost(sousuoShujuurl, sousuoShujuparam.toString());
	if(sousuoShujuresult!=null){
		JSONArray sousuoShujujsonArray = (JSONArray)sousuoShujuresult.get("rows");
		//System.out.println(sousuoShujujsonArray);
		sousuoShujus = JSONArray.toList(sousuoShujujsonArray, new Shuju(), new JsonConfig());
		if(sousuoShujus.size()>0){
			sousuoShuju = sousuoShujus.get(0);
			JSONObject sousuoSjpinglunresult = PostUtil.sendPost(sousuoShujubasePath + "getSjpingluns", "shujuId=" + sousuoShuju.getShujuId());
			if(!((sousuoSjpinglunresult.get("total")).toString()).equals("0")){
				JSONArray sousuoSjpinglunjsonArray = (JSONArray)sousuoSjpinglunresult.get("rows");
				sousuoSjpingluns = JSONArray.toList(sousuoSjpinglunjsonArray, new Sjpinglun(), new JsonConfig());
			}
		}
		//for(int i = 0;i < shujus.size();i++){
		//	System.out.println(shujus.get(i).getShujuName());
		//}
		sousuoShujusshuliang = (Integer)sousuoShujuresult.get("total");
		//System.out.println(shujusshuliang);
	}
	int shujurowCount = 1;
	shujurowCount = Integer.parseInt(sousuoShujurows);
	int shujucurrPage = 0;
	shujucurrPage = Integer.parseInt(sousuoShujupage);
	int shujutotalPage = 0;
	shujutotalPage = (sousuoShujusshuliang + shujurowCount - 1)/shujurowCount;
%>