package com.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.model.*;
import com.service.*;
import com.util.*;

@Controller
public class ByumenAction {
	@Autowired
	private ByumenService byumenService;
	@Autowired
	private ByuyuanService byuyuanService;

	@RequestMapping("/addByumen")
	public void addByumen(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();
			
			String byumenName = (String) request.getParameter("byumenName");
			String byumenMark = (String) request.getParameter("byumenMark");
			String byumenMark1 = (String) request.getParameter("byumenMark1");
			String byumenMark2 = (String) request.getParameter("byumenMark2");
			String byumenType = (String) request.getParameter("byumenType");
			String byumenType1 = (String) request.getParameter("byumenType1");
			String byumenType2 = (String) request.getParameter("byumenType2");
			String byumenDouble = (String) request.getParameter("byumenDouble");
			String byumenDouble1 = (String) request.getParameter("byumenDouble1");
			String byumenDouble2 = (String) request.getParameter("byumenDouble2");
			String byuyuanId = (String) request.getParameter("byuyuanId");
			String byumenId = (String) request.getParameter("byumenId");
			Byumen byumen = new Byumen();
			
			if (StringUtil.isNotEmpty(byumenId)) {
				byumen = byumenService.getByumen(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byumenName)) {
				byumen.setByumenName(byumenName);
			}
			if (StringUtil.isNotEmpty(byumenMark)) {
				byumen.setByumenMark(byumenMark);
			}
			if (StringUtil.isNotEmpty(byumenMark1)) {
				byumen.setByumenMark1(byumenMark1);
			}
			if (StringUtil.isNotEmpty(byumenMark2)) {
				byumen.setByumenMark2(byumenMark2);
			}
			if (StringUtil.isNotEmpty(byumenType)) {
				byumen.setByumenType(Integer.parseInt(byumenType));
			}
			if (StringUtil.isNotEmpty(byumenType1)) {
				byumen.setByumenType1(Integer.parseInt(byumenType1));
			}
			if (StringUtil.isNotEmpty(byumenType2)) {
				byumen.setByumenType2(Integer.parseInt(byumenType2));
			}
			if (StringUtil.isNotEmpty(byumenDouble)) {
				byumen.setByumenDouble(Double.parseDouble(byumenDouble));
			}
			if (StringUtil.isNotEmpty(byumenDouble1)) {
				byumen.setByumenDouble1(Double.parseDouble(byumenDouble1));
			}
			if (StringUtil.isNotEmpty(byumenDouble2)) {
				byumen.setByumenDouble2(Double.parseDouble(byumenDouble2));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				byumen.setByuyuanId(Integer.parseInt(byuyuanId));
				Byuyuan byuyuan = new Byuyuan();
				byuyuan = byuyuanService.getByuyuan(Integer.parseInt(byuyuanId));
				byumen.setByuyuanName(byuyuan.getByuyuanName());
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				byumenService.modifyByumen(byumen);
			} else {
				byumenService.save(byumen);
			}
			result.put("success", "true");

			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getByumens")
	public void getByumens(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String byumenName = (String) request.getParameter("byumenName");
		String byumenType = (String) request.getParameter("byumenType");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		Byumen byumen = new Byumen();
		if (StringUtil.isNotEmpty(byumenName)) {
			byumen.setByumenName(byumenName);
		}
		if (StringUtil.isNotEmpty(byumenType)) {
			byumen.setByumenType(Integer.parseInt(byumenType));
		}
		if (StringUtil.isNotEmpty(byuyuanId)) {
			byumen.setByuyuanId(Integer.parseInt(byuyuanId));
		}
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			JSONArray jsonArray = JSONArray.fromObject(byumenService.queryByumens(byumen, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = byumenService.queryByumens(byumen, 0,0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteByumen")
	public void deleteByumen(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				byumenService.deleteByumen(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/byumenComboList")
	public void byumenComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String byumenType = (String) request.getParameter("byumenType");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		Byumen byumen = new Byumen();
		if (StringUtil.isNotEmpty(byumenType)) {
			byumen.setByumenType(Integer.parseInt(byumenType));
		}
		if (StringUtil.isNotEmpty(byuyuanId)) {
			byumen.setByuyuanId(Integer.parseInt(byuyuanId));
		}
		try {
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("byumenName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(byumenService.queryByumens(byumen,0,0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
