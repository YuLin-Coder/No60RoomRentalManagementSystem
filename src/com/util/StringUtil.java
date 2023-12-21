package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isEmpty(String str){
		if("".equals(str)|| str==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public static String jiequString(String str,int n){
		//去除富文本
		String regx = "(<.+?>)|(</.+?>)";
        Matcher matcher = Pattern.compile(regx).matcher(str);
        while (matcher.find()){
            // 替换图片
        	str= matcher.replaceAll("").replace(" ", "");
        }
        //截取文字
		String xinString = "";
		if(str.length()>=n){
			xinString = str.substring(0,n);
		}else{
			xinString = str;
		}
		return xinString;
	}
}
