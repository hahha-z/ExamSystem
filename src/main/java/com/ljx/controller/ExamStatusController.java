package com.ljx.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljx.common.QueryPageParam;
import com.ljx.common.Result;
import com.ljx.entity.ExamStatus;
import com.ljx.entity.ExamStatus;
import com.ljx.service.ExamStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljx
 * @since 2025-06-04
 */
@RestController
@RequestMapping("/exam-status")
public class ExamStatusController {

    @Autowired
    private ExamStatusService examStatusService;

    @GetMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();

        String id = String.valueOf(param.get("examStatusId"));


        Page<ExamStatus> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        QueryWrapper<ExamStatus> queryWrapper = new QueryWrapper<>();
//        queryWrapper.apply("r.goods = g.id and g.storage = s.id and g.goodsType = gt.id");



        if(StringUtils.isNotBlank(id) && !"null".equals(id)){
//            queryWrapper.like(ExamStatus::getGoods, name);
            queryWrapper.apply("s.stu_id = "+id);
        }


//        IPage result = examStatusService.pageC(page);
        IPage result = examStatusService.pageCC(page, queryWrapper);

        System.out.println("total===" + result.getTotal());


        return Result.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/save")
    public Result save(@RequestBody ExamStatus examStatus) {
        return examStatusService.save(examStatus) ? Result.success(examStatus) : Result.fail();
    }


}
