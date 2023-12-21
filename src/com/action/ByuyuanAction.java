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
public class ByuyuanAction {
	@Autowired
	private ByuyuanService byuyuanService;

	@RequestMapping("/addByuyuan")
	public void addByuyuan(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();
			
			String byuyuanName = (String) request.getParameter("byuyuanName");
			String byuyuanMark = (String) request.getParameter("byuyuanMark");
			String byuyuanMark1 = (String) request.getParameter("byuyuanMark1");
			String byuyuanMark2 = (String) request.getParameter("byuyuanMark2");
			String byuyuanType = (String) request.getParameter("byuyuanType");
			String byuyuanType1 = (String) request.getParameter("byuyuanType1");
			String byuyuanType2 = (String) request.getParameter("byuyuanType2");
			String byuyuanDouble = (String) request.getParameter("byuyuanDouble");
			String byuyuanDouble1 = (String) request.getParameter("byuyuanDouble1");
			String byuyuanDouble2 = (String) request.getParameter("byuyuanDouble2");
			String byuyuanId = (String) request.getParameter("byuyuanId");
			Byuyuan byuyuan = new Byuyuan();
			
			if (StringUtil.isNotEmpty(byuyuanId)) {
				byuyuan = byuyuanService.getByuyuan(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byuyuanName)) {
				byuyuan.setByuyuanName(byuyuanName);
			}
			if (StringUtil.isNotEmpty(byuyuanMark)) {
				byuyuan.setByuyuanMark(byuyuanMark);
			}
			if (StringUtil.isNotEmpty(byuyuanMark1)) {
				byuyuan.setByuyuanMark1(byuyuanMark1);
			}
			if (StringUtil.isNotEmpty(byuyuanMark2)) {
				byuyuan.setByuyuanMark2(byuyuanMark2);
			}
			if (StringUtil.isNotEmpty(byuyuanType)) {
				byuyuan.setByuyuanType(Integer.parseInt(byuyuanType));
			}
			if (StringUtil.isNotEmpty(byuyuanType1)) {
				byuyuan.setByuyuanType1(Integer.parseInt(byuyuanType1));
			}
			if (StringUtil.isNotEmpty(byuyuanType2)) {
				byuyuan.setByuyuanType2(Integer.parseInt(byuyuanType2));
			}
			if (StringUtil.isNotEmpty(byuyuanDouble)) {
				byuyuan.setByuyuanDouble(Double.parseDouble(byuyuanDouble));
			}
			if (StringUtil.isNotEmpty(byuyuanDouble1)) {
				byuyuan.setByuyuanDouble1(Double.parseDouble(byuyuanDouble1));
			}
			if (StringUtil.isNotEmpty(byuyuanDouble2)) {
				byuyuan.setByuyuanDouble2(Double.parseDouble(byuyuanDouble2));
			}

			if (StringUtil.isNotEmpty(byuyuanId)) {
				byuyuanService.modifyByuyuan(byuyuan);
			} else {
				byuyuanService.save(byuyuan);
			}
			result.put("success", "true");

			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getByuyuans")
	public void getByuyuans(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String byuyuanName = (String) request.getParameter("byuyuanName");
		String byuyuanType = (String) request.getParameter("byuyuanType");
		Byuyuan byuyuan = new Byuyuan();
		if (StringUtil.isNotEmpty(byuyuanName)) {
			byuyuan.setByuyuanName(byuyuanName);
		}
		if (StringUtil.isNotEmpty(byuyuanType)) {
			byuyuan.setByuyuanType(Integer.parseInt(byuyuanType));
		}
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			JSONArray jsonArray = JSONArray.fromObject(byuyuanService.queryByuyuans(byuyuan, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = byuyuanService.queryByuyuans(byuyuan, 0,0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteByuyuan")
	public void deleteByuyuan(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				byuyuanService.deleteByuyuan(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/byuyuanComboList")
	public void byuyuanComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String byuyuanType = (String) request.getParameter("byuyuanType");
		Byuyuan byuyuan = new Byuyuan();
		if (StringUtil.isNotEmpty(byuyuanType)) {
			byuyuan.setByuyuanType(Integer.parseInt(byuyuanType));
		}
		try {
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("byuyuanName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(byuyuanService.queryByuyuans(byuyuan,0,0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
