package com.sgai.property.supplier.web;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.supplier.entity.GysContent;
import com.sgai.property.supplier.entity.GysSupplier;
import com.sgai.property.supplier.service.GysContentServiceImpl;
import com.sgai.property.supplier.service.GysSupplierServiceImpl;
import com.sgai.property.supplier.vo.DataResult;
import com.sgai.property.supplier.vo.GysContentParams;
import com.sgai.property.supplier.vo.GysContentVO;
import com.sgai.property.supplier.vo.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
@Api(description = "供应商内容")
public class GysContentController extends BaseController {
    @Autowired
    private GysContentServiceImpl gysContentService;
    @Autowired
    private GysSupplierServiceImpl gysSupplierService;

    @ApiOperation(value = "获取所有供应商内容列表", httpMethod = "POST", notes = "获取所有供应商内容列表")
    @PostMapping(value = "/findAllContentList")
    public Response<List<GysContent>> list() {

        List<GysContent> gysContentList = gysContentService.findAllList();
        Response<List<GysContent>> result = new Response<>();
        result.setData(gysContentList);
        return result;
    }

    @ApiOperation(value = "新增供应商内容", httpMethod = "POST", notes = "新增供应商内容")
    @PostMapping(value = "/saveGysContent")
    public Response<ResultMessage> saveGysContent(
            @RequestBody GysContentParams gysContentParams) {
        Response<ResultMessage> result = new Response<>();
        ResultMessage dataResult = new ResultMessage();
        GysContent gysContent = new GysContent();
        gysContent.setName(gysContentParams.getName());
        GysContent content = gysContentService.get(gysContent);
        //如果供应商内容不为空，则返回提示名称重复
        if (content != null) {
            dataResult.setCode("3");
            dataResult.setMessage("内容名称重复,请重新输入!");
        } else {
            //如果供应商内容为空，则添加
            gysContent.setDescription(gysContentParams.getDescription());
            gysContentService.save(gysContent);
            dataResult.setCode("0");
            dataResult.setMessage("保存成功!");
        }
        result.setData(dataResult);
        return result;
    }

    @ApiOperation(value = "根据唯一标示获取供应商内容", httpMethod = "POST", notes = "根据唯一标示获取供应商内容")
    @PostMapping(value = "/getGysContentById")
    public Response<GysContent> getGysContentById(
            @RequestParam  String id) {
        Response<GysContent> result = new Response<>();
        DataResult dataResult = new DataResult();

        if (id == null || "".equals(id)) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        }
        result.setData(gysContentService.getById(id));
        return result;
    }

    @ApiOperation(value = "编辑供应商内容", httpMethod = "POST", notes = "编辑供应商内容")
    @PostMapping(value = "/updateGysContent")
    public Response<ResultMessage> updateGysContent(
            @RequestParam(value = "id", required = true) String id,
            @RequestBody GysContentParams gysContentParams) {

        Response<ResultMessage> result = new Response<>();
        ResultMessage dataResult = new ResultMessage();
        if (id == null || "".equals(id)) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        }
        GysContent gysContent = new GysContent();
        gysContent.setName(gysContentParams.getName());
        GysContent content = gysContentService.get(gysContent);
        if (content != null && !id.equals(content.getId())) {
            dataResult.setCode("3");
            dataResult.setMessage("内容名称重复!");
        } else {
            gysContent.setDescription(gysContentParams.getDescription());
            boolean flag = gysContentService.updateById(id, gysContent);
            if (flag == true) {
                dataResult.setCode("0");
                dataResult.setMessage("编辑成功!");
            } else {
                dataResult.setCode("-1");
                dataResult.setMessage("编辑失败!");
            }
        }
        result.setData(dataResult);
        return result;
    }


    @ApiOperation(value = "删除供应商内容", httpMethod = "POST", notes = "删除供应商内容")
    @PostMapping(value = "/deleteGysContentById")
    public Response<ResultMessage> deleteGysContentById(
            @RequestParam  String id) {

        Response<ResultMessage> result = new Response<>();
        ResultMessage dataResult = new ResultMessage();
        if (id == null || "".equals(id)) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        }
        GysSupplier gysSupplier = new GysSupplier();
        gysSupplier.setContentId(id);
        boolean flag = gysContentService.deleteById(id);
        if (flag == true) {
            dataResult.setCode("0");
            dataResult.setMessage("删除成功!");
        } else {
            dataResult.setCode("-1");
            dataResult.setMessage("删除失败!");
        }
        result.setData(dataResult);
        return result;
    }

    @ApiOperation(value = "获取供应商内容列表", httpMethod = "POST", notes = "获取供应商内容列表")
    @PostMapping(value = "/findGysContentList")
    public Response<Page<GysContentVO>> findGysContentList(
            @RequestParam(value = "pageNum", required = true) int pageNum,
            @RequestParam(value = "pageSize", required = true) int pageSize) {

        Response<Page<GysContentVO>> result = new Response<>();
        //传入已删除的标识，在后台判断不等于该标识
        GysContent gysContent = new GysContent();
        gysContent.setIsDelete(1L);
        gysContent.setComCode(UserServletContext.getUserInfo().getComCode());
        gysContent.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<GysContentVO> page = gysContentService.findGysContentList(gysContent, pageNum, pageSize);
        result.setData(page);
        return result;
    }

}