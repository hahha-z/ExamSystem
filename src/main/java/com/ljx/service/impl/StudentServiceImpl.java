package com.ljx.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljx.entity.Student;
import com.ljx.mapper.StudentMapper;
import com.ljx.service.StudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student login(Student stu) {
        return studentMapper.login(stu);
    }

    @Override
    public IPage pageCC(IPage<Student> page, Wrapper wrapper) {
        return studentMapper.pageCC(page,wrapper);
    }

    @Override
    public boolean updateByPhone(Student student, LambdaQueryWrapper<Student> queryWrapper) {
        return studentMapper.updateByPhone(student);
    }

    @Override
    public String getStudentNameByStuId(Integer studentId) {
        return studentMapper.getStudentNameByStuId(studentId);
    }

}
