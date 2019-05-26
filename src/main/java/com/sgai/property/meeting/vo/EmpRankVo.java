package com.sgai.property.meeting.vo;

import java.io.Serializable;

/**
 * 员工预定排行信息
 *
 * @author hou
 * @date 2017-12-20 18:39
 */
public class EmpRankVo implements Serializable {
    private Integer rankNo;
    private String empName;
    private Integer userCount;
    private Long scase;

    public Integer getRankNo() {
        return rankNo;
    }

    public EmpRankVo setRankNo(Integer rankNo) {
        this.rankNo = rankNo;
        return this;
    }

    public String getEmpName() {
        return empName;
    }

    public EmpRankVo setEmpName(String empName) {
        this.empName = empName;
        return this;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public EmpRankVo setUserCount(Integer userCount) {
        this.userCount = userCount;
        return this;
    }

    public Long getScase() {
        return scase;
    }

    public EmpRankVo setScase(Long scase) {
        this.scase = scase;
        return this;
    }
}
