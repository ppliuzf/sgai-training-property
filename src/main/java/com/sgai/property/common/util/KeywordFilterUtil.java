package com.sgai.property.common.util;

public class KeywordFilterUtil {

    public static String filter(String inputStr){
    	if(inputStr != null) {
//			反斜线符号会被语法分析程序剥离一次，在进行模式匹配时，又会被剥离一次，最后会剩下一个反斜线符号接受匹配
			inputStr = inputStr.replaceAll("\\\\", "\\\\\\\\");
    		inputStr = inputStr.replaceAll("%", "\\\\%");
    	}
        return inputStr;
    }
    
}
