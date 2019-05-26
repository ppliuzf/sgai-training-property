package com.sgai.property.customer.service;

import com.google.common.collect.Lists;
import com.sgai.common.mapper.BeanMapper;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.StringUtil;
import com.sgai.property.commonService.dao.IDictGeneralDao;
import com.sgai.property.commonService.entity.DictGeneral;
import com.sgai.property.customer.constants.Constants;
import com.sgai.property.customer.dao.*;
import com.sgai.property.customer.entity.*;
import com.sgai.property.customer.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomInfoServiceImpl {


    @Autowired
    private IPersonalRecordInfoDao personalRecordInfoDao;
    @Autowired
    private ICustomTypeInfoDao customTypeInfoDao;
    @Autowired
    private ICustomLevelInfoDao customLevelInfoDao;
    @Autowired
    private ICustomCardInfoDao customCardInfoDao;
    @Autowired
    private IOrgRecordInfoDao orgRecordInfoDao;
    @Autowired
    private IOrgRecordCardDao orgRecordCardDao;
    @Autowired
    private IOrgRecordLinkmanDao orgRecordLinkmanDao;
    @Autowired
    private IDictGeneralDao dictGeneralDao;
    @Autowired
    private UploadRecordInfoServiceImpl uploadRecordInfoService;
    @Autowired
    private PersonalRecordInfoServiceImpl personalRecordInfoService;
    @Autowired
    private OrgRecordInfoServiceImpl orgRecordInfoService;
    @Autowired
    private TokenServerImpl tokenServer;
    @Autowired
    private CustomLevelInfoServiceImpl customLevelInfoService;
    @Autowired
    private CustomTypeInfoServiceImpl customTypeInfoService;
    @Autowired
    private CustomCardInfoServiceImpl customCardInfoService;
    @Autowired
    private OrgRecordCardServiceImpl orgRecordCardService;
    @Autowired
    private OrgRecordLinkmanServiceImpl orgRecordLinkmanService;



    public List<CustomTypeInfoVo> findAllCustomTypeInfos(Integer typeClass) {

        CustomTypeInfo typeInfo = new CustomTypeInfo();
        typeInfo.setTypeClass(typeClass.longValue());
        typeInfo.setComCode(UserServletContext.getUserInfo().getComCode());
        List<CustomTypeInfoVo> result = new ArrayList<>();
        List<CustomTypeInfo> customTypeInfoList = customTypeInfoDao.findAllListByTypeClass(typeInfo);
        if (null != customTypeInfoList || !customTypeInfoList.isEmpty()) {
            for (CustomTypeInfo customTypeInfo : customTypeInfoList) {
                CustomTypeInfoVo customTypeInfoVo = new CustomTypeInfoVo();
                BeanUtils.copyProperties(customTypeInfo, customTypeInfoVo);
                customTypeInfoVo.setCtId(customTypeInfo.getId());
                result.add(customTypeInfoVo);
            }
        }
        return result;
    }

    public List<CustomLevelInfoVo> findAllCustomLevelInfos() {

        List<CustomLevelInfoVo> result = new ArrayList<>();
        CustomLevelInfo info = new CustomLevelInfo();
        info.setClIsDelete(Constants.FALSE);
        info.preGet();
        List<CustomLevelInfo> list = customLevelInfoDao.findListTo(info);

        if (null != list && !list.isEmpty()) {
            for (CustomLevelInfo customLevelInfo : list) {
                CustomLevelInfoVo customLevelInfoVo = new CustomLevelInfoVo();
                BeanUtils.copyProperties(customLevelInfo, customLevelInfoVo);
                customLevelInfoVo.setClId(customLevelInfo.getId());
                result.add(customLevelInfoVo);
            }
        }
        return result;
    }


    @Transactional(rollbackFor = BusinessException.class)
    public boolean saveCustomLevelInfo(CustomLevelInfoVo customLevelInfoVo) {

        
        CustomLevelInfo customLevelInfo = new CustomLevelInfo();
        BeanUtils.copyProperties(customLevelInfoVo, customLevelInfo);
        customLevelInfoVo.setClId(null);
        long currt = System.currentTimeMillis();
        customLevelInfo.setComId(UserServletContext.getUserInfo().getComCode());
        customLevelInfo.setCreateTime(currt);
        customLevelInfo.setUpdateTime(currt);
        customLevelInfo.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
        customLevelInfo.setLevelType(Constants.TRUE);
        customLevelInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
        customLevelInfo.setClIsDelete(Constants.IsDelete.No);
        customLevelInfo.setLevelType(Constants.IsDelete.Yes);
        customLevelInfo.setComCode(UserServletContext.getUserInfo().getComCode());
        customLevelInfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
        customLevelInfoService.save(customLevelInfo);
        return true;
    }


    @Transactional(rollbackFor = BusinessException.class)
    public boolean updateCustomLevelInfoById(CustomLevelInfoVo customLevelInfoVo) {

        long currt = System.currentTimeMillis();
        CustomLevelInfo customLevelInfo = new CustomLevelInfo();
        BeanUtils.copyProperties(customLevelInfoVo, customLevelInfo);
        //查询当前级别是什么类型 默认还是自定义
        CustomLevelInfo customLevel = customLevelInfoDao.getById(customLevelInfoVo.getClId());
        if(null != customLevel && null != customLevel.getLevelType()){

            customLevelInfo.setLevelType(customLevel.getLevelType());
        }

        customLevelInfo.setId(customLevelInfoVo.getClId());
        customLevelInfo.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
        customLevelInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
        customLevelInfo.setUpdateTime(currt);
        customLevelInfo.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
        customLevelInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
        customLevelInfo.setId(customLevelInfoVo.getClId());
        int rs = customLevelInfoDao.updateById(customLevelInfo);
        return rs > 0;
    }


    @Transactional(rollbackFor = BusinessException.class)
    public boolean saveCustomTypeInfo(CustomTypeInfoVo customTypeInfoVo) {

        
        CustomTypeInfo customTypeInfo = new CustomTypeInfo();
        BeanUtils.copyProperties(customTypeInfoVo, customTypeInfo);
        long currt = System.currentTimeMillis();
        customTypeInfo.setComId(UserServletContext.getUserInfo().getComCode());
        customTypeInfo.setCreateTime(currt);
        customTypeInfo.setUpdateTime(currt);
        customTypeInfo.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
        customTypeInfo.setCtIsDelete(Constants.IsDelete.No);
        customTypeInfo.setTypeClass(1L);
        customTypeInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
        customTypeInfo.setComCode(UserServletContext.getUserInfo().getComCode());
        customTypeInfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
        customTypeInfoService.save(customTypeInfo);
        return true;
    }


    @Transactional(rollbackFor = BusinessException.class)
    public boolean updateCustomTypeInfoById(CustomTypeInfoVo customTypeInfoVo, Long isdelete) {


        //验证类别与ID是否一致   不能是删除情况
        CustomTypeInfo typeInfo = customTypeInfoDao.getById(customTypeInfoVo.getCtId());
        if (!typeInfo.getTypeClass().equals(customTypeInfoVo.getTypeClass()) && !Constants.IsDelete.Yes.equals(isdelete)) {
            throw new BusinessException(ReturnType.TokenError, "更新数据类型不一致！");
        }

        CustomTypeInfo customTypeInfo = new CustomTypeInfo();
        customTypeInfo.setId(customTypeInfoVo.getCtId());
        if (null != isdelete && Constants.IsDelete.Yes.equals(isdelete)) {
            customTypeInfo.setCtIsDelete(Constants.IsDelete.Yes);
        } else {
            customTypeInfo.setCtIsDelete(Constants.IsDelete.No);
        }

        BeanUtils.copyProperties(customTypeInfoVo, customTypeInfo);
        long currt = System.currentTimeMillis();
        customTypeInfo.setUpdateTime(currt);
        customTypeInfo.setUpdateUserId(UserServletContext.getUserInfo().getUserNo()+"");
        customTypeInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
        customTypeInfo.setId(customTypeInfoVo.getCtId());
        int rs = customTypeInfoDao.updateById(customTypeInfo);
        return rs > 0;
    }


    public CustomLevelInfoVo getCustomLevelInfoById(String clId) {

        CustomLevelInfoVo customLevelInfoVo = new CustomLevelInfoVo();
        BeanUtils.copyProperties(customLevelInfoDao.getById(clId), customLevelInfoVo);
        return customLevelInfoVo;
    }


    public CustomTypeInfoVo getCustomTypeInfoById(String ctId) {

        CustomTypeInfoVo vo = new CustomTypeInfoVo();
        CustomTypeInfo customTypeInfo = customTypeInfoDao.getById(ctId);
        if (null != customTypeInfo) {
            BeanUtils.copyProperties(customTypeInfo, vo);
            vo.setCtId(customTypeInfo.getId());
        }
        return vo;
    }


    public Page<PersonalRecordInfoVo> findAllCustomerInfosPageList(PersonalRecordInfoRequest personalRecordInfoRequest, int pageNum, int pageSize) {
        PersonalRecordInfo personalRecordInfo = new PersonalRecordInfo();
        if (personalRecordInfoRequest != null) {
            personalRecordInfo = BeanMapper.map(personalRecordInfoRequest, PersonalRecordInfo.class);
            personalRecordInfo.setPrName(StringUtil.fuzzySearchStr(personalRecordInfoRequest.getPrName()));
        }
        personalRecordInfo.setComId(UserServletContext.getUserInfo().getComCode());
        if(personalRecordInfo.getClId() == null || personalRecordInfo.getClId().equals("") || Constants.DefaultValue.All.equals(personalRecordInfo.getClId())){
            personalRecordInfo.setClId(null);
        }
        if(personalRecordInfo.getCtId() == null || personalRecordInfo.getCtId().equals("") || Constants.DefaultValue.All.equals(personalRecordInfo.getCtId())){
            personalRecordInfo.setCtId(null);
        }
        if(personalRecordInfo.getDeptId() == null || personalRecordInfo.getDeptId().equals("") || Constants.DefaultValue.All.equals(personalRecordInfo.getDeptId())){
            personalRecordInfo.setDeptId(null);
        }
        Page<PersonalRecordInfo> personalRecordInfoPage = new Page<>(pageNum,pageSize);

        //更新时间排序
        personalRecordInfoPage.setOrderBy(Constants.SqlString.UpdateTimeDesc);
        personalRecordInfo.setPage(personalRecordInfoPage);
        personalRecordInfo.setPrIsDelete(Constants.IsDelete.No);
        personalRecordInfo.setComCode(UserServletContext.getUserInfo().getComCode());
        personalRecordInfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
        //获取列表
        List<PersonalRecordInfo> list = personalRecordInfoDao.findList(personalRecordInfo);
        List<PersonalRecordInfoVo> personalRecordInfoVos = Lists.newArrayList();
        if(null != list && !list.isEmpty()){
            for(PersonalRecordInfo personalRecord : list){
                PersonalRecordInfoVo vo = new PersonalRecordInfoVo();
                BeanUtils.copyProperties(personalRecord,vo);
                vo.setPrId(personalRecord.getId());
                personalRecordInfoVos.add(vo);
            }
        }
        Page<PersonalRecordInfoVo> page = new Page<>();
        BeanUtils.copyProperties(personalRecordInfoPage,page);

        personalRecordInfoVos = BeanMapper.mapList(personalRecordInfoVos, PersonalRecordInfoVo.class);
        for (PersonalRecordInfoVo personalRecordInfoVo : personalRecordInfoVos) {
            CustomTypeInfo customTypeInfo = customTypeInfoDao.getById(personalRecordInfoVo.getCtId());
            CustomLevelInfo customLevelInfo = customLevelInfoDao.getById(personalRecordInfoVo.getClId());
            if (customTypeInfo != null) {
                personalRecordInfoVo.setCtName(customTypeInfo.getTypeName());
            }
            if (customLevelInfo != null) {
                personalRecordInfoVo.setClName(customLevelInfo.getLevelName());
            }

            //处理手机号为0的情况
            if (personalRecordInfoVo.getPrPhoneFirst() != null && personalRecordInfoVo.getPrPhoneFirst().equals(0L)) {
                personalRecordInfoVo.setPrPhoneFirst(null);
            }
        }
        page.setList(personalRecordInfoVos);
        return page;
    }


    public PersonalRecordDetailInfoVo getPersonalRecordInfoById(String prId) {

        PersonalRecordDetailInfoVo personalRecordDetailInfoVo = new PersonalRecordDetailInfoVo();
        PersonalRecordInfo personalRecordInfo = personalRecordInfoDao.getById(prId);
        BeanMapper.copy(personalRecordInfo, personalRecordDetailInfoVo);
        personalRecordDetailInfoVo.setPrId(personalRecordInfo.getId());
        CustomTypeInfo customTypeInfo = customTypeInfoDao.getById(personalRecordDetailInfoVo.getCtId());
        CustomLevelInfo customLevelInfo = customLevelInfoDao.getById(personalRecordDetailInfoVo.getClId());
        if (customTypeInfo != null) {
            personalRecordDetailInfoVo.setCtName(customTypeInfo.getTypeName());
        }
        if (customLevelInfo != null) {
            personalRecordDetailInfoVo.setClName(customLevelInfo.getLevelName());
        }

        //处理手机号为0的情况
        if (personalRecordDetailInfoVo.getPrPhoneFirst() != null && personalRecordDetailInfoVo.getPrPhoneFirst().equals(0L)) {
            personalRecordDetailInfoVo.setPrPhoneFirst(null);
        }
        if (personalRecordDetailInfoVo.getPrPhoneSecond() != null && personalRecordDetailInfoVo.getPrPhoneSecond().equals(0L)) {
            personalRecordDetailInfoVo.setPrPhoneSecond(null);
        }
        //证件信息
        List<CustomCardInfoVo> customCardInfoVos = Lists.newArrayList();
        List<CustomCardInfo> customCardInfos = customCardInfoDao.getByPrId(prId + "");
        if(null != customCardInfos && !customCardInfos.isEmpty()){
            for(CustomCardInfo customCard : customCardInfos){
                CustomCardInfoVo vo = new CustomCardInfoVo();
                BeanUtils.copyProperties(customCard,vo);
                vo.setCcId(customCard.getId());
                customCardInfoVos.add(vo);
            }
        }
        personalRecordDetailInfoVo.setCustomCardInfoVos(customCardInfoVos);
        return personalRecordDetailInfoVo;
    }


    @Transactional(rollbackFor = BusinessException.class)
    public boolean updatePersonalRecordInfoById(PersonalRecordInfoDto personalRecordInfoDto) {

        long currt = System.currentTimeMillis();
        PersonalRecordInfo personalRecordInfo = new PersonalRecordInfo();
        personalRecordInfo = BeanMapper.map(personalRecordInfoDto, PersonalRecordInfo.class);
        personalRecordInfo.setUpdateTime(currt);
        personalRecordInfo.setUpdateUserId(UserServletContext.getUserInfo().getUserNo()+"");
        personalRecordInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
        personalRecordInfo.setId(personalRecordInfoDto.getPrId());
        int rs = personalRecordInfoDao.updateById(personalRecordInfo);
        if (rs > 0) {
            //删除之前添加的证件信息
            CustomCardInfo customCard = new CustomCardInfo();
            customCard.setCcIsDelete(Constants.IsDelete.No);
            customCard.setPrId(personalRecordInfo.getId());
            customCard.setComCode(UserServletContext.getUserInfo().getComCode());
            customCard.setModuCode(UserServletContext.getUserInfo().getModuCode());
            List<CustomCardInfo> cardList = customCardInfoDao.findList(customCard);//全部查询出来
            if (null != cardList && !cardList.isEmpty()) {
                for(CustomCardInfo cardInfo : cardList){
                    customCardInfoDao.deleteById(cardInfo.getId());
                }
            }

            if (null != personalRecordInfoDto.getCustomCardInfoDtos() && !personalRecordInfoDto.getCustomCardInfoDtos().isEmpty()) {
                //设置证件信息
                List<CustomCardInfoDto> customCardInfoDtos = personalRecordInfoDto.getCustomCardInfoDtos();

                for (CustomCardInfoDto customCardInfoDto : customCardInfoDtos) {
                    CustomCardInfo customCardInfo = new CustomCardInfo();
                    BeanUtils.copyProperties(customCardInfoDto, customCardInfo);
                    customCardInfo.setCcIsDelete(0L);
                    customCardInfo.setPrId(personalRecordInfo.getId());
                    customCardInfo.setCreateTime(currt);
                    customCardInfo.setUpdateTime(currt);
                    customCardInfo.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
                    customCardInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
                    customCardInfo.setComCode(UserServletContext.getUserInfo().getComCode());
                    customCardInfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    if (Constants.TRUE.equals(personalRecordInfo.getPrIsDelete())) {
                        customCardInfo.setCcIsDelete(Long.valueOf(Constants.TRUE));
                    }
                    customCardInfoService.save(customCardInfo);
                }
            }

        }
        return rs > 0;
    }


    @Transactional(rollbackFor = BusinessException.class)
    public boolean savePersonalRecordInfo(PersonalRecordInfoDto personalRecordInfoDto) {


        long currt = System.currentTimeMillis();
        PersonalRecordInfo personalRecordInfo = new PersonalRecordInfo();
        personalRecordInfo = BeanMapper.map(personalRecordInfoDto, PersonalRecordInfo.class);
        personalRecordInfo.setComId(UserServletContext.getUserInfo().getComCode());
        personalRecordInfo.setCreateTime(currt);
        personalRecordInfo.setUpdateTime(currt);
        personalRecordInfo.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
        personalRecordInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
        personalRecordInfo.setPrIsDelete(0L);
        personalRecordInfo.setComCode(UserServletContext.getUserInfo().getComCode());
        personalRecordInfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
        personalRecordInfoService.save(personalRecordInfo);
//		if(rs > 0) {
        if(null != personalRecordInfoDto.getCustomCardInfoDtos() && !personalRecordInfoDto.getCustomCardInfoDtos().isEmpty()){
            //设置证件信息
            List<CustomCardInfoDto> customCardInfoDtos = personalRecordInfoDto.getCustomCardInfoDtos();
            List<CustomCardInfo> customCardInfos = Lists.newArrayList();
            customCardInfos = BeanMapper.mapList(customCardInfoDtos, CustomCardInfo.class);
            for (CustomCardInfo customCardInfo : customCardInfos) {
                customCardInfo.setPrId(personalRecordInfo.getId());
                customCardInfo.setCreateTime(currt);
                customCardInfo.setUpdateTime(currt);
                customCardInfo.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
                customCardInfo.setCcIsDelete(0L);
                customCardInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
                customCardInfo.setComCode(UserServletContext.getUserInfo().getComCode());
                customCardInfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
                customCardInfoService.save(customCardInfo);
            }
//
        }

        return true;
    }


    public Page<UploadRecordInfo> findUploadRecordInfoPageList(UploadRecordInfo uploadRecordInfo, int pageNum, int pageSize) {

        Page<UploadRecordInfo> pageInfo = uploadRecordInfoService.findListPage(uploadRecordInfo, pageNum, pageSize);
        return pageInfo;
    }


    public OrgRecordInfoDetailVo getOrgRecordInfoById(String orId) {

        OrgRecordInfoDetailVo orgRecordInfoDetailVo = new OrgRecordInfoDetailVo();
        OrgRecordInfo orgRecordInfo = orgRecordInfoDao.getById(orId);
        orgRecordInfoDetailVo = BeanMapper.map(orgRecordInfo, OrgRecordInfoDetailVo.class);

        CustomTypeInfo customTypeInfo = customTypeInfoDao.getById(orgRecordInfoDetailVo.getCtId());
        CustomLevelInfo customLevelInfo = customLevelInfoDao.getById(orgRecordInfoDetailVo.getClId());
        if (customTypeInfo != null) {
            orgRecordInfoDetailVo.setCtName(customTypeInfo.getTypeName());
        }
        if (customLevelInfo != null) {
            orgRecordInfoDetailVo.setClName(customLevelInfo.getLevelName());
        }

        //证件信息
        List<OrgRecordCardVo> orgRecordCardVos = Lists.newArrayList();
        List<OrgRecordCard> orgRecordCards = orgRecordCardDao.findAllOrgRecordCardByOrId(orId);
        orgRecordCardVos = BeanMapper.mapList(orgRecordCards, OrgRecordCardVo.class);
        orgRecordInfoDetailVo.setOrgRecordCardVos(orgRecordCardVos);
        //联系人信息
        List<OrgRecordLinkmanVo> orgRecordLinkmanVos = Lists.newArrayList();
        List<OrgRecordLinkman> orgRecordLinkmans = orgRecordLinkmanDao.findAllOrgRecordLinkmanByOrId(orId);
        orgRecordLinkmanVos = BeanMapper.mapList(orgRecordLinkmans, OrgRecordLinkmanVo.class);
        orgRecordInfoDetailVo.setOrgRecordLinkmanVos(orgRecordLinkmanVos);

        return orgRecordInfoDetailVo;
    }


    public boolean updateOrgRecordInfoById(OrgRecordInfoDto orgRecordInfoDto) {

        long currt = System.currentTimeMillis();
        OrgRecordInfo orgRecordInfo = new OrgRecordInfo();
        orgRecordInfo = BeanMapper.map(orgRecordInfoDto, OrgRecordInfo.class);
        orgRecordInfo.setUpdateTime(currt);
        orgRecordInfo.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
        orgRecordInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
        orgRecordInfo.setId(orgRecordInfoDto.getOrId());
        int rs = orgRecordInfoDao.updateById(orgRecordInfo);
        if (rs > 0) {
            //删除之前的信息
            if (!"".equals(orgRecordInfo.getId())) {
                orgRecordCardDao.deleteByOrId(orgRecordInfo.getId());
                orgRecordLinkmanDao.deleteByOrId(orgRecordInfo.getId());
            }

            //设置证件信息
            List<OrgRecordCardDto> orgRecordCardDtos = orgRecordInfoDto.getOrgRecordCardDtos();
            List<OrgRecordCard> orgRecordCards = Lists.newArrayList();
            orgRecordCards = BeanMapper.mapList(orgRecordCardDtos, OrgRecordCard.class);
            for (OrgRecordCard orgRecordCard : orgRecordCards) {
                orgRecordCard.setOrId(orgRecordInfo.getId());
                orgRecordCard.setCreateTime(currt);
                orgRecordCard.setUpdateTime(currt);
                orgRecordCard.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
                orgRecordCard.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
                orgRecordCard.setOrcIsDelete(0L);
                orgRecordCard.setComCode(UserServletContext.getUserInfo().getComCode());
                orgRecordCard.setModuCode(UserServletContext.getUserInfo().getModuCode());
                if (Constants.TRUE.equals(orgRecordInfo.getOrIsDelete())) {
                    orgRecordCard.setOrcIsDelete(Constants.TRUE);
                }
                orgRecordCardService.save(orgRecordCard);
            }

            //设置联系人信息
            List<OrgRecordLinkmanDto> orgRecordLinkmanDtos = orgRecordInfoDto.getOrgRecordLinkmanDtos();
            List<OrgRecordLinkman> orgRecordLinkmans = Lists.newArrayList();
            orgRecordLinkmans = BeanMapper.mapList(orgRecordLinkmanDtos, OrgRecordLinkman.class);
            for (OrgRecordLinkman orgRecordLinkman : orgRecordLinkmans) {
                orgRecordLinkman.setOrId(orgRecordInfo.getId());
                orgRecordLinkman.setCreateTime(currt);
                orgRecordLinkman.setUpdateTime(currt);
                orgRecordLinkman.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
                orgRecordLinkman.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
                orgRecordLinkman.setOrlIsDelete(0L);
                orgRecordLinkman.setComCode(UserServletContext.getUserInfo().getComCode());
                orgRecordLinkman.setModuCode(UserServletContext.getUserInfo().getModuCode());
                if (Constants.TRUE.equals(orgRecordInfo.getOrIsDelete())) {
                    orgRecordLinkman.setOrlIsDelete(Constants.TRUE);
                }
                orgRecordLinkmanService.save(orgRecordLinkman);
            }

        }
        return true;
    }


    public boolean saveOrgRecordInfo(OrgRecordInfoDto orgRecordInfoDto) {

        long currt = System.currentTimeMillis();
        OrgRecordInfo orgRecordInfo = new OrgRecordInfo();
        orgRecordInfo = BeanMapper.map(orgRecordInfoDto, OrgRecordInfo.class);
        orgRecordInfo.setComId(UserServletContext.getUserInfo().getCompanyId());
        orgRecordInfo.setCreateTime(currt);
        orgRecordInfo.setUpdateTime(currt);
        orgRecordInfo.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
        orgRecordInfo.setOrIsDelete(0L);
        orgRecordInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
        orgRecordInfoService.save(orgRecordInfo);
//		if(rs > 0) {
        //设置证件信息
        List<OrgRecordCardDto> orgRecordCardDtos = orgRecordInfoDto.getOrgRecordCardDtos();
        List<OrgRecordCard> orgRecordCards = Lists.newArrayList();
        orgRecordCards = BeanMapper.mapList(orgRecordCardDtos, OrgRecordCard.class);
        for (OrgRecordCard orgRecordCard : orgRecordCards) {
            orgRecordCard.setOrId(orgRecordInfo.getId());
            orgRecordCard.setUpdateTime(currt);
            orgRecordCard.setCreateTime(currt);

            orgRecordCard.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
            orgRecordCard.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
            orgRecordCard.setOrcIsDelete(0L);
            orgRecordCardService.save(orgRecordCard);
        }

        //设置联系人信息
        List<OrgRecordLinkmanDto> orgRecordLinkmanDtos = orgRecordInfoDto.getOrgRecordLinkmanDtos();
        List<OrgRecordLinkman> orgRecordLinkmans = Lists.newArrayList();
        orgRecordLinkmans = BeanMapper.mapList(orgRecordLinkmanDtos, OrgRecordLinkman.class);
        for (OrgRecordLinkman orgRecordLinkman : orgRecordLinkmans) {
            orgRecordLinkman.setOrId(orgRecordInfo.getId());
            orgRecordLinkman.setCreateTime(currt);
            orgRecordLinkman.setUpdateTime(currt);
            orgRecordLinkman.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
            orgRecordLinkman.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
            orgRecordLinkman.setOrlIsDelete(0L);
            orgRecordLinkmanService.save(orgRecordLinkman);
        }
//		}
        return true;
    }


    public Page<OrgRecordInfoVo> findAllOrgRecordInfoPageList(OrgRecordInfoRequest orgRecordInfoRequest,
                                                              int pageNum, int pageSize) {

        OrgRecordInfo orgRecordInfo = new OrgRecordInfo();
        if (orgRecordInfoRequest != null) {
            orgRecordInfo = BeanMapper.map(orgRecordInfoRequest, OrgRecordInfo.class);
        }
        orgRecordInfo.setComId(UserServletContext.getUserInfo().getCompanyId());
        orgRecordInfo.setComCode(UserServletContext.getUserInfo().getComCode());
        orgRecordInfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<OrgRecordInfo> orgRecordInfos = orgRecordInfoService.findListPage(orgRecordInfo, pageNum, pageSize);
        List<OrgRecordInfoVo> orgRecordInfoVos = Lists.newArrayList();
        orgRecordInfoVos = BeanMapper.mapList(orgRecordInfos.getList(), OrgRecordInfoVo.class);
        for (OrgRecordInfoVo orgRecordInfoVo : orgRecordInfoVos) {
            CustomTypeInfo customTypeInfo = customTypeInfoDao.getById(orgRecordInfoVo.getCtId());
            CustomLevelInfo customLevelInfo = customLevelInfoDao.getById(orgRecordInfoVo.getClId());
            if (customTypeInfo != null) {
                orgRecordInfoVo.setCtName(customTypeInfo.getTypeName());
            }
            if (customLevelInfo != null) {
                orgRecordInfoVo.setClName(customLevelInfo.getLevelName());
            }

            //
            List<OrgRecordLinkman> recordLinkmans = orgRecordLinkmanDao.findAllOrgRecordLinkmanByOrId(orgRecordInfoVo.getOrId());
            if (recordLinkmans != null && recordLinkmans.size() > 0) {
                OrgRecordLinkman orgRecordLinkman = recordLinkmans.get(0);
                orgRecordInfoVo.setOrlLinkman(orgRecordLinkman.getOrlLinkman());
                orgRecordInfoVo.setOrlPhone(orgRecordLinkman.getOrlPhone());
            }

        }
        Page<OrgRecordInfoVo> info = new Page<>(pageNum, pageSize, 0, orgRecordInfoVos);
        return info;
    }


    public List<CustomCardInfoVo> findCustomCardInfos(Integer type) {
        List<DictGeneral> dictGenerals = null;
		try {
			dictGenerals = dictGeneralDao.getDictGeneralsByType(Constants.DG_CODE_BIZ_CR_CERTIFICATE, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
        List<CustomCardInfoVo> customCardInfos = Lists.newArrayList();
        for (DictGeneral general : dictGenerals) {
            CustomCardInfoVo customCardInfo = new CustomCardInfoVo();
            customCardInfo.setCcnId(general.getId());
            customCardInfo.setCcCertificateName(general.getDgValue());
            customCardInfos.add(customCardInfo);
        }
        return customCardInfos;

    }



    @Transactional
    public Boolean updateCardInfo(CustomCardInfoDto customCardInfoDto){
        Boolean result ;

        CustomCardInfo customCardInfo = new CustomCardInfo();
        BeanUtils.copyProperties(customCardInfoDto,customCardInfo);
        customCardInfo.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
        customCardInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
        customCardInfo.setUpdateTime(System.currentTimeMillis());
        customCardInfo.setId(customCardInfoDto.getID());
        if(null != customCardInfoDto.getCcIsDelete() && Constants.IsDelete.Yes.equals(customCardInfoDto.getCcIsDelete())){
            customCardInfo.setCcIsDelete(Constants.IsDelete.Yes);
        }else {
            customCardInfo.setCcIsDelete(Constants.IsDelete.No);
        }
        result = customCardInfoDao.updateById(customCardInfo) > 0;
        return result;
    }



    @Transactional
    public Boolean saveCardInfo(CustomCardInfoDto customCardInfoDto){
        CustomCardInfo customCardInfo = new CustomCardInfo();

        BeanUtils.copyProperties(customCardInfoDto,customCardInfo);
        customCardInfo.setUpdateUserId(UserServletContext.getUserInfo().getUserNo());
        customCardInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
        customCardInfo.setUpdateTime(System.currentTimeMillis());
        customCardInfo.setCcIsDelete(Constants.IsDelete.No);
        customCardInfo.setId(null);
        customCardInfo.setComCode(UserServletContext.getUserInfo().getComCode());
        customCardInfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
        customCardInfoService.save(customCardInfo);
        return true;
    }
}