package com.ljx.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljx.common.QueryPageParam;
import com.ljx.common.Result;
import com.ljx.entity.ExamQuestion;
import com.ljx.entity.Student;
import com.ljx.entity.Student;
import com.ljx.service.StudentService;
import com.ljx.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private StudentService studentService;


    @PostMapping("/save")
    public Result save(@RequestBody Student student) {
        return studentService.save(student) ? Result.success(student) : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Student student) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        String sid = String.valueOf(student.getStuId());

        if (StringUtils.isBlank(sid)&& "null".equals(sid)) {
            return Result.fail();
        }


        if(StringUtils.isNotBlank(sid) && !"null".equals(sid)){
            queryWrapper.eq(Student::getStuId, sid);
        }

        return studentService.update(student,queryWrapper) ? Result.success(student) : Result.fail();
    }

    @PostMapping("/updateP")
    public Result updateP(@RequestBody Student student) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        String sid = String.valueOf(student.getStuId());

        if (StringUtils.isBlank(sid)&& "null".equals(sid)) {
            return Result.fail();
        }


        if(StringUtils.isNotBlank(sid) && !"null".equals(sid)){
            queryWrapper.eq(Student::getStuId, sid);
        }

        return studentService.updateByPhone(student,queryWrapper) ? Result.success() : Result.fail();
    }



    @PostMapping("/login")
    public Result login(@RequestBody Student stu) {
        //校验用户名密码是否正确
        Student loginStudent = studentService.login(stu);
        Map<String, Object> map;
        if (loginStudent != null) {

            String roleId = String.valueOf(loginStudent.getRoleId());


            map = new HashMap<>();
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), String.valueOf(loginStudent.getId()), null);
            map.put("student", loginStudent);
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

        String sid = String.valueOf(param.get("studentId"));


        Page<Student> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();

        /*if(StringUtils.isNotBlank(name)){
            queryWrapper.like(Student::getName, name);
        }

        if(StringUtils.isNotBlank(sex)){
            queryWrapper.eq(Student::getSex, sex);
        }

        if(StringUtils.isNotBlank(roleId)){
            queryWrapper.eq(Student::getRoleId, roleId);
        }*/

        if(StringUtils.isNotBlank(sid) && !"null".equals(sid)){
            queryWrapper.eq(Student::getStuId, sid);
        }

//        IPage result = userService.pageC(page);
        IPage result = studentService.pageCC(page, queryWrapper);
        System.out.println("total===" + result.getTotal());


        return Result.success(result.getRecords(), result.getTotal());
    }

}
