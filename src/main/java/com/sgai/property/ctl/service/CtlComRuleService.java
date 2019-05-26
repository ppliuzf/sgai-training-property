package com.sgai.property.ctl.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.entity.CtlComRule;
import com.sgai.property.ctl.entity.CtlRuleInfo;
import com.sgai.property.ctl.entity.CtlRuleItem;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ctl.dao.CtlComRuleDao;

/**
 * user关联rule队列Service
 * @author chenxing
 * @version 2017-11-20
 */
@Service
@Transactional
public class CtlComRuleService extends CrudServiceExt<CtlComRuleDao, CtlComRule> {

	@Autowired
	private CtlRuleInfoService ctlRuleInfoService;

	public CtlComRule get(String id) {
		return super.get(id);
	}

	public List<CtlComRule> findList(CtlComRule ctlComRule) {
		return super.findList(ctlComRule);
	}

	public Page<CtlComRule> findPage(Page<CtlComRule> page, CtlComRule ctlComRule) {
		return super.findPage(page, ctlComRule);
	}

	@Transactional(readOnly = false)
	public void save(CtlComRule ctlComRule) {
		super.save(ctlComRule);
	}

	@Transactional(readOnly = false)
	public void delete(CtlComRule ctlComRule) {
		super.delete(ctlComRule);
	}

	@Transactional(readOnly = false)
	public void batchDeleteComRule(List<String> idList) {
		dao.batchDelete(idList);
	}

	public List<Map<String,Object>> getComList(){
		List<Map<String,Object>> result = Lists.newArrayList();
		List<Map<String,Object>> list = dao.getComList();
		for(Map<String,Object> map : list) {
			Map<String,Object> newMap = Maps.newHashMap();
			newMap.put("id", map.get("COM_CODE"));
			newMap.put("pId", "0");
			newMap.put("name", map.get("COM_NAME"));
			result.add(newMap);
		}
		return result;
	}

	public List<Map<String,Object>> getRuleList(){
		List<Map<String,Object>> result = Lists.newArrayList();
		List<Map<String,Object>> list = dao.getRuleList();
		for(Map<String,Object> map : list) {
			Map<String,Object> newMap = Maps.newHashMap();
			newMap.put("id", map.get("SEQU_CODE"));
			newMap.put("pId", "0");
			newMap.put("name", map.get("SEQU_NAME"));
			result.add(newMap);
		}
		return result;
	}

	public CtlComRule getComRule(CtlComRule param) {
		return dao.getComRule(param);
	}

	/**
	 * 获取当前登录账户下，指定单据规则的下一个编码
	 */
    public String getNext(String sequCode) {
    	LoginUser user = UserServletContext.getUserInfo();
    	return getNext(user.getComCode(), sequCode);
	}

    /**
	 * 获取指定账户下，指定单据规则的下一个编码
	 */
    @Transactional(readOnly = false)
	public synchronized String getNext(String comCode, String sequCode) {
    	String next;
		CtlComRule param1 = new CtlComRule();
		param1.setComCode(comCode);
		param1.setSequCode(sequCode);
		CtlComRule comRule = getComRule(param1);

		CtlRuleInfo param2 = new CtlRuleInfo();
		param2.setSequCode(sequCode);
		CtlRuleInfo ruleInfo = ctlRuleInfoService.getRuleInfo(param2);
		if(ruleInfo==null) {
			return "null";
		}
		if("Y".equals(ruleInfo.getIsMultipleCom())) {//多个商户共同使用
			next = getNextInMultipleModel(comRule,ruleInfo);
		}else {//单个商户独自使用
			next = getNextInSingleModel(comRule,ruleInfo);
		}

		//更新
		save(comRule);
		ctlRuleInfoService.save(ruleInfo);

		return next;
	}

    public String getNextInMultipleModel(CtlComRule com, CtlRuleInfo rule){
    	Long currentNo;//当前序号
    	//判断是不是要重置
    	String df = "yyyyMMdd";
    	String sequReset = rule.getSequReset();//重置规则:none,yyyyMMdd,yyyyMM,yyyy
    	if(!"none".equals(sequReset)) {
    		df = sequReset;
    	}
    	SimpleDateFormat formatter = new SimpleDateFormat(df);
    	String currentDate = formatter.format(new Date());
    	if("none".equals(sequReset) || currentDate.equals(rule.getCurrentReset())){//不重置
    		currentNo = Long.valueOf(rule.getCurrentNo()==null?"0":rule.getCurrentNo());
    	}else {//重置
    		currentNo = 0L;
    	}
    	//生成编号
    	StringBuffer sb = new StringBuffer();
    	String delimiter = rule.getSequDelimiter()==null?"":rule.getSequDelimiter();//分隔符
    	Long step = Long.valueOf(rule.getStepLength());
    	boolean hasNumbering = false;
    	List<CtlRuleItem> list = rule.getCtlRuleItemList();
    	for(CtlRuleItem item:list) {
    		if("const".equals(item.getRuleType())){
    			sb.append(item.getRuleValue());
    		}else if("numbering".equals(item.getRuleType())){
    			hasNumbering = true;
    			Long no = (currentNo + 1) * step;
    			sb.append(formateNumber(no,item));
    		}else if("timestamp".equals(item.getRuleType())){
    			sb.append(currentDate);
    		}
    		sb.append(delimiter);
    	}
    	sb.deleteCharAt(sb.length()-1);
    	if(hasNumbering) {
    		currentNo++;
    	}
    	rule.setCurrentNo(currentNo+"");
    	rule.setCurrentCode(sb.toString());
    	rule.setCurrentReset(currentDate);

    	return sb.toString();
    }

    public String getNextInSingleModel(CtlComRule com, CtlRuleInfo rule) {
    	Long currentNo;//当前序号
    	//判断是不是要重置
    	String df = "yyyyMMdd";
    	String sequReset = rule.getSequReset();//重置规则:none,yyyyMMdd,yyyyMM,yyyy
    	if(!"none".equals(sequReset)) {
    		df = sequReset;
    	}
    	SimpleDateFormat formatter = new SimpleDateFormat(df);
    	String currentDate = formatter.format(new Date());
    	if("none".equals(sequReset) || currentDate.equals(com.getCurrentReset())){//不重置
    		currentNo = Long.valueOf(com.getCurrentNo()==null?"0":com.getCurrentNo());
    	}else {//重置
    		currentNo = 0L;
    	}
    	//生成编号
    	StringBuffer sb = new StringBuffer();
    	String delimiter = rule.getSequDelimiter()==null?"":rule.getSequDelimiter();//分隔符
    	Long step = Long.valueOf(rule.getStepLength());
    	boolean hasNumbering = false;
    	List<CtlRuleItem> list = rule.getCtlRuleItemList();
    	for(CtlRuleItem item:list) {
    		if("const".equals(item.getRuleType())){
    			sb.append(item.getRuleValue());
    		}else if("numbering".equals(item.getRuleType())){
    			hasNumbering = true;
    			Long no = (currentNo + 1) * step;
    			sb.append(formateNumber(no,item));
    		}else if("timestamp".equals(item.getRuleType())){
    			sb.append(currentDate);
    		}
    		sb.append(delimiter);
    	}
    	sb.deleteCharAt(sb.length()-1);
    	if(hasNumbering) {
    		currentNo++;
    	}
    	com.setCurrentNo(currentNo+"");
    	com.setCurrentCode(sb.toString());
    	com.setCurrentReset(currentDate);

        return sb.toString();
    }

    public String formateNumber(Long no, CtlRuleItem item) {
    	String result = null;
    	String noStr = String.valueOf(no);
    	Integer paddingWidth = Integer.valueOf(item.getPaddingWidth());
    	String paddingChar = item.getPaddingChar();
    	String paddingSide = item.getPaddingSide();
        int fixedWidth = paddingWidth - noStr.length();
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<fixedWidth; i++) {
        	sb.append(paddingChar);
        }
    	if("left".equals(paddingSide)) {
    		result = sb.toString() +  noStr;
    	}else if("right".equals(paddingSide)) {
    		result = noStr + sb.toString();
    	}

    	return result;
    }

}
