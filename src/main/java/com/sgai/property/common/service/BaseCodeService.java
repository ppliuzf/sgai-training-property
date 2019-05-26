package com.sgai.property.common.service;

/**
 * 编码规则基础数据.
 *
 * @author ppliu
 * created in 2018/12/21 13:52
 */
public interface BaseCodeService {
    String getNumber(String comCode, String sequCode);

    Long getseq(String codeType);
}
