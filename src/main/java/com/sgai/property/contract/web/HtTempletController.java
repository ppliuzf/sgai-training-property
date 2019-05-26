package com.sgai.property.contract.web;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.contract.dao.IHtTempletDaoMapper;
import com.sgai.property.contract.entity.HtTemplet;
import com.sgai.property.contract.service.HtTempletServiceImpl;
import com.sgai.property.contract.vo.HtTempletVO;
import com.sgai.property.contract.vo.ResultMessage;
import com.sgai.property.contract.vo.TempletParams;
import com.sgai.property.ctl.service.CtlComRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/htTemplet")
@Api(description = "合同模板")
public class HtTempletController extends BaseController {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HtTempletServiceImpl htTempletService;

    @Autowired
    private IHtTempletDaoMapper htTempletDaoMapper;

    @Autowired
    private CtlComRuleService ctlComRuleService;


    @Value("${mat.comCode}")
    private String comCode;
    @Value("${mat.sequCode}")
    private String sequCode;

    @PostMapping(value = "/getHtTempletById")
    @ApiOperation(value = "根据唯一标识获取合同模板", httpMethod = "POST", notes = "根据唯一标识获取合同模板")
    public Response<HtTemplet> getHtTempletById(String id) {
        Response<HtTemplet> result = new Response<>();
        //根据id查询没有删除的合同模板
        if (id != null && !"".equals(id)) {
            HtTemplet htTemplet = new HtTemplet();
            htTemplet.setId(id);
            HtTemplet templet = htTempletDaoMapper.getHtTempletById(htTemplet);
            result.setData(templet);
        } else {
            //如果id为空则参数错误
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        }
        return result;
    }


    @ApiOperation(value = "保存合同模板", httpMethod = "POST", notes = "保存合同模板")
    @PostMapping(value = "/saveTemplet")
    public Response<ResultMessage> saveTemplet(@RequestBody TempletParams templetParams) {
        Response<ResultMessage> result = new Response<ResultMessage>();
        ResultMessage resultMessage = new ResultMessage();
        //判断名称是否重复

        HtTemplet htTemplet = new HtTemplet();
        htTemplet.setName(templetParams.getName());
        htTemplet.setIsDelete(0L);
        htTemplet.setComCode(UserServletContext.getUserInfo().getComCode());
        htTemplet.setModuCode(UserServletContext.getUserInfo().getModuCode());
        //获取合同模板编码
        LoginUser loginUser = UserServletContext.getUserInfo();
        String no = ctlComRuleService.getNext(comCode, sequCode);
        //根据名称查询模板是否存在
        HtTemplet templet = htTempletDaoMapper.getHtTempletByName(htTemplet);
        if (templet != null) {
            //如果存在返回名称重复
            resultMessage.setCode(3);
            resultMessage.setMessage("模板名称重复,请重新输入!");
            result.setData(resultMessage);
            return result;
        }
        if (templetParams != null) {
            htTempletService.save(loginUser, templetParams, no);
            resultMessage.setCode(0);
            resultMessage.setMessage("保存成功!");
            result.setData(resultMessage);
            return result;
        } else {
            resultMessage.setCode(-1);
            resultMessage.setMessage("参数不能为空!");
            result.setData(resultMessage);
            return result;
        }
    }

    @ApiOperation(value = "根据唯一标识修改合同模板信息", httpMethod = "POST", notes = "根据唯一标识修改合同模板信息")
    @PostMapping(value = "/updateHtTempletById")
    public Response<ResultMessage> updateHtTempletById(@RequestBody TempletParams templetParams) {

        Response<ResultMessage> result = new Response<>();
        ResultMessage dataResult = new ResultMessage();
        if (templetParams.getId() == null || "".equals(templetParams.getId())) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        }
        HtTemplet htTemplet = new HtTemplet();
        htTemplet.setName(templetParams.getName());
            htTemplet.setComCode(UserServletContext.getUserInfo().getComCode());
            htTemplet.setModuCode(UserServletContext.getUserInfo().getModuCode());
        HtTemplet templet = htTempletService.get(htTemplet);
        if (templet != null && !templetParams.getId().equals(templet.getId())) {
            dataResult.setCode(3);
            dataResult.setMessage("模板名称重复!");
        } else {
            BeanUtils.copyProperties(templetParams, htTemplet);
            boolean flag = htTempletService.updateById(templetParams.getId(), htTemplet);
            if (flag == true) {
                dataResult.setCode(0);
                dataResult.setMessage("编辑成功!");
            } else {
                dataResult.setCode(-1);
                dataResult.setMessage("编辑失败!");
            }
        }
        result.setData(dataResult);
        return result;
    }

    @ApiOperation(value = "根据唯一标识删除合同模板", httpMethod = "POST", notes = "根据唯一标识删除合同模板")
    @PostMapping(value = "/deleteHtTempletById")
    public Response<ResultMessage> deleteHtTempletById(
            @RequestParam(value = "id", required = true) String id) {

        Response<ResultMessage> result = new Response<>();
        ResultMessage dataResult = new ResultMessage();
        //如果id为空则输出参数错误
        if (id == null || "".equals(id)) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        }
        //根据唯一标识删除合同模板
        boolean flag = htTempletService.deleteById(id);
        if (flag == true) {
            dataResult.setCode(0);
            dataResult.setMessage("删除成功!");
        } else {
            dataResult.setCode(-1);
            dataResult.setMessage("删除失败!");
        }
        result.setData(dataResult);
        return result;
    }

    @ApiOperation(value = "获取合同模板列表", httpMethod = "POST", notes = "获取合同模板列表")
    @PostMapping(value = "/findHtTempletList")
    public Response<Page<HtTempletVO>> findHtTempletList(
            @RequestParam(value = "pageNum", required = true) int pageNum,
            @RequestParam(value = "pageSize", required = true) int pageSize) {

        Response<Page<HtTempletVO>> result = new Response<>();
        //传入已删除的标识，在后台判断不等于该标识
        HtTemplet htTemplet = new HtTemplet();
        htTemplet.setIsDelete(1L);
        Page<HtTempletVO> page = htTempletService.findHtTempletList(htTemplet, pageNum, pageSize);
        result.setData(page);
        return result;
    }


}