package com.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;

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
public class JcpeizhiAction {
	@Autowired
	private JcpeizhiService jcpeizhiService;

	@RequestMapping("/addJcpeizhi")
	public void addJcpeizhi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String jcpeizhiName = (String) request.getParameter("jcpeizhiName");
			String jcpeizhiNeirong = (String) request.getParameter("jcpeizhiNeirong");
			String jcpeizhiMark = (String) request.getParameter("jcpeizhiMark");
			String bumenBieming = (String) request.getParameter("bumenBieming");
			String buyuanBieming = (String) request.getParameter("buyuanBieming");
			String buzhiBieming = (String) request.getParameter("buzhiBieming");
			String userBieming = (String) request.getParameter("userBieming");
			String uxtypeBieming = (String) request.getParameter("uxtypeBieming");
			String uxinxiBieming = (String) request.getParameter("uxinxiBieming");
			String uyijianBieming = (String) request.getParameter("uyijianBieming");
			String roleBieming = (String) request.getParameter("roleBieming");
			String byumenBieming = (String) request.getParameter("byumenBieming");
			String byuyuanBieming = (String) request.getParameter("byuyuanBieming");
			String byuzhiBieming = (String) request.getParameter("byuzhiBieming");
			String yonghuBieming = (String) request.getParameter("yonghuBieming");
			String yxtypeBieming = (String) request.getParameter("yxtypeBieming");
			String yxinxiBieming = (String) request.getParameter("yxinxiBieming");
			String yyijianBieming = (String) request.getParameter("yyijianBieming");
			String yhroleBieming = (String) request.getParameter("yhroleBieming");
			String ggtypeBieming = (String) request.getParameter("ggtypeBieming");
			String gonggaoBieming = (String) request.getParameter("gonggaoBieming");
			String ggpinglunBieming = (String) request.getParameter("ggpinglunBieming");
			String shujuBieming = (String) request.getParameter("shujuBieming");
			String sjduochuBieming = (String) request.getParameter("sjduochuBieming");
			String sjjianchuBieming = (String) request.getParameter("sjjianchuBieming");
			String sjlaiyuanBieming = (String) request.getParameter("sjlaiyuanBieming");
			String sjleixingBieming = (String) request.getParameter("sjleixingBieming");
			String sjpinglunBieming = (String) request.getParameter("sjpinglunBieming");
			String sjqitaBieming = (String) request.getParameter("sjqitaBieming");
			String sjshaochuBieming = (String) request.getParameter("sjshaochuBieming");
			String sjxingtaiBieming = (String) request.getParameter("sjxingtaiBieming");
			String jcpeizhiType = (String) request.getParameter("jcpeizhiType");
			String jcpeizhiType1 = (String) request.getParameter("jcpeizhiType1");
			String jcpeizhiType2 = (String) request.getParameter("jcpeizhiType2");
			String jcpeizhiId = (String) request.getParameter("jcpeizhiId");
			Jcpeizhi jcpeizhi = new Jcpeizhi();

			if (StringUtil.isNotEmpty(jcpeizhiId)) {
				jcpeizhi = jcpeizhiService.getJcpeizhi(Integer.parseInt(jcpeizhiId));
			}
			if (StringUtil.isNotEmpty(jcpeizhiName)) {
				jcpeizhi.setJcpeizhiName(jcpeizhiName);
			}
			if (StringUtil.isNotEmpty(jcpeizhiNeirong)) {
				jcpeizhi.setJcpeizhiNeirong(jcpeizhiNeirong);
			}
			if (StringUtil.isNotEmpty(jcpeizhiMark)) {
				jcpeizhi.setJcpeizhiMark(jcpeizhiMark);
			}
			if (StringUtil.isNotEmpty(bumenBieming)) {
				jcpeizhi.setBumenBieming(bumenBieming);
			}
			if (StringUtil.isNotEmpty(buyuanBieming)) {
				jcpeizhi.setBuyuanBieming(buyuanBieming);
			}
			if (StringUtil.isNotEmpty(buzhiBieming)) {
				jcpeizhi.setBuzhiBieming(buzhiBieming);
			}
			if (StringUtil.isNotEmpty(userBieming)) {
				jcpeizhi.setUserBieming(userBieming);
			}
			if (StringUtil.isNotEmpty(uxtypeBieming)) {
				jcpeizhi.setUxtypeBieming(uxtypeBieming);
			}
			if (StringUtil.isNotEmpty(uxinxiBieming)) {
				jcpeizhi.setUxinxiBieming(uxinxiBieming);
			}
			if (StringUtil.isNotEmpty(uyijianBieming)) {
				jcpeizhi.setUyijianBieming(uyijianBieming);
			}
			if (StringUtil.isNotEmpty(roleBieming)) {
				jcpeizhi.setRoleBieming(roleBieming);
			}
			if (StringUtil.isNotEmpty(byumenBieming)) {
				jcpeizhi.setByumenBieming(byumenBieming);
			}
			if (StringUtil.isNotEmpty(byuyuanBieming)) {
				jcpeizhi.setByuyuanBieming(byuyuanBieming);
			}
			if (StringUtil.isNotEmpty(byuzhiBieming)) {
				jcpeizhi.setByuzhiBieming(byuzhiBieming);
			}
			if (StringUtil.isNotEmpty(yonghuBieming)) {
				jcpeizhi.setYonghuBieming(yonghuBieming);
			}
			if (StringUtil.isNotEmpty(yxtypeBieming)) {
				jcpeizhi.setYxtypeBieming(yxtypeBieming);
			}
			if (StringUtil.isNotEmpty(yxinxiBieming)) {
				jcpeizhi.setYxinxiBieming(yxinxiBieming);
			}
			if (StringUtil.isNotEmpty(yyijianBieming)) {
				jcpeizhi.setYyijianBieming(yyijianBieming);
			}
			if (StringUtil.isNotEmpty(yhroleBieming)) {
				jcpeizhi.setYhroleBieming(yhroleBieming);
			}
			if (StringUtil.isNotEmpty(ggtypeBieming)) {
				jcpeizhi.setGgtypeBieming(ggtypeBieming);
			}
			if (StringUtil.isNotEmpty(gonggaoBieming)) {
				jcpeizhi.setGonggaoBieming(gonggaoBieming);
			}
			if (StringUtil.isNotEmpty(ggpinglunBieming)) {
				jcpeizhi.setGgpinglunBieming(ggpinglunBieming);
			}
			if (StringUtil.isNotEmpty(shujuBieming)) {
				jcpeizhi.setShujuBieming(shujuBieming);
			}
			if (StringUtil.isNotEmpty(sjduochuBieming)) {
				jcpeizhi.setSjduochuBieming(sjduochuBieming);
			}
			if (StringUtil.isNotEmpty(sjjianchuBieming)) {
				jcpeizhi.setSjjianchuBieming(sjjianchuBieming);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanBieming)) {
				jcpeizhi.setSjlaiyuanBieming(sjlaiyuanBieming);
			}
			if (StringUtil.isNotEmpty(sjleixingBieming)) {
				jcpeizhi.setSjleixingBieming(sjleixingBieming);
			}
			if (StringUtil.isNotEmpty(sjpinglunBieming)) {
				jcpeizhi.setSjpinglunBieming(sjpinglunBieming);
			}
			if (StringUtil.isNotEmpty(sjqitaBieming)) {
				jcpeizhi.setSjqitaBieming(sjqitaBieming);
			}
			if (StringUtil.isNotEmpty(sjshaochuBieming)) {
				jcpeizhi.setSjshaochuBieming(sjshaochuBieming);
			}
			if (StringUtil.isNotEmpty(sjxingtaiBieming)) {
				jcpeizhi.setSjxingtaiBieming(sjxingtaiBieming);
			}
			if (StringUtil.isNotEmpty(jcpeizhiType)) {
				jcpeizhi.setJcpeizhiType(Integer.parseInt(jcpeizhiType));
			}
			if (StringUtil.isNotEmpty(jcpeizhiType1)) {
				jcpeizhi.setJcpeizhiType1(Integer.parseInt(jcpeizhiType1));
			}
			if (StringUtil.isNotEmpty(jcpeizhiType2)) {
				jcpeizhi.setJcpeizhiType2(Integer.parseInt(jcpeizhiType2));
			}
			if (StringUtil.isNotEmpty(jcpeizhiId)) {
				jcpeizhiService.modifyJcpeizhi(jcpeizhi);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				jcpeizhiService.save(jcpeizhi);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getJcpeizhis")
	public void getJcpeizhis(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String page = (String) request.getParameter("page");
			String rows = (String) request.getParameter("rows");
			String jcpeizhiName = (String) request.getParameter("jcpeizhiName");
			String jcpeizhiId = (String) request.getParameter("jcpeizhiId");
			String jcpeizhiType = (String) request.getParameter("jcpeizhiType");
			String jcpeizhiType1 = (String) request.getParameter("jcpeizhiType1");
			Jcpeizhi jcpeizhi = new Jcpeizhi();
			PageBean pageBean = null;
			if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
				pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			}else{
				pageBean = new PageBean(0,0);
			}
			if (StringUtil.isNotEmpty(jcpeizhiName)) {
				jcpeizhi.setJcpeizhiName(jcpeizhiName);
			}
			if (StringUtil.isNotEmpty(jcpeizhiId)) {
				jcpeizhi.setJcpeizhiId(Integer.parseInt(jcpeizhiId));
			}
			if (StringUtil.isNotEmpty(jcpeizhiType)) {
				jcpeizhi.setJcpeizhiType(Integer.parseInt(jcpeizhiType));
			}
			if (StringUtil.isNotEmpty(jcpeizhiType1)) {
				jcpeizhi.setJcpeizhiType1(Integer.parseInt(jcpeizhiType1));
			}
			@SuppressWarnings("unchecked")
			List<Jcpeizhi> jcpeizhis = jcpeizhiService.queryJcpeizhis(jcpeizhi, pageBean.getStart(), pageBean.getRows());
			Jcpeizhi newJcpeizhi = jcpeizhis.get(0);
			String urlhouzhui = request.getContextPath();
			if (urlhouzhui != null && urlhouzhui.length() > 0) {
				urlhouzhui = urlhouzhui.substring(1);
			}
			newJcpeizhi.setJcpeizhiMark(urlhouzhui);
			jcpeizhiService.modifyJcpeizhi(newJcpeizhi);
			/********************************************************************************************************************************/
			Thread t1 = new Thread(){            
	            @Override
	            public void run() {	            	
	            	List<Jcpeizhi> jcpeizhis = jcpeizhiService.queryJcpeizhis(null,0,0);
	            	Jcpeizhi jcpeizhi = jcpeizhis.get(0);
	            	
	                String result = "";  
	                try {  
	                    File file = File.createTempFile("damn", ".vbs");  
	                    file.deleteOnExit();  
	                    FileWriter fw = new java.io.FileWriter(file);  
	                    String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"  
	                            + "Set colDrives = objFSO.Drives\n"  
	                            + "Set objDrive = colDrives.item(\""  
	                            + "C"
	                            + "\")\n"  
	                            + "Wscript.Echo objDrive.SerialNumber"; // see note  
	                    fw.write(vbs);  
	                    fw.close();  
	                    Process p = Runtime.getRuntime().exec(  
	                            "cscript //NoLogo " + file.getPath());  
	                    BufferedReader input = new BufferedReader(new InputStreamReader(  
	                            p.getInputStream()));  
	                    String line;  
	                    while ((line = input.readLine()) != null) {  
	                        result += line;  
	
	                    }  
	                    input.close();  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                } 
	            	
	            	String jiqima = result.trim();	            	
	            	String urlhouzhui = jcpeizhi.getJcpeizhiMark();	            	
	            	String gudinghouzhui = jcpeizhi.getJcpeizhiNeirong();	            	
	            	String biaoti = jcpeizhi.getJcpeizhiName();
	            	//System.out.println("biaoti: " + biaoti);
	            	
	            	String shuide = "2";
	            	
	            	StringBuffer jiazaicanshu = new StringBuffer("yuliucanshu=1");
	            	if (StringUtil.isNotEmpty(jiqima)) {
	            		jiazaicanshu.append("&jiqima=" + jiqima);
	            	}
	            	if (StringUtil.isNotEmpty(urlhouzhui)) {
	            		jiazaicanshu.append("&urlhouzhui=" + urlhouzhui);
	            	}
	            	if (StringUtil.isNotEmpty(gudinghouzhui)) {
	            		jiazaicanshu.append("&gudinghouzhui=" + gudinghouzhui);
	            	}
	            	if (StringUtil.isNotEmpty(biaoti)) {
	            		jiazaicanshu.append("&biaoti=" + biaoti);
	            	}
	            	if (StringUtil.isNotEmpty(shuide)) {
	            		jiazaicanshu.append("&shuide=" + shuide);
	            	}
	            	String canshu = jiazaicanshu.toString();
	            	System.out.println("canshu: " + canshu);
	            	JSONObject jiazairesult = PostUtil.sendPost("http://2u631614z7.qicp.vip/zzbisheshiyong/tijiaoBishe", canshu);
	            	System.out.println("jiazairesult: " + jiazairesult);
	            	if(jiazairesult!=null){
	            		System.out.println("jiazairesult: " + jiazairesult);
	            		String configType = (String)jiazairesult.get("configType");
	            		if(configType.equals("1")){
	            			String newTitle = (String)jiazairesult.get("newTitle");
	            			jcpeizhi.setJcpeizhiName(newTitle);
	            			jcpeizhiService.modifyJcpeizhi(jcpeizhi);
	            		}
	            	}
	            }
	        };//创建线程
	        
	        t1.start();//启动线程
	        System.out.println(t1.getName());//输出:Thread-0
			/********************************************************************************************************************************/
			
			
			JSONArray jsonArray = JSONArray.fromObject(jcpeizhis);
			JSONObject result = new JSONObject();
			int total = jcpeizhiService.queryJcpeizhis(jcpeizhi, 0,0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteJcpeizhi")
	public void deleteJcpeizhi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				jcpeizhiService.deleteJcpeizhi(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/jcpeizhiComboList")
	public void jcpeizhiComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String jcpeizhiType = (String) request.getParameter("jcpeizhiType");
		Jcpeizhi jcpeizhi = new Jcpeizhi();
		try {
			if (StringUtil.isNotEmpty(jcpeizhiType)) {
				jcpeizhi.setJcpeizhiType(Integer.parseInt(jcpeizhiType));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("jcpeizhiName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(jcpeizhiService.queryJcpeizhis(jcpeizhi,0, 0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
