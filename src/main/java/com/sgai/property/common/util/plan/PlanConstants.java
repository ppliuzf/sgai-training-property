package com.sgai.property.common.util.plan;


/**
 * 常量类
 * @author 146584
 * @date 2017-11-3 14:35
 */
public class PlanConstants {
    public static final String J2CACHE_CHANNEL_NAME="car";
	public static final String TOKEN_KEY_PREFIX = "bjfzx:accessToken:";
	public final static String REDIS_KEY_ORGAN_KEY = "bjfzx:car:organ:key";
    public static final Long ORGAN_TOKEN_EXPIRE_IN =7000L;
    public static final Long TOKEN_EXPIRE_IN = 3600*12L;
    public static final Long ORGAN_EXPIRE=600L;
	
	public final static String YES="Y";  //删除
    public final static String NO="N";  //正常
    public final static Integer TRUE=1;  //是
    public final static Integer FALSE=0;  //否

    public static final Long IMAGE_UPLOAD_SIZE_LIMIT=10485760L; //10M
    public static final String imgType = "gif,png,bmp,jpeg,jpg"; //图片类型

    public static final  class meetingType{
        public static final String ADD = "add"; //插入
        public static final String DEL = "del"; //删除
        public static final String MOD = "mod"; //修改
    }

    public static final class room{
        public static final Integer nameLenght = 20;
        public static final Integer descLenght = 200;
    }
    //1 未开始 2执行中 3已结束 4已逾期5已取消
    public static final Integer MT_STATUS_1=1;  //未开始
    public static final Integer MT_STATUS_2=2;  //执行中
    public static final Integer MT_STATUS_3=3;  //已结束
    public static final Integer MT_STATUS_4=4;  //已逾期
    public static final Integer MT_STATUS_5=5;  //已取消
    
    //1 参加未请假 0 未参加未请假 2请假未参加 3请假且参加
    public static final Integer MT_INVITE_1=1;  //参加未请假
    public static final Integer MT_INVITE_0=0;  //未参加未请假
    public static final Integer MT_INVITE_2=2;  //请假未参加
    public static final Integer MT_INVITE_3=3;  //请假且参加
    
    public static final String DATEFORMAT_YYYYMMDD="yyyy-MM-dd";  //日期格式化
    public static final String DATEFORMAT_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String DATEFORMAT_YYYYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";  //日期格式化

    public static final class email{
        public static final Integer passWordLenght = 20;
        public static final Integer accountLenght = 100;
        public static final Integer mcNameLenght = 10;

    }

    public static final String PC_TOKEN_PREFIX = "PC_";
    
    public static final class task{
        public static final String STAT_NOT_BEGIN = "未开始";
        public static final String STAT_ING = "进行中";
        public static final String STAT_OVERDUE = "已逾期";
        public static final String STAT_COMPLETE = "待审核";
        public static final String STAT_4_APPROVAL = "待审核";
    }
    public static final class taskDayState{
        public static final String STAT_W = "W";
        public static final String STAT_P = "P";
    }
    
}
