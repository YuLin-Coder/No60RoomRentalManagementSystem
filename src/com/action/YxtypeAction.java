package com.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.*;
import com.service.*;
import com.util.*;

@Controller
public class YxtypeAction {
	@Autowired
	private YxtypeService yxtypeService;

	@RequestMapping("/getYxtypes")
	public void getYxtypes(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String yxtypeName = (String) request.getParameter("yxtypeName");
		String yxtypeId = (String) request.getParameter("yxtypeId");
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		Yxtype yxtype = new Yxtype();
		try {
			if (StringUtil.isNotEmpty(yxtypeName)) {
				yxtype.setYxtypeName(yxtypeName);
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yxtype.setYxtypeId(Integer.parseInt(yxtypeId));
			}
			JSONArray jsonArray = JSONArray.fromObject(yxtypeService.queryYxtypes(yxtype, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = yxtypeService.queryYxtypes(yxtype, 0, 0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addYxtype")
	public void addYxtype(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();
			
			String yxtypeName = (String) request.getParameter("yxtypeName");
			String yxtypeMark = (String) request.getParameter("yxtypeMark");
			String yxtypeId = (String) request.getParameter("yxtypeId");
			Yxtype yxtype = new Yxtype();
			
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yxtype = yxtypeService.getYxtype(Integer.parseInt(yxtypeId));
			}
			if (StringUtil.isNotEmpty(yxtypeName)) {
				yxtype.setYxtypeName(yxtypeName);
			}
			if (StringUtil.isNotEmpty(yxtypeMark)) {
				yxtype.setYxtypeMark(yxtypeMark);
			}

			if (StringUtil.isNotEmpty(yxtypeId)) {
				yxtypeService.modifyYxtype(yxtype);
			} else {
				yxtypeService.save(yxtype);
			}
			result.put("success", "true");

			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteYxtype")
	public void deleteYxtype(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				yxtypeService.deleteYxtype(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/yxtypeComboList")
	public void yxtypeComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("yxtypeName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(yxtypeService.queryYxtypes(null, 0, 0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
