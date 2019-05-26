package com.sgai.property.purchase.param;

import com.sgai.common.persistence.BoEntity;
import com.sgai.property.purchase.entity.PlanTask;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/3/2.
 */
public class TaskParam extends BoEntity<PlanTask> {
    @ApiModelProperty(value = "申请人姓名")
    private String taskEmpName; //申请人姓名
    @ApiModelProperty(value = "IDList")
    private String[] receiptArry;

    public String getTaskEmpName() {
        return taskEmpName;
    }

    public void setTaskEmpName(String taskEmpName) {
        this.taskEmpName = taskEmpName;
    }

    public String[] getReceiptArry() {
        return receiptArry;
    }

    public void setReceiptArry(String[] receiptArry) {
        this.receiptArry = receiptArry;
    }
}
