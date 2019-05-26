package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;

public class ExPlanItemlVo {

    //任务项

    @ApiModelProperty(value = "任务项id")
    private String id;
    @ApiModelProperty(value = "任务项名称")
    private String name;
    @ApiModelProperty(value = "执行人")
    private String executor;
//    @ApiModelProperty(value = "检查项状态")
//    private Long executoStatus;
    @ApiModelProperty(value = "执行状态 已完成，未完成")
    private String executoStatus;
    public String getExecutoStatus() {
		return executoStatus;
	}

	public void setExecutoStatus(String executoStatus) {
		this.executoStatus = executoStatus;
	}

	private String tiId;

    @ApiModelProperty(value = " 答题类型(0:数字型,1:选择型)")
    private Integer piIsInput;
    @ApiModelProperty(value = "执行时间")
    private String executoTime;

    @ApiModelProperty(value = "执行结果 0：未检查， 1：合格， 2：缺陷")
    private Long executeResult;
    public Long getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(Long executeResult) {
		this.executeResult = executeResult;
	}

	@ApiModelProperty(value = "备注")
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }


    public Integer getPiIsInput() {
        return piIsInput;
    }

    public void setPiIsInput(Integer piIsInput) {
        this.piIsInput = piIsInput;
    }

    public String getExecutoTime() {
        return executoTime;
    }

    public void setExecutoTime(String executoTime) {
        this.executoTime = executoTime;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTiId() {
        return tiId;
    }

    public void setTiId(String tiId) {
        this.tiId = tiId;
    }
}
