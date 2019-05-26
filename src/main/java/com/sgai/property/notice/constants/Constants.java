package com.sgai.property.notice.constants;


/**
 * 常量类
 * @author 146584
 * @date 2017-11-3 14:35
 */
public class Constants {
    public static final String J2CACHE_CHANNEL_NAME="car";
	public static final String TOKEN_KEY_PREFIX = "bjfzx:accessToken:";
    public static final Long ORGAN_TOKEN_EXPIRE_IN =7000L;
    public static final Long TOKEN_EXPIRE_IN = 3600*12L;
    public static final Long ORGAN_EXPIRE=600L;
	
	public final static String YES="Y";  //删除
    public final static String NO="N";  //正常


    public static final Long IMAGE_UPLOAD_SIZE_LIMIT=10485760L; //10M
    public static final String imgType = "gif,png,bmp,jpeg,jpg"; //图片类型
    
    public static final class Notice {
    	//公告状态
    	public static final Integer DSH = 1; //待审核
    	public static final Integer WTG = 2; //未通过
    	public static final Integer YTG = 4; //已通过
    	public static final Integer YFB = 8; //已发布
    	public static final Integer YCH = 16; //已撤回
    	
    	//发布范围全部可见
    	public static final Integer INFO_SCOPE_IS_ALL = 1;	//是
    	public static final Integer INFO_SCOPE_IS_NOT_ALL = 0; //否
    	
    	//是否回执
    	public static final Integer RECEIPT_Y = 1; //已回执
    	public static final Integer RECEIPT_N = 0; //未回执
    	
    	//是否审核
    	public static final Integer APPROVAL_Y = 1; //需要
    	public static final Integer APPROVAL_N = 0; //不需要
    	
    	//公告发起人
    	public static final Long CREATE_TYPE_EMP = 0L; //员工
    	public static final Long CREATE_TYPE_ORG = 1L; //组织名片持有者
		// 置顶0：不需要，1：需要
    	public static final Long INFO_IS_TOP_Y = 1L; //需要
    	public static final Long INFO_IS_TOP_N = 0L; //不需要
    }

    public static final String PC_TOKEN_PREFIX = "PC_";
}
