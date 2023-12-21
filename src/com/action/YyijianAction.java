package com.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.model.*;
import com.service.*;
import com.util.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
//导入导出

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

@Controller
public class YyijianAction {
	@Autowired
	private YyijianService yyijianService;
	@Autowired
	private YxtypeService yxtypeService;
	@Autowired
	private YonghuService yonghuService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	@RequestMapping("/getYyijians")
	public void getYyijians(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String yyijianName = (String) request.getParameter("yyijianName");
		String yyijianId = (String) request.getParameter("yyijianId");
		String yxtypeId = (String) request.getParameter("yxtypeId");
		String yyijianType = (String) request.getParameter("yyijianType");
		String yyijianType1 = (String) request.getParameter("yyijianType1");
		String yonghuId = (String) request.getParameter("yonghuId");
		String byumenId = (String) request.getParameter("byumenId");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		String byuzhiId = (String) request.getParameter("byuzhiId");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		String sdate = (String) request.getParameter("sdate");
		String edate = (String) request.getParameter("edate");
		Yyijian yyijian = new Yyijian();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(yyijianName)) {
				yyijian.setYyijianName(yyijianName);
			}
			if (StringUtil.isNotEmpty(yyijianId)) {
				yyijian.setYyijianId(Integer.parseInt(yyijianId));
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yyijian.setYxtypeId(Integer.parseInt(yxtypeId));
			}
			if (StringUtil.isNotEmpty(yyijianType)) {
				yyijian.setYyijianType(Integer.parseInt(yyijianType));
			}
			if (StringUtil.isNotEmpty(yyijianType1)) {
				yyijian.setYyijianType1(Integer.parseInt(yyijianType1));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yyijian.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				yyijian.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				yyijian.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				yyijian.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yyijian.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yyijian.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yyijian.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yyijian.setBuzhiId(Integer.parseInt(buzhiId));
			}
			JSONArray jsonArray = JSONArray.fromObject(yyijianService.queryYyijians(
					yyijian, pageBean.getStart(), pageBean.getRows(), sdate, edate));
			JSONObject result = new JSONObject();
			int total = yyijianService.queryYyijians(yyijian, 0,0, sdate, edate).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addYyijian")
	public void addYyijian(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String yyijianName = (String) request.getParameter("yyijianName");
			String yyijianMark = (String) request.getParameter("yyijianMark");
			String yyijianMark1 = (String) request.getParameter("yyijianMark1");
			String yyijianMark2 = (String) request.getParameter("yyijianMark2");
			String yyijianDate = (String) request.getParameter("yyijianDate");
			String yyijianType = (String) request.getParameter("yyijianType");
			String yyijianType1 = (String) request.getParameter("yyijianType1");
			String yyijianType2 = (String) request.getParameter("yyijianType2");
			String yyijianZong = (String) request.getParameter("yyijianZong");
			String yyijianZong1 = (String) request.getParameter("yyijianZong1");
			String yyijianZong2 = (String) request.getParameter("yyijianZong2");
			String yyijianDouble = (String) request.getParameter("yyijianDouble");
			String yyijianDouble1 = (String) request.getParameter("yyijianDouble1");
			String yyijianDouble2 = (String) request.getParameter("yyijianDouble2");
			String yxtypeId = (String) request.getParameter("yxtypeId");
			String yonghuId = (String) request.getParameter("yonghuId");
			String yyijianId = (String) request.getParameter("yyijianId");
			Yyijian yyijian = new Yyijian();

			if (StringUtil.isNotEmpty(yyijianId)) {
				yyijian = yyijianService.getYyijian(Integer.parseInt(yyijianId));
			}
			if (StringUtil.isNotEmpty(yyijianName)) {
				yyijian.setYyijianName(yyijianName);
			}
			if (StringUtil.isNotEmpty(yyijianMark)) {
				yyijian.setYyijianMark(yyijianMark);
			}
			if (StringUtil.isNotEmpty(yyijianMark1)) {
				yyijian.setYyijianMark1(yyijianMark1);
			}
			if (StringUtil.isNotEmpty(yyijianMark2)) {
				yyijian.setYyijianMark2(yyijianMark2);
			}
			if (StringUtil.isNotEmpty(yyijianDate)) {
				yyijian.setYyijianDate(DateUtil.formatString(yyijianDate,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yyijianType)) {
				yyijian.setYyijianType(Integer.parseInt(yyijianType));
			}
			if (StringUtil.isNotEmpty(yyijianType1)) {
				yyijian.setYyijianType1(Integer.parseInt(yyijianType1));
			}
			if (StringUtil.isNotEmpty(yyijianType2)) {
				yyijian.setYyijianType2(Integer.parseInt(yyijianType2));
			}
			if (StringUtil.isNotEmpty(yyijianZong)) {
				yyijian.setYyijianZong(Integer.parseInt(yyijianZong));
			}
			if (StringUtil.isNotEmpty(yyijianZong1)) {
				yyijian.setYyijianZong1(Integer.parseInt(yyijianZong1));
			}
			if (StringUtil.isNotEmpty(yyijianZong2)) {
				yyijian.setYyijianZong2(Integer.parseInt(yyijianZong2));
			}
			if (StringUtil.isNotEmpty(yyijianDouble)) {
				yyijian.setYyijianDouble(Double.parseDouble(yyijianDouble));
			}
			if (StringUtil.isNotEmpty(yyijianDouble1)) {
				yyijian.setYyijianDouble1(Double.parseDouble(yyijianDouble1));
			}
			if (StringUtil.isNotEmpty(yyijianDouble2)) {
				yyijian.setYyijianDouble2(Double.parseDouble(yyijianDouble2));
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yyijian.setYxtypeId(Integer.parseInt(yxtypeId));
				Yxtype yxtype = new Yxtype();
				yxtype = yxtypeService.getYxtype(Integer.parseInt(yxtypeId));
				yyijian.setYxtypeName(yxtype.getYxtypeName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yyijian.setYonghuId(Integer.parseInt(yonghuId));
				Yonghu yonghu = new Yonghu();
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
				yyijian.setYonghuName(yonghu.getYonghuName());
				yyijian.setByumenId(yonghu.getByumenId());
				yyijian.setByumenName(yonghu.getByumenName());
				yyijian.setByuyuanId(yonghu.getByuyuanId());
				yyijian.setByuyuanName(yonghu.getByuyuanName());
				yyijian.setByuzhiId(yonghu.getByuzhiId());
				yyijian.setByuzhiName(yonghu.getByuzhiName());
				yyijian.setUserId(yonghu.getUserId());
				yyijian.setUserName(yonghu.getUserName());
				yyijian.setBumenId(yonghu.getBumenId());
				yyijian.setBumenName(yonghu.getBumenName());
				yyijian.setBuyuanId(yonghu.getBuyuanId());
				yyijian.setBuyuanName(yonghu.getBuyuanName());
				yyijian.setBuzhiId(yonghu.getBuzhiId());
				yyijian.setBuzhiName(yonghu.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(yyijianId)) {
				yyijianService.modifyYyijian(yyijian);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				Date date = new Date();
				yyijian.setYyijianDate(date);
				yyijianService.save(yyijian);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteYyijian")
	public void deleteYyijian(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		String delIds = (String) request.getParameter("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				yyijianService.deleteYyijian(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/yyijianComboList")
	public void yyijianComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String yyijianName = (String) request.getParameter("yyijianName");
		String yyijianId = (String) request.getParameter("yyijianId");
		String yxtypeId = (String) request.getParameter("yxtypeId");
		String yyijianType = (String) request.getParameter("yyijianType");
		String yyijianType1 = (String) request.getParameter("yyijianType1");
		String yonghuId = (String) request.getParameter("yonghuId");
		String byumenId = (String) request.getParameter("byumenId");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		String byuzhiId = (String) request.getParameter("byuzhiId");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		Yyijian yyijian = new Yyijian();
		try {
			if (StringUtil.isNotEmpty(yyijianName)) {
				yyijian.setYyijianName(yyijianName);
			}
			if (StringUtil.isNotEmpty(yyijianId)) {
				yyijian.setYyijianId(Integer.parseInt(yyijianId));
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yyijian.setYxtypeId(Integer.parseInt(yxtypeId));
			}
			if (StringUtil.isNotEmpty(yyijianType)) {
				yyijian.setYyijianType(Integer.parseInt(yyijianType));
			}
			if (StringUtil.isNotEmpty(yyijianType1)) {
				yyijian.setYyijianType1(Integer.parseInt(yyijianType1));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yyijian.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				yyijian.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				yyijian.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				yyijian.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yyijian.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yyijian.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yyijian.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yyijian.setBuzhiId(Integer.parseInt(buzhiId));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("yyijianName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(yyijianService.queryYyijians(yyijian,
					0,0, null, null)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/yyijianTongji")
	public void yyijianTongji(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sdate=request.getParameter("sdate");
		String edate=request.getParameter("edate");
		String userId=request.getParameter("userId");
		String tijiaoUrl = "yyijianTongji";
		List<Integer> yxtypeIds = new ArrayList<Integer>();
		List<String> tongjiNames = new ArrayList<String>();
		List<Double> tongjiZongshus = new ArrayList<Double>();
		List<Yxtype> yxtypes = new ArrayList<Yxtype>();
		List<Yyijian> yyijians = new ArrayList<Yyijian>();
		Double zongshu = 0.0;
		try {
			yxtypes = yxtypeService.queryYxtypes(null, 0,0);
			for(int i=0;i<yxtypes.size();i++){
				yxtypeIds.add(yxtypes.get(i).getYxtypeId());
				tongjiNames.add(yxtypes.get(i).getYxtypeName());
			}
			for(int i=0;i<yxtypeIds.size();i++){
				Double yyijianZongshu = 0.0;
				Yyijian yyijian = new Yyijian();
				yyijian.setUserId(Integer.parseInt(userId));
				yyijian.setYxtypeId(yxtypeIds.get(i));
				yyijians = yyijianService.queryYyijians(yyijian, 0,0,sdate,edate);
				for(int j=0;j<yyijians.size();j++){
					yyijianZongshu = yyijianZongshu + yyijians.size();
				}
				zongshu = zongshu + yyijianZongshu;
				tongjiZongshus.add(yyijianZongshu);
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("tijiaoUrl", tijiaoUrl);
			session.setAttribute("tongjiNames", tongjiNames);
			session.setAttribute("tongjiZongshus", tongjiZongshus);
			session.setAttribute("zongshu", zongshu);
			response.sendRedirect("tongjitu/bingzhuangtu.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/shangchuanYyijian")
	public void shangchuanYyijian(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String yyijianId = (String) request.getParameter("yyijianId");
			String directory = "/file";
			String targetDirectory = request.getSession().getServletContext().getRealPath(directory);
	        String fileName = uploadFile.getOriginalFilename();  
			File dir = new File(targetDirectory,fileName);        
	        if(!dir.exists()){
	            dir.mkdirs();
	        }
	        //MultipartFile自带的解析方法
	        uploadFile.transferTo(dir);

			String shangchuandizhi = "/file" + "/" + fileName;
			String shangchuanname = fileName;
			Yyijian yyijian = yyijianService.getYyijian(Integer.parseInt(yyijianId));
			yyijian.setYyijianImg(shangchuandizhi);
			yyijian.setYyijianImgName(shangchuanname);
			yyijianService.modifyYyijian(yyijian);
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daoruYyijian")
	public void daoruYyijian(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String directory = "/file";
			String targetDirectory = request.getSession().getServletContext().getRealPath(directory);
	        String fileName = uploadFile.getOriginalFilename();  
			File dir = new File(targetDirectory,fileName);        
	        if(!dir.exists()){
	            dir.mkdirs();
	        }
	        //MultipartFile自带的解析方法
	        uploadFile.transferTo(dir);
			excelFile = new FileInputStream(dir);
			List<List<String>> list = new ArrayList<List<String>>();
			list = ExcelUtil.jiexiExcel(excelFile);
			for (int i = 1; i < list.size(); i++) {
				List<String> row = list.get(i);
				String yyijianName = row.get(0);
				String yyijianMark = row.get(1);
				String yyijianMark1 = row.get(2);
				String yonghuId = row.get(3);
				Yyijian yyijian = new Yyijian();
				
				if (StringUtil.isNotEmpty(yyijianName)) {
					yyijian.setYyijianName(yyijianName);
				}
				if (StringUtil.isNotEmpty(yyijianMark)) {
					yyijian.setYyijianMark(yyijianMark);
				}
				if (StringUtil.isNotEmpty(yyijianMark1)) {
					yyijian.setYyijianMark1(yyijianMark1);
				}
				if (StringUtil.isNotEmpty(yonghuId)) {
					yyijian.setYonghuId(Integer.parseInt(yonghuId));
					Yonghu yonghu = new Yonghu();
					yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
					yyijian.setYonghuName(yonghu.getYonghuName());
					yyijian.setByumenId(yonghu.getByumenId());
					yyijian.setByumenName(yonghu.getByumenName());
					yyijian.setByuyuanId(yonghu.getByuyuanId());
					yyijian.setByuyuanName(yonghu.getByuyuanName());
				}
				Date date = new Date();
				yyijian.setYyijianDate(date);
				yyijianService.save(yyijian);
			}
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daochuYyijian")
	public void daochuYyijian(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			Date date = new Date();
			String strdate = DateUtil.formatDate(date, "yyyyMMddhhmmss");
			String excelName = strdate + ".xls";
			String mubanLujing = "";
			String daochuLujing = "" + excelName;
			String delIds = (String) request.getParameter("delIds");
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			List<List<String>> list = new ArrayList<List<String>>();
			Yyijian yyijian = new Yyijian();
			for (int i = 0; i < str.length; i++) {
				List<String> row = new ArrayList<String>();
				yyijian = yyijianService.getYyijian(Integer.parseInt(str[i]));
				row.add(TypeUtil.toString(i+1));
				row.add(yyijian.getYonghuName());
				row.add(yyijian.getYyijianMark1());
				list.add(row);
			}
			if(ExcelUtil.daochuExcle(list, mubanLujing, daochuLujing)){
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}else{
				result.put("success", "true");
				result.put("errorMsg", "导出Excel出错！");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
