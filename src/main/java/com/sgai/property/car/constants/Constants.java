package com.sgai.property.car.constants;


/**
 * 常量类
 *
 * @author 146584
 * @date 2017-11-3 14:35
 */
public class Constants {
    public static final String J2CACHE_CHANNEL_NAME = "car";
    public static final String TOKEN_KEY_PREFIX = "bjfzx:accessToken:";
    public final static String REDIS_KEY_ORGAN_KEY = "bjfzx:car:organ:key";
    public static final Long ORGAN_TOKEN_EXPIRE_IN = 7000L;
    public static final Long TOKEN_EXPIRE_IN = 3600 * 12L;
    public static final Long ORGAN_EXPIRE = 600L;

    public final static Long YES = 1L;  //删除
    public final static Long NO = 0L;  //正常
    public final static Long TRUE = 0L;  //是
    public final static Long FALSE = 1L;  //否
    public final static Long PASSING = 1L;  //审核中
    public final static Long PASS = 2L;  //审核通过
    public final static Long NO_PASS = 3L;  //审核不通过

    public static final Long IMAGE_UPLOAD_SIZE_LIMIT = 10485760L; //10M
    public static final String imgType = "gif,png,bmp,jpeg,jpg"; //图片类型

    public static final class meetingType {
        public static final String ADD = "add"; //插入
        public static final String DEL = "del"; //删除
        public static final String MOD = "mod"; //修改
    }
    public static final class carStatus {
        public static final Long USE = 0L; //启用
        public static final Long STOP = 1L; //停用
    }
    public static final class room {
        public static final Integer nameLenght = 20;
        public static final Integer descLenght = 200;
    }
    //车辆管理的分页参数
    public static final class carGearBoxType {
        public static final Integer PAGENUM = 1; //第几页
        public static final Integer  PAGESIZE = 20; //默认一页显示个数20
    }
    //0 未提交审核 1 审核中 2 审核通过 3审核不通过
    public static final Long AUDIT_STATUS_0 = 0L;  //未提交审核
    public static final Long AUDIT_STATUS_1 = 1L;  //审核中
    public static final Long AUDIT_STATUS_2 = 2L;  //审核通过
    public static final Long AUDIT_STATUS_3 = 3L;  //审核不通过
    public static final Long AUDIT_STATUS_4 = 4L;  //已归还
    public static final Long AUDIT_STATUS_5 = 5L;  //已归还但未到归还时间

    public static final String DATEFORMAT_YYYYMMDD = "yyyy-MM-dd";  //日期格式化
    public static final String DATEFORMAT_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String DATEFORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";  //日期格式化


    public static final class myCarUserRelationInfo {
        public static final Long AUDIT_STATUS_0 = 0L;  //预约者提交预约
        public static final Long AUDIT_STATUS_1 = 1L;  //审核者审批中
        public static final Long AUDIT_STATUS_2 = 2L;  //审核通过
        public static final Long AUDIT_STATUS_3 = 3L;  //审核不通过

    }
    public static final String PC_TOKEN_PREFIX = "PC_";
}
