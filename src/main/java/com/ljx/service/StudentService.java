package com.ljx.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljx.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljx
 * @since 2025-06-03
 */
public interface StudentService extends IService<Student> {

    Student login(Student stu);

    IPage pageCC(IPage<Student> page, Wrapper wapper);


    boolean updateByPhone(Student student, LambdaQueryWrapper<Student> queryWrapper);

    String getStudentNameByStuId(Integer studentId);
}
