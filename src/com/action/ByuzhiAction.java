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
public class ByuzhiAction {
	@Autowired
	private ByuzhiService byuzhiService;
	@Autowired
	private ByuyuanService byuyuanService;
	@Autowired
	private ByumenService byumenService;

	@RequestMapping("/addByuzhi")
	public void addByuzhi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();
			
			String byuzhiName = (String) request.getParameter("byuzhiName");
			String byuzhiMark = (String) request.getParameter("byuzhiMark");
			String byuzhiMark1 = (String) request.getParameter("byuzhiMark1");
			String byuzhiMark2 = (String) request.getParameter("byuzhiMark2");
			String byuzhiType = (String) request.getParameter("byuzhiType");
			String byuzhiType1 = (String) request.getParameter("byuzhiType1");
			String byuzhiType2 = (String) request.getParameter("byuzhiType2");
			String byuzhiDouble = (String) request.getParameter("byuzhiDouble");
			String byuzhiDouble1 = (String) request.getParameter("byuzhiDouble1");
			String byuzhiDouble2 = (String) request.getParameter("byuzhiDouble2");
			String byuyuanId = (String) request.getParameter("byuyuanId");
			String byumenId = (String) request.getParameter("byumenId");
			String byuzhiId = (String) request.getParameter("byuzhiId");
			Byuzhi byuzhi = new Byuzhi();
			
			if (StringUtil.isNotEmpty(byuzhiId)) {
				byuzhi = byuzhiService.getByuzhi(Integer.parseInt(byuzhiId));
			}
			if (StringUtil.isNotEmpty(byuzhiName)) {
				byuzhi.setByuzhiName(byuzhiName);
			}
			if (StringUtil.isNotEmpty(byuzhiMark)) {
				byuzhi.setByuzhiMark(byuzhiMark);
			}
			if (StringUtil.isNotEmpty(byuzhiMark1)) {
				byuzhi.setByuzhiMark1(byuzhiMark1);
			}
			if (StringUtil.isNotEmpty(byuzhiMark2)) {
				byuzhi.setByuzhiMark2(byuzhiMark2);
			}
			if (StringUtil.isNotEmpty(byuzhiType)) {
				byuzhi.setByuzhiType(Integer.parseInt(byuzhiType));
			}
			if (StringUtil.isNotEmpty(byuzhiType1)) {
				byuzhi.setByuzhiType1(Integer.parseInt(byuzhiType1));
			}
			if (StringUtil.isNotEmpty(byuzhiType2)) {
				byuzhi.setByuzhiType2(Integer.parseInt(byuzhiType2));
			}
			if (StringUtil.isNotEmpty(byuzhiDouble)) {
				byuzhi.setByuzhiDouble(Double.parseDouble(byuzhiDouble));
			}
			if (StringUtil.isNotEmpty(byuzhiDouble1)) {
				byuzhi.setByuzhiDouble1(Double.parseDouble(byuzhiDouble1));
			}
			if (StringUtil.isNotEmpty(byuzhiDouble2)) {
				byuzhi.setByuzhiDouble2(Double.parseDouble(byuzhiDouble2));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				byuzhi.setByumenId(Integer.parseInt(byumenId));
				Byumen byumen = new Byumen();
				byumen = byumenService.getByumen(Integer.parseInt(byumenId));
				byuzhi.setByumenName(byumen.getByumenName());
				byuzhi.setByuyuanId(byumen.getByuyuanId());
				byuzhi.setByuyuanName(byumen.getByuyuanName());
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				byuzhi.setByuyuanId(Integer.parseInt(byuyuanId));
				Byuyuan byuyuan = new Byuyuan();
				byuyuan = byuyuanService.getByuyuan(Integer.parseInt(byuyuanId));
				byuzhi.setByuyuanName(byuyuan.getByuyuanName());
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				byuzhiService.modifyByuzhi(byuzhi);
			} else {
				byuzhiService.save(byuzhi);
			}
			result.put("success", "true");

			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getByuzhis")
	public void getByuzhis(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String byuzhiName = (String) request.getParameter("byuzhiName");
		String byuzhiType = (String) request.getParameter("byuzhiType");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		String byumenId = (String) request.getParameter("byumenId");
		Byuzhi byuzhi = new Byuzhi();
		if (StringUtil.isNotEmpty(byuzhiName)) {
			byuzhi.setByuzhiName(byuzhiName);
		}
		if (StringUtil.isNotEmpty(byuzhiType)) {
			byuzhi.setByuzhiType(Integer.parseInt(byuzhiType));
		}
		if (StringUtil.isNotEmpty(byuyuanId)) {
			byuzhi.setByuyuanId(Integer.parseInt(byuyuanId));
		}
		if (StringUtil.isNotEmpty(byumenId)) {
			byuzhi.setByumenId(Integer.parseInt(byumenId));
		}
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			JSONArray jsonArray = JSONArray.fromObject(byuzhiService.queryByuzhis(byuzhi, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = byuzhiService.queryByuzhis(byuzhi, 0,0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteByuzhi")
	public void deleteByuzhi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				byuzhiService.deleteByuzhi(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/byuzhiComboList")
	public void byuzhiComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String byuzhiName = (String) request.getParameter("byuzhiName");
		String byuzhiType = (String) request.getParameter("byuzhiType");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		String byumenId = (String) request.getParameter("byumenId");
		Byuzhi byuzhi = new Byuzhi();
		if (StringUtil.isNotEmpty(byuzhiName)) {
			byuzhi.setByuzhiName(byuzhiName);
		}
		if (StringUtil.isNotEmpty(byuzhiType)) {
			byuzhi.setByuzhiType(Integer.parseInt(byuzhiType));
		}
		if (StringUtil.isNotEmpty(byuyuanId)) {
			byuzhi.setByuyuanId(Integer.parseInt(byuyuanId));
		}
		if (StringUtil.isNotEmpty(byumenId)) {
			byuzhi.setByumenId(Integer.parseInt(byumenId));
		}
		try {
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("byuzhiName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(byuzhiService.queryByuzhis(byuzhi,0,0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
