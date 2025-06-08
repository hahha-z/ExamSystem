package com.ljx.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljx.common.QueryPageParam;
import com.ljx.common.Result;
import com.ljx.entity.ExamResult;
import com.ljx.entity.ExamResult;
import com.ljx.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljx
 * @since 2025-06-04
 */
@RestController
@RequestMapping("/exam-result")
public class ExamResultController {

    @Autowired
    private ExamResultService examResultService;


    @GetMapping("/listPage")
    public List<ExamResult> listPage(@RequestBody QueryPageParam query) {
        /*LambdaQueryWrapper<ExamResult> queryWrapper = new LambdaQueryWrapper<>();
        //模糊查询（名字或id或……）
        queryWrapper.like(ExamResult::getQuestionId ,examResult.getQuestionId());
        //精准匹配
        queryWrapper.eq(ExamResult::getQuestionId ,examResult.getQuestionId());*/

/*
        System.out.println("num===" + query.getPageNum());
        System.out.println("size===" + query.getPageSize());*/

        LambdaQueryWrapper<ExamResult> queryWrapper = new LambdaQueryWrapper<>();

        HashMap param = query.getParam();
//        String sid = String.valueOf(param.get("sid"));
        String cid = String.valueOf(param.get("courseId"));
      /*  System.out.println("name===" + (String)param.get("name"));
        System.out.println("no===" + (String)param.get("no"));*/

        Page<ExamResult> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

       /* if(StringUtils.isNotBlank(sid) && !"null".equals(sid)){
            queryWrapper.eq(ExamResult::getStudentId, sid);
        }*/

        if(StringUtils.isNotBlank(cid) && !"null".equals(cid)){
            queryWrapper.apply("er.course_id="+ cid);
        }

        IPage result = examResultService.pageCC(page, queryWrapper);
        System.out.println("total===" + result.getTotal());


        return result.getRecords();
    }




}
