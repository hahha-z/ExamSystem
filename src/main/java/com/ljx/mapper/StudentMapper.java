package com.ljx.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ljx.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface StudentMapper extends BaseMapper<Student> {

    Student login(Student stu);

    IPage pageCC(IPage<Student> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    boolean updateByPhone(Student stu);

    String getStudentNameByStuId(Integer studentId);
}






