package com.sgai.property.supplier.web;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.supplier.entity.GysLevel;
import com.sgai.property.supplier.entity.GysSupplier;
import com.sgai.property.supplier.service.GysLevelServiceImpl;
import com.sgai.property.supplier.vo.DataResult;
import com.sgai.property.supplier.vo.GysLevelParams;
import com.sgai.property.supplier.vo.GysLevelVO;
import com.sgai.property.supplier.vo.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/level")
@Api(description = "供应商等级")
public class GysLevelController extends BaseController {
    @Autowired
    private GysLevelServiceImpl gysLevelService;

    @ApiOperation(value = "获取所有供应商等级列表", httpMethod = "POST", notes = "获取所有供应商等级列表")
    @PostMapping(value = "/findAllLevelList")
    public Response<List<GysLevel>> list() {

        List<GysLevel> gysContentList = gysLevelService.findAllList();
        Response<List<GysLevel>> result = new Response<>();
        result.setData(gysContentList);
        return result;
    }

    @ApiOperation(value = "新增供应商等级", httpMethod = "POST", notes = "新增供应商等级")
    @PostMapping(value = "/saveGysLevel")
    public Response<ResultMessage> saveGysLevel(
            @RequestBody GysLevelParams gysLevelParams) {
        Response<ResultMessage> result = new Response<>();
        ResultMessage dataResult = new ResultMessage();
        GysLevel gysLevel = new GysLevel();
        gysLevel.setName(gysLevelParams.getName());
        GysLevel level = gysLevelService.get(gysLevel);
        //如果供应商级别不为空，则返回提示名称重复
        if (level != null) {
            dataResult.setCode("3");
            dataResult.setMessage("等级名称重复,请重新输入!");
        } else {
            //如果供应商级别为空，则添加
            gysLevel.setDescription(gysLevelParams.getDescription());
            gysLevelService.save(gysLevel);
            dataResult.setCode("0");
            dataResult.setMessage("保存成功!");
        }
        result.setData(dataResult);
        return result;
    }

    @ApiOperation(value = "根据唯一标示获取供应商等级", httpMethod = "POST", notes = "根据唯一标示获取供应商等级")
    @PostMapping(value = "/getGysLevelById")
    public Response<GysLevel> getGysLevelById(
            @RequestParam  String id) {
        Response<GysLevel> result = new Response<>();
        DataResult dataResult = new DataResult();

        if (id == null || "".equals(id)) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        }
        result.setData(gysLevelService.getById(id));
        return result;
    }


    @ApiOperation(value = "编辑供应商等级", httpMethod = "POST", notes = "编辑供应商等级")
    @PostMapping(value = "/updateGysLevel")
    public Response<ResultMessage> updateGysLevel(
            @RequestParam(value = "id", required = true) String id,
            @RequestBody GysLevelParams gysLevelParams) {

        Response<ResultMessage> result = new Response<>();
        ResultMessage dataResult = new ResultMessage();
        if (id == null || "".equals(id)) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        }
        GysLevel gysLevel = new GysLevel();
        gysLevel.setName(gysLevelParams.getName());
        GysLevel level = gysLevelService.get(gysLevel);
        if (level != null && !id.equals(level.getId())) {
            dataResult.setCode("3");
            dataResult.setMessage("级别名称重复!");
        } else {
            gysLevel.setDescription(gysLevelParams.getDescription());
            boolean flag = gysLevelService.updateById(id, gysLevel);
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


    @ApiOperation(value = "删除供应商等级", httpMethod = "POST", notes = "删除供应商等级")
    @PostMapping(value = "/deleteGysLevelById")
    public Response<ResultMessage> deleteGysLevelById(
            @RequestParam  String id) {

        Response<ResultMessage> result = new Response<>();
        ResultMessage dataResult = new ResultMessage();
        if (id == null || "".equals(id)) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        }
        GysSupplier gysSupplier = new GysSupplier();
        gysSupplier.setLevelId(id);
        boolean flag = gysLevelService.deleteById(id);
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


    @ApiOperation(value = "获取供应商等级列表", httpMethod = "POST", notes = "获取供应商等级列表")
    @PostMapping(value = "/findGysLevelList")
    public Response<Page<GysLevelVO>> findGysLevelList(
            @RequestParam(value = "pageNum", required = true) int pageNum,
            @RequestParam(value = "pageSize", required = true) int pageSize) {

        Response<Page<GysLevelVO>> result = new Response<>();
        //传入已删除的标识，在后台判断不等于该标识
        GysLevel gysLevel = new GysLevel();
        gysLevel.setIsDelete(1L);
        gysLevel.setComCode(UserServletContext.getUserInfo().getComCode());
        gysLevel.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<GysLevelVO> page = gysLevelService.findGysLevelList(gysLevel, pageNum, pageSize);
        result.setData(page);
        return result;
    }

}