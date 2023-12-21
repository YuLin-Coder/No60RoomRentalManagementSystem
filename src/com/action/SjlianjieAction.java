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
public class SjlianjieAction {
	@Autowired
	private SjlianjieService sjlianjieService;

	@RequestMapping("/getSjlianjies")
	public void getSjlianjies(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String sjlianjieName = (String) request.getParameter("sjlianjieName");
		String sjlianjiePhone = (String) request.getParameter("sjlianjiePhone");
		String sjlianjieId = (String) request.getParameter("sjlianjieId");
		String sjlianjieType1 = (String) request.getParameter("sjlianjieType1");
		String sjlianjieType = (String) request.getParameter("sjlianjieType");
		Sjlianjie sjlianjie = new Sjlianjie();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(sjlianjieName)) {
				sjlianjie.setSjlianjieName(sjlianjieName);
			}
			if (StringUtil.isNotEmpty(sjlianjiePhone)) {
				sjlianjie.setSjlianjiePhone(sjlianjiePhone);
			}
			if (StringUtil.isNotEmpty(sjlianjieId)) {
				sjlianjie.setSjlianjieId(Integer.parseInt(sjlianjieId));
			}
			if (StringUtil.isNotEmpty(sjlianjieType)) {
				sjlianjie.setSjlianjieType(Integer.parseInt(sjlianjieType));
			}
			if (StringUtil.isNotEmpty(sjlianjieType1)) {
				sjlianjie.setSjlianjieType1(Integer.parseInt(sjlianjieType1));
			}
			JSONArray jsonArray = JSONArray.fromObject(sjlianjieService.querySjlianjies(
					sjlianjie, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = sjlianjieService.querySjlianjies(sjlianjie, 0,0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addSjlianjie")
	public void addSjlianjie(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String sjlianjieName = (String) request.getParameter("sjlianjieName");
			String sjlianjiePhone = (String) request.getParameter("sjlianjiePhone");
			String sjlianjieMark = (String) request.getParameter("sjlianjieMark");
			String sjlianjieMark1 = (String) request.getParameter("sjlianjieMark1");
			String sjlianjieMark2 = (String) request.getParameter("sjlianjieMark2");
			String sjlianjieDizhi = (String) request.getParameter("sjlianjieDizhi");
			String sjlianjieDate = (String) request.getParameter("sjlianjieDate");
			String sjlianjieDate1 = (String) request.getParameter("sjlianjieDate1");
			String sjlianjieType = (String) request.getParameter("sjlianjieType");
			String sjlianjieType1 = (String) request.getParameter("sjlianjieType1");
			String sjlianjieDouble = (String) request.getParameter("sjlianjieDouble");
			String sjlianjieDouble1 = (String) request.getParameter("sjlianjieDouble1");
			String sjlianjieId = (String) request.getParameter("sjlianjieId");
			Sjlianjie sjlianjie = new Sjlianjie();

			if (StringUtil.isNotEmpty(sjlianjieId)) {
				sjlianjie = sjlianjieService.getSjlianjie(Integer.parseInt(sjlianjieId));
			}
			if (StringUtil.isNotEmpty(sjlianjieName)) {
				sjlianjie.setSjlianjieName(sjlianjieName);
			}
			if (StringUtil.isNotEmpty(sjlianjiePhone)) {
				sjlianjie.setSjlianjiePhone(sjlianjiePhone);
			}
			if (StringUtil.isNotEmpty(sjlianjieMark)) {
				sjlianjie.setSjlianjieMark(sjlianjieMark);
			}
			if (StringUtil.isNotEmpty(sjlianjieMark1)) {
				sjlianjie.setSjlianjieMark1(sjlianjieMark1);
			}
			if (StringUtil.isNotEmpty(sjlianjieMark2)) {
				sjlianjie.setSjlianjieMark2(sjlianjieMark2);
			}
			if (StringUtil.isNotEmpty(sjlianjieDizhi)) {
				sjlianjie.setSjlianjieDizhi(sjlianjieDizhi);
			}
			if (StringUtil.isNotEmpty(sjlianjieDate)) {
				sjlianjie.setSjlianjieDate(DateUtil.formatString(sjlianjieDate,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjlianjieDate1)) {
				sjlianjie.setSjlianjieDate1(DateUtil.formatString(sjlianjieDate1,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjlianjieType)) {
				sjlianjie.setSjlianjieType(Integer.parseInt(sjlianjieType));
			}
			if (StringUtil.isNotEmpty(sjlianjieType1)) {
				sjlianjie.setSjlianjieType1(Integer.parseInt(sjlianjieType1));
			}
			if (StringUtil.isNotEmpty(sjlianjieDouble)) {
				sjlianjie.setSjlianjieDouble(Double.parseDouble(sjlianjieDouble));
			}
			if (StringUtil.isNotEmpty(sjlianjieDouble1)) {
				sjlianjie.setSjlianjieDouble1(Double.parseDouble(sjlianjieDouble1));
			}
			if (StringUtil.isNotEmpty(sjlianjieId)) {
				Date date = new Date();
				sjlianjie.setSjlianjieDate(date);
				sjlianjieService.modifySjlianjie(sjlianjie);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				Date date = new Date();
				sjlianjie.setSjlianjieDate(date);
				sjlianjieService.save(sjlianjie);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteSjlianjie")
	public void deleteSjlianjie(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				sjlianjieService.deleteSjlianjie(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjlianjieComboList")
	public void sjlianjieComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sjlianjieType1 = (String) request.getParameter("sjlianjieType1");
		String sjlianjieType = (String) request.getParameter("sjlianjieType");
		Sjlianjie sjlianjie = new Sjlianjie();
		try {
			if (StringUtil.isNotEmpty(sjlianjieType)) {
				sjlianjie.setSjlianjieType(Integer.parseInt(sjlianjieType));
			}
			if (StringUtil.isNotEmpty(sjlianjieType1)) {
				sjlianjie.setSjlianjieType1(Integer.parseInt(sjlianjieType1));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("sjlianjieName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(sjlianjieService.querySjlianjies(sjlianjie,
					0,0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
