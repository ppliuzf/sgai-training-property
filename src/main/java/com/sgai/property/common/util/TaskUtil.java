package com.sgai.property.common.util;

import com.sgai.property.quality.entity.QtTaskItem;
import com.sgai.property.quality.entity.QtTestTask;

public class TaskUtil {
	public static String getTaskStatusDesc(Integer status){
		String statusDesc="";
		if(QtTestTask.NO_START.equals(status)){
			statusDesc="未开始";
		}else if(QtTestTask.STARTING.equals(status)){
			statusDesc="进行中";
		}else if(QtTestTask.AUDITING.equals(status)){
			statusDesc="审核中";
		}else if(QtTestTask.FINISH.equals(status)){
			statusDesc="已完结";
		}
		return statusDesc;
	}
	public static String getTaskItemStatusDesc(Integer status){
		String statusDesc="";
		if(QtTaskItem.NO_QUALIFIED_STATUS.equals(status)){
			statusDesc="未检查";
		}else if(QtTaskItem.QUALIFIED.equals(status)){
			statusDesc="合格";
		}else if(QtTaskItem.UNQUALIFIED.equals(status)){
			statusDesc="缺陷";
		}
		return statusDesc;
	}

	/**
	 * 获取类型文字描述
	 * @param isInput
	 * @return
	 */
	public static String getItemTypeDesc(Integer isInput){
		String result="";
		if(QtTaskItem.IS_INPUT.equals(isInput)){
			result="数值";
		}else{
			result="单选";
		}
		return result;
	}
	/**
	 * 获取是否检验文字描述
	 * @param isInput
	 * @return
	 */
	public static String getItemIsSubmitDesc(Integer isSubmit){
		String result="";
		if(QtTaskItem.CHECKED.equals(isSubmit)){
			result="已检验";
		}else{
			result="未检验";
		}
		return result;
	}


}
