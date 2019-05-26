package com.sgai.property.contract.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.contract.dao.ICHtTypeDao;
import com.sgai.property.contract.dao.IHtTempletDao;
import com.sgai.property.contract.dao.IHtTempletDaoMapper;
import com.sgai.property.contract.entity.HtTemplet;
import com.sgai.property.contract.entity.HtType;
import com.sgai.property.contract.vo.HtTempletVO;
import com.sgai.property.contract.vo.TempletParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class HtTempletServiceImpl extends MoreDataSourceCrudServiceImpl<IHtTempletDao, HtTemplet> {
    @Autowired
    private IHtTempletDao htTempletDao;

    @Autowired
    private IHtTempletDaoMapper htTempletDaoMapper;

    @Autowired
    private ICHtTypeDao typeDao;

    /**
     * 上传模板
     *
     * @param templetParams
     */
    @Transactional
    public void save(LoginUser loginUser, TempletParams templetParams, String no) {
        HtTemplet htTemplet = new HtTemplet();
        //添加模板名称
        htTemplet.setName(templetParams.getName());
        //添加模板描述
        if (templetParams.getDescription() != null && !"".equals(templetParams.getDescription())) {
            htTemplet.setDescription(templetParams.getDescription());
        }
        //添加模板类型
        if (templetParams.getTypeId() != null && !"".equals(templetParams.getTypeId())) {
            htTemplet.setTypeId(templetParams.getTypeId());
        }
        //添加上传模板的路径
        htTemplet.setUrl(templetParams.getUrl());
        //添加模板编码
        htTemplet.setNo(no);
        //添加toonCode中的参数
//		htTemplet.setComId(Long.parseLong(loginUser.getComCode()));
        htTemplet.setComName(loginUser.getComName());
//		htTemplet.setFeedId(loginUser.getUserId());
        //添加上传者
        htTemplet.setUploadBy(loginUser.getUserName());
        //添加文件名称
        htTemplet.setFileName(templetParams.getFileName());
        //不删除
        htTemplet.setIsDelete(0L);
        this.save(htTemplet);
    }

    /**
     * 根据唯一标识更新合同模板
     *
     * @param id
     * @param htTemplet
     * @return
     */
    @Transactional
    public boolean updateById(String id, HtTemplet htTemplet) {
        //添加查询参数：id
        htTemplet.setId(id);
        return this.updateById(htTemplet);
    }


    /**
     * 分页查询合同模板信息
     *
     * @param htTemplet
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<HtTempletVO> findHtTempletList(HtTemplet htTemplet, int pageNum, int pageSize) {
        //初始化信息：分页信息，合同模板输出实体，合同模板输出实体集合
        Page<HtTempletVO> pageInfo = new Page<HtTempletVO>();
        List<HtTempletVO> list = new ArrayList<HtTempletVO>();

        //封装分页信息
        Page<HtTemplet> page = new Page<HtTemplet>(pageNum, pageSize);
        page.setOrderBy("CREATED_DT ASC");
        htTemplet.setPage(page);

        htTemplet.setComCode(UserServletContext.getUserInfo().getComCode());
        htTemplet.setModuCode(UserServletContext.getUserInfo().getModuCode());

        //获取合同模板信息集合
        List<HtTemplet> htTempletList = htTempletDaoMapper.findHtTempletList(htTemplet);
        //获取合同模板信息数量
        int count = htTempletDaoMapper.getListCount(htTemplet);

        for (HtTemplet templet : htTempletList) {
            //封装实体
            HtTempletVO htTempletVO = new HtTempletVO();
            //合同模板唯一标识
            htTempletVO.setId(templet.getId());
            //合同模板编号
            htTempletVO.setNo(templet.getNo());
            //合同模板名称
            htTempletVO.setName(templet.getName());
            //合同模板类型
            htTempletVO.setTypeId(templet.getTypeId());
            //合同模板类型名称
            HtType htType = typeDao.getById(templet.getTypeId());
            if (htType != null && !"".equals(htType.getTypeName())) {
                htTempletVO.setTypeName(htType.getTypeName());
            }
            //合同模板的描述信息
            htTempletVO.setDescription(templet.getDescription());
            //合同模板的上传地址
            htTempletVO.setUrl(templet.getUrl());
            //合同模板的删除状态
            htTempletVO.setIsDelete(templet.getIsDelete());
            //合同模板上传日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            htTempletVO.setCreateDate(sdf.format(templet.getUpdatedDt()));
            //合同模板的上传人
            htTempletVO.setUploadBy(templet.getUploadBy());
            //合同模板文件名称
            htTempletVO.setFileName(templet.getFileName());
            list.add(htTempletVO);
        }

        //封装分页详情.
        pageInfo.setList(list);
        pageInfo.setCount(count);
        return pageInfo;
    }

}