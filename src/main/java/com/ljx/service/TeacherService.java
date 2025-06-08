package com.ljx.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljx.entity.Student;
import com.ljx.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljx
 * @since 2025-06-03
 */
public interface TeacherService extends IService<Teacher> {

    Teacher login(Teacher teacher);

    IPage pageCC(IPage<Teacher> page, Wrapper wapper);

}
