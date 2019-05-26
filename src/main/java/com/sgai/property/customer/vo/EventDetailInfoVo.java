  
    /**    
    * @Title: EventDetailInfo.java  
    * @Package com.sgai.property.customer.vo
    * @Description: (用一句话描述该文件做什么)
    * @author syswin  
    * @date 2018年6月12日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.customer.vo;

    import com.sgai.property.em.entity.*;
    import com.sgai.property.wf.entity.WfInstanceRecord;
    import io.swagger.annotations.ApiModelProperty;

    import java.io.Serializable;
    import java.util.List;

    /**
        * @ClassName: EventDetailInfo
        * @Description: (这里用一句话描述这个类的作用)
        * @author syswin
        * @date 2018年6月12日
        * @Company 首自信--智慧城市创新中心
        */

    public class EventDetailInfoVo implements Serializable{


            /**
            * @Fields field:field:(用一句话描述这个变量表示什么)
            */

        private static final long serialVersionUID = 263690411860109489L;
        @ApiModelProperty(value = "流程节点信息")
        private List<WfInstanceRecord> wfInstanceRecords;
    //	@ApiModelProperty(value = "投诉创建")
    //	private EmComplaintsVo emComplaintsVo;
    //	@ApiModelProperty(value = "维修创建")
    //	private EmRepairListVo emRepairListVo;
    //	@ApiModelProperty(value = "安保创建")
    //	private EmSecuRecordVo emSecuRecordVo;
        @ApiModelProperty(value = "创建信息")
        private EmEventCreateVo emEventCreateVo;
        @ApiModelProperty(value = "指派信息")
        private EmAssign emAssign;
        @ApiModelProperty(value = "核实信息")
        private EmConfirm emConfirm;
        @ApiModelProperty(value = "处理信息")
        private List<EmProcess> emProcesses;
        @ApiModelProperty(value = "回访信息")
        private EmComplaintsReturn emComplaintsReturn;
        @ApiModelProperty(value = "终止信息")
        private EmEnded emEnded;


        public EmEventCreateVo getEmEventCreateVo() {
            return emEventCreateVo;
        }

        public void setEmEventCreateVo(EmEventCreateVo emEventCreateVo) {
            this.emEventCreateVo = emEventCreateVo;
        }

        public List<WfInstanceRecord> getWfInstanceRecords() {
            return wfInstanceRecords;
        }

        public void setWfInstanceRecords(List<WfInstanceRecord> wfInstanceRecords) {
            this.wfInstanceRecords = wfInstanceRecords;
        }

        public EmAssign getEmAssign() {
            return emAssign;
        }

        public void setEmAssign(EmAssign emAssign) {
            this.emAssign = emAssign;
        }

        public EmConfirm getEmConfirm() {
            return emConfirm;
        }

        public void setEmConfirm(EmConfirm emConfirm) {
            this.emConfirm = emConfirm;
        }

        public List<EmProcess> getEmProcesses() {
            return emProcesses;
        }

        public void setEmProcesses(List<EmProcess> emProcesses) {
            this.emProcesses = emProcesses;
        }

        public EmComplaintsReturn getEmComplaintsReturn() {
            return emComplaintsReturn;
        }

        public void setEmComplaintsReturn(EmComplaintsReturn emComplaintsReturn) {
            this.emComplaintsReturn = emComplaintsReturn;
        }

        public EmEnded getEmEnded() {
            return emEnded;
        }

        public void setEmEnded(EmEnded emEnded) {
            this.emEnded = emEnded;
        }


            /* (非 Javadoc)
            *
            *
            * @return
            * @see java.lang.Object#toString()
            */

        @Override
        public String toString() {
            return "EventDetailInfoVo [wfInstanceRecords=" + wfInstanceRecords + ", emEventCreateVo=" + emEventCreateVo
                    + ", emAssign=" + emAssign + ", emConfirm=" + emConfirm + ", emProcesses=" + emProcesses
                    + ", emComplaintsReturn=" + emComplaintsReturn + ", emEnded=" + emEnded + "]";
        }


            /* (非 Javadoc)
            *
            *
            * @return
            * @see java.lang.Object#toString()
            */




    }
