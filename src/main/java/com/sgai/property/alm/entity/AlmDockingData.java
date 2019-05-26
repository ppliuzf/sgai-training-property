package com.sgai.property.alm.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import springfox.documentation.spring.web.json.Json;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 报警对接数据.
 *
 * @author ppliu
 * created in 2019/1/11 10:30
 */
@Entity
@Table(name = "alm_docking_data")
public class AlmDockingData {
    /** 主键. */
    @Id
    private String uuid;
    /** 报警源. */
    private String source;
    /** 报警分类. */
    private String alarmClass;
    /** 报警源状态（Normal正常，其他异常）. */
    private String sourceState;
    /** 报警时间. */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date timestamp;
    /** 报警信息. */
    private String msgText;
    /** 报警信息详细. */
    private String msgTextDetail;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAlarmClass() {
        return alarmClass;
    }

    public void setAlarmClass(String alarmClass) {
        this.alarmClass = alarmClass;
    }

    public String getSourceState() {
        return sourceState;
    }

    public void setSourceState(String sourceState) {
        this.sourceState = sourceState;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getMsgTextDetail() {
        return msgTextDetail;
    }

    public void setMsgTextDetail(String msgTextDetail) {
        this.msgTextDetail = msgTextDetail;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.WRITE_MAP_NULL_FEATURES,SerializerFeature.WriteDateUseDateFormat);
    }
}
