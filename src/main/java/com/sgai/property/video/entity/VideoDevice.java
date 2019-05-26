package com.sgai.property.video.entity;

import javax.persistence.*;

/**
 * @author ppliu
 * created in 2019/4/2 14:48
 */
@Entity
@Table(name = "video_device")
public class VideoDevice {
    /** 主键. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** ip地址. */
    private String ip;
    /** 视频名称. */
    private String videoName;
    /** 描述. */
    private String description;
    /** 位置. */
    private String position;
    /** 通道. */
    private String aisle;
    /** 图纸名称. */
    private String drawingName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getDrawingName() {
        return drawingName;
    }

    public void setDrawingName(String drawingName) {
        this.drawingName = drawingName;
    }
}
