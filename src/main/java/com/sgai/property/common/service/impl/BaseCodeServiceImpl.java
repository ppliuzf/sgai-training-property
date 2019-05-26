package com.sgai.property.common.service.impl;

import com.sgai.property.common.entity.Code;
import com.sgai.property.common.mapper.CodeMapper;
import com.sgai.property.common.service.BaseCodeService;
import com.sgai.property.ctl.service.CtlComRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * 编码规则基础数据.
 *
 * @author ppliu
 * created in 2018/12/21 13:52
 */
@Service
public class BaseCodeServiceImpl implements BaseCodeService {
    @Autowired
    private CtlComRuleService ctlComRuleService;
    @Autowired
    private CodeMapper codeMapper;

    @Override
    public String getNumber(String comCode, String sequCode) {
        return ctlComRuleService.getNext(comCode, sequCode);
    }

    @Override
    public Long getseq(String codeType) {
        Example example = new Example(Code.class);
        example.createCriteria().andEqualTo("codeType", codeType);
        Code code = codeMapper.selectOneByExample(example);
        Long result = code.getCodeNum();
        code.setCodeNum(code.getCodeNum() + 1);
        codeMapper.updateByPrimaryKeySelective(code);
        return result;
    }
}
