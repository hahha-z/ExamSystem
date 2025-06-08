package com.ljx.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljx.entity.Student;
import com.ljx.entity.Teacher;
import com.ljx.mapper.TeacherMapper;
import com.ljx.service.TeacherService;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher login(Teacher teacher) {
        return teacherMapper.login(teacher);
    }

    @Override
    public IPage pageCC(IPage<Teacher> page, Wrapper wrapper) {
        return teacherMapper.pageCC(page,wrapper);
    }


}
