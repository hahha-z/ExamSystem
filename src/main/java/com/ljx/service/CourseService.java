package com.ljx.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljx.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljx.entity.ExamStatus;
import com.ljx.entity.StudentCourse;
import com.ljx.entity.TeacherCourse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljx
 * @since 2025-06-03
 */

public interface CourseService extends IService<Course> {


    StudentCourse[] select(Integer studentId);
    TeacherCourse[] selectC(Integer workId);

}
