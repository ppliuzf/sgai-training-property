package com.sgai.property.customer.constants;


/**
 * 常量类
 * @author 146584
 * @date 2017-11-3 14:35
 */
public class Constants {
    public static final String J2CACHE_CHANNEL_NAME="customer";
	public static final String TOKEN_KEY_PREFIX = "bjfzx:accessToken:";
	public final static String REDIS_KEY_ORGAN_KEY = "bjfzx:customer:organ:key";
    public static final Long ORGAN_TOKEN_EXPIRE_IN =7000L;
    public static final Long TOKEN_EXPIRE_IN = 3600*12L;
    public static final Long ORGAN_EXPIRE=600L;

	public final static String YES="Y";  //删除
    public final static String NO="N";  //正常
    public final static Long TRUE=1L;  //是
    public final static Long FALSE=0L;  //否

    public static final Long IMAGE_UPLOAD_SIZE_LIMIT=10485760L; //10M
    public static final String imgType = "gif,png,bmp,jpeg,jpg"; //图片类型

    public static final  class meetingType{
        public static final String ADD = "add"; //插入
        public static final String DEL = "del"; //删除
        public static final String MOD = "mod"; //修改
    }

    public static final class DefaultValue{
        public static final String All = "0";
    }

    public static final class IsDelete{
        public static final Long Yes = 1L;
        public static final Long No = 0L;
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
    
    public static final String CODE_TYPE_TSCATEGORY ="TsCategory";  //投訴
    public static final String CODE_TYPE_ABCATEGORY="AbCategory";  //安保
    public static final String CODE_TYPE_WXCATEGORY="WxCategory";  //维修
    
    public static final String DATEFORMAT_YYYYMMDD="yyyy-MM-dd";  //日期格式化
    public static final String DATEFORMAT_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String DATEFORMAT_YYYYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";  //日期格式化

    public static final class email{
        public static final Integer passWordLenght = 20;
        public static final Integer accountLenght = 100;
        public static final Integer mcNameLenght = 10;

    }

    public static final String PC_TOKEN_PREFIX = "PC_";

    public static final String DG_CODE_BIZ_CR_CERTIFICATE = "BIZ_CR_CERTIFICATE";

    public static final class SqlString{
        public static final String UpdateTimeDesc = " UPDATED_DT DESC ";
    }

    public static final class Excel{
        public static final Long Max = 10485760L;
        public static final String Type = ".xlsx .xls";
        public final static Integer excelSizeMax = 65535;
        public final static Integer excelSizeMin = 1;
        public final static String CustomExcelName = "个人客户档案模板";
    }

    public static final class SurveyStatus{
        public static final Long  Wait = 0L;
        public static final Long  Ing = 1L;
        public static final Long  Stop = 2L;
    }


    public static final class EventType{
        //投诉
        public static final String Complaints = "TS";
        public static final String Complaints_Name = "投诉事件";
        public static final String APPEMCODE_TS = "APPEMCODE-TS";
        //安保
        public static final String Security = "AB";
        public static final String Security_Name = "安保事件";
        public static final String APPEMCODE_AB = "APPEMCODE-AB";
        //应急
        public static final String Emergency = "YJ";
        public static final String Emergency_Name = "应急事件";
        public static final String APPEMCODE_YJ = "APPEMCODE-YJ";
        //修理
        public static final String Repair = "XL";
        public static final String Repair_Name = "修理事件";
        public static final String APPEMCODE_XL = "APPEMCODE-XL";

        public static final String Init_Proccess = "已创建";
    }



    public enum ComplaintsEventStatus {
        //投诉事件
        WaitAssign("待指派", 0),
        WaitHandle("待处理", 1),
        WaitVisit("待回访", 2),
        Finish("完成", 3);

        ComplaintsEventStatus(String name, int id) {
            _name = name;
            _id = id;
        }

        private String _name;
        private int _id;

        public String getName() {
            return _name;
        }

        public int getId() {
            return _id;
        }

        public static int getComplaintsEventStatusSize(){
            return ComplaintsEventStatus.values().length;
        }
    }

    public static ComplaintsEventStatus getComplaintsStatus(int complaintsEventCode) {
        switch (complaintsEventCode) {
            case 0:
                return ComplaintsEventStatus.WaitAssign;
            case 1:
                return ComplaintsEventStatus.WaitHandle;
            case 2:
                return ComplaintsEventStatus.WaitVisit;
            case 3:
                return ComplaintsEventStatus.Finish;
            default:
                return ComplaintsEventStatus.WaitAssign;
        }
    }


    public enum SecurityEventStatus {
        //安保事件
        WaitAssign("待指派", 0),
        WaitHandle("待处理", 1),
        Finish("完成", 2);

        SecurityEventStatus(String name, int id) {
            _name = name;
            _id = id;
        }

        private String _name;
        private int _id;

        public String getName() {
            return _name;
        }

        public int getId() {
            return _id;
        }

        public static int getSecurityEventStatusSize(){
            return SecurityEventStatus.values().length;
        }
    }

    public static SecurityEventStatus getSecurityStatus(int securityEventCode) {
        switch (securityEventCode) {
            case 0:
                return SecurityEventStatus.WaitAssign;
            case 1:
                return SecurityEventStatus.WaitHandle;
            case 2:
                return SecurityEventStatus.Finish;
            default:
                return SecurityEventStatus.WaitAssign;
        }
    }

    public enum EmergencyEventStatus {
        //应急事件
        WaitVerify("待核实", 0),
        WaitHandle("待处理", 1),
        Finish("完成", 2);

        EmergencyEventStatus(String name, int id) {
            _name = name;
            _id = id;
        }

        private String _name;
        private int _id;

        public String getName() {
            return _name;
        }

        public int getId() {
            return _id;
        }

        public static int getEmergencyEventStatusSize(){
            return EmergencyEventStatus.values().length;
        }
    }

    public static EmergencyEventStatus getEmergencyStatus(int emergencyEventCode) {
        switch (emergencyEventCode) {
            case 0:
                return EmergencyEventStatus.WaitVerify;
            case 1:
                return EmergencyEventStatus.WaitHandle;
            case 2:
                return EmergencyEventStatus.Finish;
            default:
                return EmergencyEventStatus.WaitVerify;
        }
    }


    public enum RepairEventStatus {
        //修理事件
        WaitOrders("待接单", 0),
        WaitVerify("待核实", 1),
        WaitAssign("待指派", 2),
        WaitHandle("待处理", 3),
        Finish("完成", 4);

        RepairEventStatus(String name, int id) {
            _name = name;
            _id = id;
        }

        private String _name;
        private int _id;

        public String getName() {
            return _name;
        }

        public int getId() {
            return _id;
        }

        public static int getRepairEventStatusSize(){
            return RepairEventStatus.values().length;
        }
    }

    public static RepairEventStatus getRepairStatus(int repairEventCode) {
        switch (repairEventCode) {
            case 0:
                return RepairEventStatus.WaitOrders;
            case 1:
                return RepairEventStatus.WaitVerify;
            case 2:
                return RepairEventStatus.WaitAssign;
            case 3:
                return RepairEventStatus.WaitHandle;
            case 4:
                return RepairEventStatus.Finish;
            default:
                return RepairEventStatus.WaitOrders;
        }
    }
}
