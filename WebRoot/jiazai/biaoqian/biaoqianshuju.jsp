<%@ page language="java" contentType="text/html; charset=utf-8"  
import="com.model.*,com.util.*,java.util.List,java.util.ArrayList,net.sf.json.JSONArray,net.sf.json.JSONObject,net.sf.json.JsonConfig"  
pageEncoding="utf-8"%>
<%
	String biaoqianShujupage = (String) request.getParameter("page");
	String biaoqianShujurows = (String) request.getParameter("rows");
	String biaoqianYonghuId = (String) request.getParameter("yonghuId");
	String biaoqianshujuType = "0";
	
	StringBuffer biaoqianShujuparam = new StringBuffer("yuliucanshu=1");
	if (StringUtil.isEmpty(biaoqianShujupage)) {
		biaoqianShujupage = "1";
	}
	biaoqianShujuparam.append("&page=" + biaoqianShujupage);
	if (StringUtil.isEmpty(biaoqianShujurows)) {
		biaoqianShujurows = "6";
	}
	biaoqianShujuparam.append("&rows=" + biaoqianShujurows);

	if (StringUtil.isNotEmpty(biaoqianYonghuId)) {
		biaoqianShujuparam.append("&yonghuId=" + biaoqianYonghuId);
	}

	List<Shuju> biaoqianShujus = new ArrayList<Shuju>();
	List<Sjpinglun> biaoqianSjpingluns = new ArrayList<Sjpinglun>();
	Shuju biaoqianShuju = null;
	int biaoqianShujusshuliang = 0;
	String biaoqianShujupath = request.getContextPath();
	String biaoqianShujubasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+biaoqianShujupath+"/";
	
	String biaoqianShujuurl = biaoqianShujubasePath + "biaoqianShujus";
	JSONObject biaoqianShujuresult = PostUtil.sendPost(biaoqianShujuurl, biaoqianShujuparam.toString());
	if(biaoqianShujuresult!=null){
		JSONArray biaoqianShujujsonArray = (JSONArray)biaoqianShujuresult.get("rows");
		//System.out.println(biaoqianShujujsonArray);
		biaoqianShujus = JSONArray.toList(biaoqianShujujsonArray, new Shuju(), new JsonConfig());
		if(biaoqianShujus.size()>0){
			biaoqianShuju = biaoqianShujus.get(0);
			JSONObject biaoqianSjpinglunresult = PostUtil.sendPost(biaoqianShujubasePath + "getSjpingluns", "shujuId=" + biaoqianShuju.getShujuId());
			if(!((biaoqianSjpinglunresult.get("total")).toString()).equals("0")){
				JSONArray biaoqianSjpinglunjsonArray = (JSONArray)biaoqianSjpinglunresult.get("rows");
				biaoqianSjpingluns = JSONArray.toList(biaoqianSjpinglunjsonArray, new Sjpinglun(), new JsonConfig());
			}
		}
		//for(int i = 0;i < shujus.size();i++){
		//	System.out.println(shujus.get(i).getShujuName());
		//}
		biaoqianShujusshuliang = (Integer)biaoqianShujuresult.get("total");
		//System.out.println(shujusshuliang);
	}
%>