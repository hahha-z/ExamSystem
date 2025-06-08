package com.ljx.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ljx.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljx.entity.ExamStatus;
import com.ljx.entity.StudentCourse;
import com.ljx.entity.TeacherCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ljx
 * @since 2025-06-03
 */
@Mapper
@Repository
public interface CourseMapper extends BaseMapper<Course> {

    StudentCourse[] select(Integer studentId);

    TeacherCourse[] selectC(Integer workId);
}
