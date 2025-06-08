package com.ljx.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljx.entity.Course;
import com.ljx.entity.Course;
import com.ljx.entity.StudentCourse;
import com.ljx.entity.TeacherCourse;
import com.ljx.mapper.CourseMapper;
import com.ljx.mapper.CourseMapper;
import com.ljx.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljx
 * @since 2025-06-03
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public StudentCourse[] select(Integer studentId) {
        return courseMapper.select(studentId);
    }
    @Override
    public TeacherCourse[] selectC(Integer workId) {
        return courseMapper.selectC(workId);
    }


}

