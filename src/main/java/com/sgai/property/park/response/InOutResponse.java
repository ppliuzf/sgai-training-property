package com.sgai.property.park.response;

import static com.sgai.property.reformer.utils.JsonUtil.beanFormatToJson;

/**
 * 进出场记录返回.
 *
 * @author ppliu
 * created in 2019/1/18 10:16
 */
public class InOutResponse {
    private String resCode;
    private String resMsg;

    public static String success() {
        InOutResponse inOutResponse = new InOutResponse();
        inOutResponse.setResCode("0");
        inOutResponse.setResMsg("成功");
        return inOutResponse.toString();
    }

    public static String failed() {
        InOutResponse inOutResponse = new InOutResponse();
        inOutResponse.setResCode("1");
        inOutResponse.setResMsg("失败");
        return inOutResponse.toString();
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    @Override
    public String toString() {
        return beanFormatToJson(this);
    }
}
