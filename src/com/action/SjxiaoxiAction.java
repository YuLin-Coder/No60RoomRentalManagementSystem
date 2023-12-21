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
public class SjxiaoxiAction {
	@Autowired
	private SjxiaoxiService sjxiaoxiService;

	@RequestMapping("/getSjxiaoxis")
	public void getSjxiaoxis(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String sjxiaoxiName = (String) request.getParameter("sjxiaoxiName");
		String sjxiaoxiPhone = (String) request.getParameter("sjxiaoxiPhone");
		String sjxiaoxiId = (String) request.getParameter("sjxiaoxiId");
		String sjxiaoxiType1 = (String) request.getParameter("sjxiaoxiType1");
		String sjxiaoxiType = (String) request.getParameter("sjxiaoxiType");
		Sjxiaoxi sjxiaoxi = new Sjxiaoxi();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(sjxiaoxiName)) {
				sjxiaoxi.setSjxiaoxiName(sjxiaoxiName);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiPhone)) {
				sjxiaoxi.setSjxiaoxiPhone(sjxiaoxiPhone);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiId)) {
				sjxiaoxi.setSjxiaoxiId(Integer.parseInt(sjxiaoxiId));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiType)) {
				sjxiaoxi.setSjxiaoxiType(Integer.parseInt(sjxiaoxiType));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiType1)) {
				sjxiaoxi.setSjxiaoxiType1(Integer.parseInt(sjxiaoxiType1));
			}
			JSONArray jsonArray = JSONArray.fromObject(sjxiaoxiService.querySjxiaoxis(
					sjxiaoxi, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = sjxiaoxiService.querySjxiaoxis(sjxiaoxi, 0,0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addSjxiaoxi")
	public void addSjxiaoxi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String sjxiaoxiName = (String) request.getParameter("sjxiaoxiName");
			String sjxiaoxiPhone = (String) request.getParameter("sjxiaoxiPhone");
			String sjxiaoxiMark = (String) request.getParameter("sjxiaoxiMark");
			String sjxiaoxiMark1 = (String) request.getParameter("sjxiaoxiMark1");
			String sjxiaoxiMark2 = (String) request.getParameter("sjxiaoxiMark2");
			String sjxiaoxiDizhi = (String) request.getParameter("sjxiaoxiDizhi");
			String sjxiaoxiDate = (String) request.getParameter("sjxiaoxiDate");
			String sjxiaoxiDate1 = (String) request.getParameter("sjxiaoxiDate1");
			String sjxiaoxiType = (String) request.getParameter("sjxiaoxiType");
			String sjxiaoxiType1 = (String) request.getParameter("sjxiaoxiType1");
			String sjxiaoxiDouble = (String) request.getParameter("sjxiaoxiDouble");
			String sjxiaoxiDouble1 = (String) request.getParameter("sjxiaoxiDouble1");
			String sjxiaoxiId = (String) request.getParameter("sjxiaoxiId");
			Sjxiaoxi sjxiaoxi = new Sjxiaoxi();

			if (StringUtil.isNotEmpty(sjxiaoxiId)) {
				sjxiaoxi = sjxiaoxiService.getSjxiaoxi(Integer.parseInt(sjxiaoxiId));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiName)) {
				sjxiaoxi.setSjxiaoxiName(sjxiaoxiName);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiPhone)) {
				sjxiaoxi.setSjxiaoxiPhone(sjxiaoxiPhone);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiMark)) {
				sjxiaoxi.setSjxiaoxiMark(sjxiaoxiMark);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiMark1)) {
				sjxiaoxi.setSjxiaoxiMark1(sjxiaoxiMark1);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiMark2)) {
				sjxiaoxi.setSjxiaoxiMark2(sjxiaoxiMark2);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiDizhi)) {
				sjxiaoxi.setSjxiaoxiDizhi(sjxiaoxiDizhi);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiDate)) {
				sjxiaoxi.setSjxiaoxiDate(DateUtil.formatString(sjxiaoxiDate,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiDate1)) {
				sjxiaoxi.setSjxiaoxiDate1(DateUtil.formatString(sjxiaoxiDate1,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiType)) {
				sjxiaoxi.setSjxiaoxiType(Integer.parseInt(sjxiaoxiType));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiType1)) {
				sjxiaoxi.setSjxiaoxiType1(Integer.parseInt(sjxiaoxiType1));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiDouble)) {
				sjxiaoxi.setSjxiaoxiDouble(Double.parseDouble(sjxiaoxiDouble));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiDouble1)) {
				sjxiaoxi.setSjxiaoxiDouble1(Double.parseDouble(sjxiaoxiDouble1));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiId)) {
				Date date = new Date();
				sjxiaoxi.setSjxiaoxiDate(date);
				sjxiaoxi.setSjxiaoxiMark1(StringUtil.jiequString(sjxiaoxi.getSjxiaoxiMark(), 120));
				sjxiaoxiService.modifySjxiaoxi(sjxiaoxi);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				Date date = new Date();
				sjxiaoxi.setSjxiaoxiDate(date);
				sjxiaoxi.setSjxiaoxiMark1(StringUtil.jiequString(sjxiaoxi.getSjxiaoxiMark(), 120));
				sjxiaoxiService.save(sjxiaoxi);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteSjxiaoxi")
	public void deleteSjxiaoxi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				sjxiaoxiService.deleteSjxiaoxi(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjxiaoxiComboList")
	public void sjxiaoxiComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sjxiaoxiType1 = (String) request.getParameter("sjxiaoxiType1");
		String sjxiaoxiType = (String) request.getParameter("sjxiaoxiType");
		Sjxiaoxi sjxiaoxi = new Sjxiaoxi();
		try {
			if (StringUtil.isNotEmpty(sjxiaoxiType)) {
				sjxiaoxi.setSjxiaoxiType(Integer.parseInt(sjxiaoxiType));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiType1)) {
				sjxiaoxi.setSjxiaoxiType1(Integer.parseInt(sjxiaoxiType1));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("sjxiaoxiName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(sjxiaoxiService.querySjxiaoxis(sjxiaoxi,
					0,0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
