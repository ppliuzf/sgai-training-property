package com.sgai.property.video.vo;

import com.alibaba.fastjson.JSON;

/**
 * 描述:
 *
 * @author ppliu
 * created in 2019/5/4 20:19
 */
public class ImageDataVo {
    private String id;
    private String base64Code;


    public ImageDataVo(String id, String base64Code) {
        this.id = id;
        this.base64Code = base64Code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBase64Code() {
        return base64Code;
    }

    public void setBase64Code(String base64Code) {
        this.base64Code = base64Code;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
