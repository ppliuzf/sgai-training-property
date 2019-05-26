package com.sgai.property.commonService.constants;

public class Constants {
	public static final String J2CACHE_CHANNEL_NAME="commons";
	public static final String TOKEN_KEY_PREFIX = "bjfzx:commons:{0}:accessToken:";
	public static final String ORGAN_TOKEN_KEY_PREFIX = "sgai:commons:{0}:accessToken:organ:";
	public final static String EMP_KEY = "bjfzx:commons:{0}:emp:eiId:";
	public final static String EMP_FEED_KEY = "bjfzx:commons:{0}:emp:feed:";
    public final static String DEPT_KEY = "bjfzx:commons:{0}:dept:";
	public final static String DEPT_ROOT_KEY = "bjfzx:commons:{0}:dept:root";
    public static final String REDIS_KEY_ORGAN_KEY="bjfzx:commons:{0}:organ:";
	public final static String DEPT_INFO_KEY = "bjfzx:commons:{0}:dept:deptInfo:";
	public final static String DEPT_LIST_KEY = "bjfzx:commons:{0}:dept:deptList:";

	public static final String SCHEDULER_CALL_BACK_URL="callBackUrl";

    public static final Long ORGAN_TOKEN_EXPIRE_IN =5000L;
	public static final Long ORGAN_ACCESS_TOKEN_EXPIRE_IN =1000L*60*60*2;
    public static final Long TOKEN_EXPIRE_IN = 3600*12L;
    public static final Long ORGAN_EXPIRE=600L;
	
	public final static String YES="Y";  //删除
    public final static String NO="N";  //正常
	public static final Integer ORG_TREE_DEPT_TYPE=0;
	public static final Integer ORG_TREE_EMP_TYPE=1;

	public static final String EXCHANGE_NAME_MESSAGE = "bjfzx_rabbitMq_exchange_toonMessage";
	public static final String QUEUE_NAME_TOON_MESSAGE = "bjfzx_rabbitMq_queue_toonMessage";
	public static final String ROUTING_KEY_MESSAGE = "bjfzx_rabbitMq_routing_key_toonMessage";

    /**
	 * 同步提示信息
	 *
	 */
	public static class Sync {		
		public static final String SYNC_RESULT_SUCCESS = "同步开始执行！请耐心等待！"; 		
		public static final String SYNC_RESULT_ERROR = "有同步处理正在执行！请稍后再试！";
		
		public static final String ROOT_PARENT_ID = "-99";
	}


	public static final class SgaiResponse{
		public static final String Status_Code_Normal = "1000";
		public static final String Status_Code_NotLogger = "1003";
		public static final String String_Code = "code";
		public static final String String_Msg = "msg";
		public static final String String_Data = "data";

	}
}
