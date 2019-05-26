package com.sgai.property.common.util;

/**
 * 字符串处理
 *
 * @author 146584
 * @date 2017-11-22 10:34
 */
public class StringUtil {

    /**
     * 模糊搜索关键字处理
     * @param keyWord
     * @return
     */
    public static String fuzzySearchStr (String keyWord){
        if(keyWord != null) {
//			反斜线符号会被语法分析程序剥离一次，在进行模式匹配时，又会被剥离一次，最后会剩下一个反斜线符号接受匹配
            keyWord = keyWord.replaceAll("\\\\", "\\\\\\\\");
            keyWord = keyWord.replaceAll("%", "\\\\%");
        }
        return keyWord;
    }
}
