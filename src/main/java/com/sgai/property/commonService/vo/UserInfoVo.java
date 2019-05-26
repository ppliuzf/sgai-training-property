package com.sgai.property.commonService.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author hou
 * @date 2017-12-27 17:43
 */
public class UserInfoVo implements Serializable {


    @ApiModelProperty(value = "姓名")
    private String userName;
    @ApiModelProperty(value = "头像url")
    private String imageUrl;
    @ApiModelProperty(value = "是否参加1 参加未请假 0 未参加未请假 2请假未参加 3请假且参加")
    private Integer isInvite;

    public Integer getIsInvite() {
        return isInvite;
    }

    public UserInfoVo setIsInvite(Integer isInvite) {
        this.isInvite = isInvite;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserInfoVo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserInfoVo setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
