package com.ljx.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljx.common.QueryPageParam;
import com.ljx.common.Result;
import com.ljx.entity.Teacher;
import com.ljx.entity.Teacher;
import com.ljx.service.TeacherService;
import com.ljx.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljx
 * @since 2025-06-03
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/login")
    public Result login(@RequestBody Teacher teacher) {
        //校验用户名密码是否正确
        Teacher loginTeacher = teacherService.login(teacher);
        Map<String, Object> map;
        if (loginTeacher != null) {

            String roleId = String.valueOf(loginTeacher.getRoleId());


            map = new HashMap<>();
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), String.valueOf(loginTeacher.getId()), null);
            map.put("teacher", loginTeacher);
            map.put("token", token);
        } else {
            //如果不正确 给出相应的提示
            return Result.fail();
        }

        return Result.success(map);
    }
    @GetMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();

        /*String name = String.valueOf(param.get("name"));
        String sex = String.valueOf(param.get("sex"));
        String sex = String.valueOf(param.get("sex"));
        String roleId = String.valueOf(param.get("roleId"));*/

        String wid = String.valueOf(param.get("workId"));


        Page<Teacher> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();

        /*if(StringUtils.isNotBlank(name)){
            queryWrapper.like(Teacher::getName, name);
        }

        if(StringUtils.isNotBlank(sex)){
            queryWrapper.eq(Teacher::getSex, sex);
        }

        if(StringUtils.isNotBlank(roleId)){
            queryWrapper.eq(Teacher::getRoleId, roleId);
        }*/

        if(StringUtils.isNotBlank(wid) && !"null".equals(wid)){
            queryWrapper.eq(Teacher::getWorkId, wid);
        }

//        IPage result = userService.pageC(page);
        IPage result = teacherService.pageCC(page, queryWrapper);
        System.out.println("total===" + result.getTotal());

        if (result.getTotal() > 0) {
            return Result.success(result.getRecords(), result.getTotal());
        }

        return Result.fail();
    }

}
