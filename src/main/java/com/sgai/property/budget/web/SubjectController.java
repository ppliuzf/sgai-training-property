package com.sgai.property.budget.web;

import com.sgai.property.budget.service.SubServiceImpl;
import com.sgai.property.budget.vo.SubjectEditParam;
import com.sgai.property.budget.vo.SubjectSaveParam;
import com.sgai.property.budget.vo.SubjectVo;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 145811 on 2017/11/16.
 */
@RestController
@RequestMapping("/subject")
@Api(description = "科目")
public class SubjectController extends BaseController {

    @Autowired
    private SubServiceImpl subjectService;

    @ApiOperation(value = "科目详情", notes = "科目详情")
    @RequestMapping(value = "/subjectDetail", method = RequestMethod.POST)
    public Response<SubjectVo> subjectDetail(

    		@ApiParam(value = "科目id", required = true) 
    		@RequestParam("subjectId") String subjectId) {
        Response<SubjectVo> result = new Response<SubjectVo>();

        SubjectVo vo = subjectService.getSubjectDetail(subjectId);
        result.setData(vo);
        return result;
    }

    @ApiOperation(value = "科目列表", notes = "科目列表")
    @RequestMapping(value = "/subjectList", method = RequestMethod.POST)
    public Response<List<SubjectVo>> subjectList(

    		) {
        Response<List<SubjectVo>> result = new Response<List<SubjectVo>>();

        List<SubjectVo> voList = subjectService.getSubjectList();
        result.setData(voList);
        return result;
    }

    @ApiOperation(value = "新增科目", notes = "新增科目")
    @RequestMapping(value = "/subjectAdd", method = RequestMethod.POST)
    public Response<Boolean> subjectAdd(@RequestBody SubjectSaveParam subject) {
        Response<Boolean> result = new Response<Boolean>();

        boolean bool = subjectService.subjectAdd(subject);
        result.setData(bool);
        return result;
    }

    @ApiOperation(value = "删除科目", notes = "删除科目")
    @RequestMapping(value = "/subjectDelete", method = RequestMethod.POST)
    public Response<Boolean> subjectDelete(

    		@ApiParam(value = "科目id") 
    		@RequestParam(value = "subjectId", required = true ) String subjectId) {
        Response<Boolean> result = new Response<Boolean>();

        boolean bool = subjectService.subjectDelete(subjectId);
        result.setData(bool);
        return result;
    }
    
    @ApiOperation(value = "编辑科目", notes = "编辑科目")
    @RequestMapping(value = "/subjectEdit", method = RequestMethod.POST)
    public Response<Boolean> subjectEdit(@RequestBody SubjectEditParam subject) {

        Response<Boolean> result = new Response<Boolean>();

        boolean bool = subjectService.subjectEdit(subject);
        result.setData(bool);
        return result;
    }

}
