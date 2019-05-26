package com.sgai.property.quality.constants;


/**
 * 常量类
 * @author 146584
 * @date 2017-11-3 14:35
 */
public class Constants {
    public static final String J2CACHE_CHANNEL_NAME="quality";
    public static final String TOKEN_KEY_PREFIX = "bjfzx:accessToken:";
    public final static String REDIS_KEY_ORGAN_KEY = "qitoon:quality:organ:key";
    public static final Long ORGAN_TOKEN_EXPIRE_IN =7000L;
    public static final Long TOKEN_EXPIRE_IN = 3600*12L;
    public static final Long ORGAN_EXPIRE=600L;

    public final static String YES="Y";  //删除
    public final static String NO="N";  //正常


    public static final Long IMAGE_UPLOAD_SIZE_LIMIT=10485760L; //10M
    public static final String imgType = "gif,png,bmp,jpeg,jpg"; //图片类型

    public static final  class OrderStatus{//工单状态
        //        public static final int NEW = 0; //新发起
        public static final int DEELING = 1; //处理中
        public static final int FEEDBACKED = 2; //已反馈
        public static final int FINISHED = 3; //已完成(目前反馈完后自动变成已完成，不需要确认完成动作)
        public static final int EVALUTED = 4; //已评价
        public static final int CLOSED = 5; //已关闭
    }

    public static final  class OrderOperate{//工单操作
        public static final int NEW = 0; //发起工单
        public static final int REDISTRIBUTE = 1; //改派
        public static final int FEEDBACK = 2; //反馈
        public static final int EVALUTE = 3; //评价
        public static final int CANCEL = 4; //撤消
    }

    public static final class email{
        public static final Integer passWordLenght = 20;
        public static final Integer accountLenght = 100;
        public static final Integer mcNameLenght = 10;

    }
    public static final class room{
        public static final Integer nameLenght = 20;
        public static final Integer descLenght = 200;
    }

    public static final  class meetingType{
        public static final String ADD = "add"; //插入
        public static final String DEL = "del"; //删除
        public static final String MOD = "mod"; //修改
    }
    /*****************************下面未自定义常量*******************************************/
    public static final Integer VALID_YES=0;//有效
    public static final Integer VALID_NO=1;//无效

    public static final String PC_TOKEN_PREFIX = "PC_";

    public static final class taskDayState{
        public static final String STAT_W = "W";//未开始
        public static final String STAT_P = "P";//已完成
        public static final String STAT_J = "J";//进行中
        public static final String STAT_Y = "Y";//已预期
    }

    public static final class SgaiResponse{
        public static final String Status_Code_Normal = "1000";
        public static final String Status_Code_NotLogger = "1003";
        public static final String String_Code = "code";
        public static final String String_Msg = "msg";
        public static final String String_Data = "data";

    }
}
