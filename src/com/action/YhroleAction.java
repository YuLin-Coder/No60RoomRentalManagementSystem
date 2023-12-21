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
public class YhroleAction {
	@Autowired
	private YhroleService yhroleService;

	@RequestMapping("/getYhroles")
	public void getYhroles(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String yhroleName = (String) request.getParameter("yhroleName");
		String yhroleId = (String) request.getParameter("yhroleId");
		String yhroleMark2 = (String) request.getParameter("yhroleMark2");
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		Yhrole yhrole = new Yhrole();
		try {
			if (StringUtil.isNotEmpty(yhroleName)) {
				yhrole.setYhroleName(yhroleName);
			}
			if (StringUtil.isNotEmpty(yhroleId)) {
				yhrole.setYhroleId(Integer.parseInt(yhroleId));
			}
			if (StringUtil.isNotEmpty(yhroleMark2)) {
				yhrole.setYhroleMark2(Integer.parseInt(yhroleMark2));
			}
			JSONArray jsonArray = JSONArray.fromObject(yhroleService.queryYhroles(yhrole, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = yhroleService.queryYhroles(yhrole, 0, 0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addYhrole")
	public void addYhrole(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();
			
			String yhroleName = (String) request.getParameter("yhroleName");
			String yhroleMark = (String) request.getParameter("yhroleMark");
			String yhroleMark1 = (String) request.getParameter("yhroleMark1");
			String yhroleMark2 = (String) request.getParameter("yhroleMark2");
			String yhroleId = (String) request.getParameter("yhroleId");
			Yhrole yhrole = new Yhrole();
			
			if (StringUtil.isNotEmpty(yhroleId)) {
				yhrole = yhroleService.getYhrole(Integer.parseInt(yhroleId));
			}
			if (StringUtil.isNotEmpty(yhroleName)) {
				yhrole.setYhroleName(yhroleName);
			}
			if (StringUtil.isNotEmpty(yhroleMark)) {
				yhrole.setYhroleMark(yhroleMark);
			}
			if (StringUtil.isNotEmpty(yhroleMark1)) {
				yhrole.setYhroleMark1(yhroleMark1);
			}
			if (StringUtil.isNotEmpty(yhroleMark2)) {
				yhrole.setYhroleMark2(Integer.parseInt(yhroleMark2));
			}

			if (StringUtil.isNotEmpty(yhroleId)) {
				yhroleService.modifyYhrole(yhrole);
			} else {
				yhroleService.save(yhrole);
			}
			result.put("success", "true");

			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteYhrole")
	public void deleteYhrole(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				yhroleService.deleteYhrole(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/yhroleComboList")
	public void yhroleComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String yhroleMark2 = (String) request.getParameter("yhroleMark2");
		Yhrole yhrole = new Yhrole();

		if (StringUtil.isNotEmpty(yhroleMark2)) {
			yhrole.setYhroleMark2(Integer.parseInt(yhroleMark2));
		}
		try {
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("yhroleName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(yhroleService.queryYhroles(yhrole, 0, 0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
