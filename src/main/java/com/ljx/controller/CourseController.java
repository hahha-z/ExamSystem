package com.ljx.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljx.common.QueryPageParam;
import com.ljx.common.Result;
import com.ljx.entity.Course;
import com.ljx.entity.StudentCourse;
import com.ljx.entity.Teacher;
import com.ljx.entity.TeacherCourse;
import com.ljx.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljx
 * @since 2025-06-03
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping("/select")
    public Result selectByStudentId(Integer studentId) {
        StudentCourse[] ob = courseService.select(studentId);

        Map<String, Object> map = new HashMap<>();
        map.put("course", ob);
        if (ob != null) {

            return Result.success(ob);

        }
        return Result.fail();
    }

    @GetMapping("/selectC")
    public Result selectByWorkId(Integer workId) {
        TeacherCourse[] ob = courseService.selectC(workId);

        Map<String, Object> map = new HashMap<>();
        map.put("course", ob);
        if (ob != null) {

            return Result.success(ob);

        }
        return Result.fail();
    }
}
