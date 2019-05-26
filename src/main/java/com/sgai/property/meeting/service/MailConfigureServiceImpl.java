package com.sgai.property.meeting.service;

import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.SendToonMessageUtil;
import com.sgai.property.meeting.constants.Constants;
import com.sgai.property.meeting.dao.IMailConfigureDao;
import com.sgai.property.meeting.entity.MailConfigure;
import com.sgai.property.meeting.vo.MailConfigurationDto;
import com.sgai.property.meeting.vo.MailConfigureDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailConfigureServiceImpl extends MoreDataSourceCrudServiceImpl<IMailConfigureDao,MailConfigure> {
    @Autowired
    private IMailConfigureDao mailConfigureDao;


    /**
     * 打开或者关闭邮件
     * @param mailConfigureDto
     * @return
     */
    public MailConfigureDto openOrCloseEmail(MailConfigureDto mailConfigureDto) {

        MailConfigure mailConfigureInfo = new MailConfigure();
        mailConfigureInfo.setIsDelete(0);
        mailConfigureInfo.setComCode(UserServletContext.getUserInfo().getComCode());
        mailConfigureInfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
        mailConfigureInfo = mailConfigureDao.get(mailConfigureInfo);

        MailConfigure mailConfigure = new MailConfigure();
        mailConfigure.setIsSend(mailConfigureDto.getIsSend());
        mailConfigure.setUpdateTime(System.currentTimeMillis());
        mailConfigure.setId(mailConfigureDto.getMcId());
        int result = mailConfigureDao.updateById(mailConfigure);

        BeanUtils.copyProperties(mailConfigureInfo,mailConfigureDto);
        if(result > 0){
            BeanUtils.copyProperties(mailConfigureInfo,mailConfigureDto);
            mailConfigure.setIsSend(mailConfigureDto.getIsSend());
            return mailConfigureDto;
        }else {
            return mailConfigureDto;
        }
    }


    /**
     * 获取邮件配置
     * @return
     */
    public MailConfigureDto getEmailConfigInfo() {

        MailConfigure mailConfigure = new MailConfigure();
        mailConfigure.setIsDelete(0);
        mailConfigure = this.get(mailConfigure);
        if(null != mailConfigure && null != mailConfigure.getId()){
            MailConfigureDto mailConfigureDto = new MailConfigureDto();
            BeanUtils.copyProperties(mailConfigure,mailConfigureDto);
            mailConfigureDto.setMcId(mailConfigure.getId());
            return mailConfigureDto;
        }else{
            mailConfigure = new MailConfigure();
            mailConfigure.setIsDelete(0);
            mailConfigure.setCreateTime(System.currentTimeMillis());
            mailConfigure.setUpdateTime(System.currentTimeMillis());
            mailConfigure.setIsSend(0);
            mailConfigure.setComId(UserServletContext.getUserInfo().getCompanyId());
            mailConfigure.setMcEmailName("会议应用");
            mailConfigure.setComCode(UserServletContext.getUserInfo().getComCode());
            mailConfigure.setModuCode(UserServletContext.getUserInfo().getModuCode());
            this.save(mailConfigure);
            MailConfigureDto mailConfigureDto = new MailConfigureDto();
            BeanUtils.copyProperties(mailConfigure,mailConfigureDto);
            mailConfigureDto.setMcId(mailConfigure.getId());
            return mailConfigureDto;
        }
    }


    /**
     * 修改邮件配置信息
     * @param mailConfigureDto
     * @return
     */
    public MailConfigureDto updateMailConfigureById(MailConfigurationDto mailConfigureDto) {
        if (mailConfigureDto.getMcAccount().length() > Constants.email.accountLenght) {
            throw new BusinessException(ReturnType.ParamIllegal, "邮箱账号长度不能超过100字符");
        }
        if (mailConfigureDto.getMcPassword().length() > Constants.email.passWordLenght) {
            throw new BusinessException(ReturnType.ParamIllegal, "邮箱密码长度不能超过20字符");
        }
        if (mailConfigureDto.getMcEmailName().length() > Constants.email.mcNameLenght) {
            throw new BusinessException(ReturnType.ParamIllegal, "发件名称不能超过10字符");
        }
        if (!SendToonMessageUtil.checkEmaile(mailConfigureDto.getMcAccount())) {
            throw new BusinessException(ReturnType.ParamIllegal, "邮箱账号输入错误");
        }
        if (!SendToonMessageUtil.isIP(mailConfigureDto.getMcIp())) {
            throw new BusinessException(ReturnType.ParamIllegal, "SMTP服务器输入错误");
        }
        Integer port1;
        try {
            port1 = Integer.valueOf(mailConfigureDto.getMcPort());
        } catch (Exception e) {
            throw new BusinessException(ReturnType.ParamIllegal, "端口输入错误");
        }
        if (!SendToonMessageUtil.isPort(port1)) {
            throw new BusinessException(ReturnType.ParamIllegal, "端口输入错误");
        }

        MailConfigure mailConfigure = new MailConfigure();
        BeanUtils.copyProperties(mailConfigureDto, mailConfigure);
        mailConfigure.setMcPort(port1);
        mailConfigure.setUpdateTime(System.currentTimeMillis());
        mailConfigure.setIsDelete(Constants.IsDelete.NO);
        mailConfigure.setComId(UserServletContext.getUserInfo().getCompanyId());
        mailConfigure.setId(mailConfigureDto.getMcId());
        mailConfigure.setComCode(UserServletContext.getUserInfo().getComCode());
        mailConfigure.setModuCode(UserServletContext.getUserInfo().getModuCode());
        if (null == mailConfigureDto.getMcId()) {
            mailConfigure.setCreateTime(System.currentTimeMillis());
            this.save(mailConfigure);
        } else {
            mailConfigureDao.updateById(mailConfigure);
        }
        MailConfigureDto mailConfigureDto1 = new MailConfigureDto();
        BeanUtils.copyProperties(mailConfigure, mailConfigureDto1);
        mailConfigureDto1.setMcId(mailConfigure.getId());
        return mailConfigureDto1;
    }
}
