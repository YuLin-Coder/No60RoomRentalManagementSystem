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
public class YxinxiAction {
	@Autowired
	private YxinxiService yxinxiService;
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

	@RequestMapping("/getYxinxis")
	public void getYxinxis(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String yxinxiName = (String) request.getParameter("yxinxiName");
		String yxinxiId = (String) request.getParameter("yxinxiId");
		String yxtypeId = (String) request.getParameter("yxtypeId");
		String yxinxiType = (String) request.getParameter("yxinxiType");
		String yxinxiType1 = (String) request.getParameter("yxinxiType1");
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
		Yxinxi yxinxi = new Yxinxi();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(yxinxiName)) {
				yxinxi.setYxinxiName(yxinxiName);
			}
			if (StringUtil.isNotEmpty(yxinxiId)) {
				yxinxi.setYxinxiId(Integer.parseInt(yxinxiId));
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yxinxi.setYxtypeId(Integer.parseInt(yxtypeId));
			}
			if (StringUtil.isNotEmpty(yxinxiType)) {
				yxinxi.setYxinxiType(Integer.parseInt(yxinxiType));
			}
			if (StringUtil.isNotEmpty(yxinxiType1)) {
				yxinxi.setYxinxiType1(Integer.parseInt(yxinxiType1));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yxinxi.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				yxinxi.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				yxinxi.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				yxinxi.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yxinxi.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yxinxi.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yxinxi.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yxinxi.setBuzhiId(Integer.parseInt(buzhiId));
			}
			JSONArray jsonArray = JSONArray.fromObject(yxinxiService.queryYxinxis(
					yxinxi, pageBean.getStart(), pageBean.getRows(), sdate, edate));
			JSONObject result = new JSONObject();
			int total = yxinxiService.queryYxinxis(yxinxi, 0,0, sdate, edate).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addYxinxi")
	public void addYxinxi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String yxinxiName = (String) request.getParameter("yxinxiName");
			String yxinxiMark = (String) request.getParameter("yxinxiMark");
			String yxinxiMark1 = (String) request.getParameter("yxinxiMark1");
			String yxinxiMark2 = (String) request.getParameter("yxinxiMark2");
			String yxinxiDate = (String) request.getParameter("yxinxiDate");
			String yxinxiType = (String) request.getParameter("yxinxiType");
			String yxinxiType1 = (String) request.getParameter("yxinxiType1");
			String yxinxiType2 = (String) request.getParameter("yxinxiType2");
			String yxinxiZong = (String) request.getParameter("yxinxiZong");
			String yxinxiZong1 = (String) request.getParameter("yxinxiZong1");
			String yxinxiZong2 = (String) request.getParameter("yxinxiZong2");
			String yxinxiDouble = (String) request.getParameter("yxinxiDouble");
			String yxinxiDouble1 = (String) request.getParameter("yxinxiDouble1");
			String yxinxiDouble2 = (String) request.getParameter("yxinxiDouble2");
			String yxtypeId = (String) request.getParameter("yxtypeId");
			String yonghuId = (String) request.getParameter("yonghuId");
			String yxinxiId = (String) request.getParameter("yxinxiId");
			Yxinxi yxinxi = new Yxinxi();

			if (StringUtil.isNotEmpty(yxinxiId)) {
				yxinxi = yxinxiService.getYxinxi(Integer.parseInt(yxinxiId));
			}
			if (StringUtil.isNotEmpty(yxinxiName)) {
				yxinxi.setYxinxiName(yxinxiName);
			}
			if (StringUtil.isNotEmpty(yxinxiMark)) {
				yxinxi.setYxinxiMark(yxinxiMark);
			}
			if (StringUtil.isNotEmpty(yxinxiMark1)) {
				yxinxi.setYxinxiMark1(yxinxiMark1);
			}
			if (StringUtil.isNotEmpty(yxinxiMark2)) {
				yxinxi.setYxinxiMark2(yxinxiMark2);
			}
			if (StringUtil.isNotEmpty(yxinxiDate)) {
				yxinxi.setYxinxiDate(DateUtil.formatString(yxinxiDate,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yxinxiType)) {
				yxinxi.setYxinxiType(Integer.parseInt(yxinxiType));
			}
			if (StringUtil.isNotEmpty(yxinxiType1)) {
				yxinxi.setYxinxiType1(Integer.parseInt(yxinxiType1));
			}
			if (StringUtil.isNotEmpty(yxinxiType2)) {
				yxinxi.setYxinxiType2(Integer.parseInt(yxinxiType2));
			}
			if (StringUtil.isNotEmpty(yxinxiZong)) {
				yxinxi.setYxinxiZong(Integer.parseInt(yxinxiZong));
			}
			if (StringUtil.isNotEmpty(yxinxiZong1)) {
				yxinxi.setYxinxiZong1(Integer.parseInt(yxinxiZong1));
			}
			if (StringUtil.isNotEmpty(yxinxiZong2)) {
				yxinxi.setYxinxiZong2(Integer.parseInt(yxinxiZong2));
			}
			if (StringUtil.isNotEmpty(yxinxiDouble)) {
				yxinxi.setYxinxiDouble(Double.parseDouble(yxinxiDouble));
			}
			if (StringUtil.isNotEmpty(yxinxiDouble1)) {
				yxinxi.setYxinxiDouble1(Double.parseDouble(yxinxiDouble1));
			}
			if (StringUtil.isNotEmpty(yxinxiDouble2)) {
				yxinxi.setYxinxiDouble2(Double.parseDouble(yxinxiDouble2));
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yxinxi.setYxtypeId(Integer.parseInt(yxtypeId));
				Yxtype yxtype = new Yxtype();
				yxtype = yxtypeService.getYxtype(Integer.parseInt(yxtypeId));
				yxinxi.setYxtypeName(yxtype.getYxtypeName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yxinxi.setYonghuId(Integer.parseInt(yonghuId));
				Yonghu yonghu = new Yonghu();
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
				yxinxi.setYonghuName(yonghu.getYonghuName());
				yxinxi.setByumenId(yonghu.getByumenId());
				yxinxi.setByumenName(yonghu.getByumenName());
				yxinxi.setByuyuanId(yonghu.getByuyuanId());
				yxinxi.setByuyuanName(yonghu.getByuyuanName());
				yxinxi.setByuzhiId(yonghu.getByuzhiId());
				yxinxi.setByuzhiName(yonghu.getByuzhiName());
			}
			/****充值审核开始****/
//			if (StringUtil.isNotEmpty(yxinxiId)) {
//				yxinxiService.modifyYxinxi(yxinxi);
//				if(yxinxi.getYxinxiType()==1){
//					Yonghu yonghu = yonghuService.getYonghu(yxinxi.getYonghuId());
//					yonghu.setYonghuDouble(yonghu.getYonghuDouble()+yxinxi.getYxinxiDouble());
//					yonghuService.modifyYonghu(yonghu);
//				}
//				result.put("success", "true");
//				ResponseUtil.write(response, result);
//			} else {
//				Date date = new Date();
//				yxinxi.setYxinxiDate(date);
//				yxinxi.setYxinxiType(0);
//				yxinxiService.save(yxinxi);
//				result.put("success", "true");
//				ResponseUtil.write(response, result);
//			}
			/****充值审核结束****/
			/****用户充值开始****/
			if (StringUtil.isNotEmpty(yxinxiId)) {
				yxinxiService.modifyYxinxi(yxinxi);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				Date date = new Date();
				yxinxi.setYxinxiDate(date);
				yxinxi.setYxinxiType(0);
				yxinxiService.save(yxinxi);
				Yonghu yonghu = yonghuService.getYonghu(yxinxi.getYonghuId());
				yonghu.setYonghuDouble(yonghu.getYonghuDouble()+yxinxi.getYxinxiDouble());
				yonghuService.modifyYonghu(yonghu);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
			/****用户充值结束****/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteYxinxi")
	public void deleteYxinxi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		String delIds = (String) request.getParameter("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				yxinxiService.deleteYxinxi(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/yxinxiComboList")
	public void yxinxiComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String yxinxiName = (String) request.getParameter("yxinxiName");
		String yxinxiId = (String) request.getParameter("yxinxiId");
		String yxtypeId = (String) request.getParameter("yxtypeId");
		String yxinxiType = (String) request.getParameter("yxinxiType");
		String yxinxiType1 = (String) request.getParameter("yxinxiType1");
		String yonghuId = (String) request.getParameter("yonghuId");
		String byumenId = (String) request.getParameter("byumenId");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		String byuzhiId = (String) request.getParameter("byuzhiId");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		Yxinxi yxinxi = new Yxinxi();
		try {
			if (StringUtil.isNotEmpty(yxinxiName)) {
				yxinxi.setYxinxiName(yxinxiName);
			}
			if (StringUtil.isNotEmpty(yxinxiId)) {
				yxinxi.setYxinxiId(Integer.parseInt(yxinxiId));
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yxinxi.setYxtypeId(Integer.parseInt(yxtypeId));
			}
			if (StringUtil.isNotEmpty(yxinxiType)) {
				yxinxi.setYxinxiType(Integer.parseInt(yxinxiType));
			}
			if (StringUtil.isNotEmpty(yxinxiType1)) {
				yxinxi.setYxinxiType1(Integer.parseInt(yxinxiType1));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yxinxi.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				yxinxi.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				yxinxi.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				yxinxi.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yxinxi.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yxinxi.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yxinxi.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yxinxi.setBuzhiId(Integer.parseInt(buzhiId));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("yxinxiName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(yxinxiService.queryYxinxis(yxinxi,
					0,0, null, null)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/yxinxiTongji")
	public void yxinxiTongji(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sdate=request.getParameter("sdate");
		String edate=request.getParameter("edate");
		String userId=request.getParameter("userId");
		String tijiaoUrl = "yxinxiTongji";
		List<Integer> yxtypeIds = new ArrayList<Integer>();
		List<String> tongjiNames = new ArrayList<String>();
		List<Double> tongjiZongshus = new ArrayList<Double>();
		List<Yxtype> yxtypes = new ArrayList<Yxtype>();
		List<Yxinxi> yxinxis = new ArrayList<Yxinxi>();
		Double zongshu = 0.0;
		try {
			yxtypes = yxtypeService.queryYxtypes(null, 0,0);
			for(int i=0;i<yxtypes.size();i++){
				yxtypeIds.add(yxtypes.get(i).getYxtypeId());
				tongjiNames.add(yxtypes.get(i).getYxtypeName());
			}
			for(int i=0;i<yxtypeIds.size();i++){
				Double yxinxiZongshu = 0.0;
				Yxinxi yxinxi = new Yxinxi();
				yxinxi.setUserId(Integer.parseInt(userId));
				yxinxi.setYxtypeId(yxtypeIds.get(i));
				yxinxis = yxinxiService.queryYxinxis(yxinxi, 0,0,sdate,edate);
				for(int j=0;j<yxinxis.size();j++){
					yxinxiZongshu = yxinxiZongshu + yxinxis.size();
				}
				zongshu = zongshu + yxinxiZongshu;
				tongjiZongshus.add(yxinxiZongshu);
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

	@RequestMapping("/shangchuanYxinxi")
	public void shangchuanYxinxi(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String yxinxiId = (String) request.getParameter("yxinxiId");
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
			Yxinxi yxinxi = yxinxiService.getYxinxi(Integer.parseInt(yxinxiId));
			yxinxi.setYxinxiImg(shangchuandizhi);
			yxinxi.setYxinxiImgName(shangchuanname);
			yxinxiService.modifyYxinxi(yxinxi);
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daoruYxinxi")
	public void daoruYxinxi(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
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
				String yxinxiName = row.get(0);
				String yxinxiMark = row.get(1);
				String yxinxiMark1 = row.get(2);
				String yonghuId = row.get(3);
				Yxinxi yxinxi = new Yxinxi();
				
				if (StringUtil.isNotEmpty(yxinxiName)) {
					yxinxi.setYxinxiName(yxinxiName);
				}
				if (StringUtil.isNotEmpty(yxinxiMark)) {
					yxinxi.setYxinxiMark(yxinxiMark);
				}
				if (StringUtil.isNotEmpty(yxinxiMark1)) {
					yxinxi.setYxinxiMark1(yxinxiMark1);
				}
				if (StringUtil.isNotEmpty(yonghuId)) {
					yxinxi.setYonghuId(Integer.parseInt(yonghuId));
					Yonghu yonghu = new Yonghu();
					yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
					yxinxi.setYonghuName(yonghu.getYonghuName());
					yxinxi.setByumenId(yonghu.getByumenId());
					yxinxi.setByumenName(yonghu.getByumenName());
					yxinxi.setByuyuanId(yonghu.getByuyuanId());
					yxinxi.setByuyuanName(yonghu.getByuyuanName());
				}
				Date date = new Date();
				yxinxi.setYxinxiDate(date);
				yxinxiService.save(yxinxi);
			}
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daochuYxinxi")
	public void daochuYxinxi(HttpServletRequest request, HttpServletResponse response)
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
			Yxinxi yxinxi = new Yxinxi();
			for (int i = 0; i < str.length; i++) {
				List<String> row = new ArrayList<String>();
				yxinxi = yxinxiService.getYxinxi(Integer.parseInt(str[i]));
				row.add(TypeUtil.toString(i+1));
				row.add(yxinxi.getYonghuName());
				row.add(yxinxi.getYxinxiMark1());
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
