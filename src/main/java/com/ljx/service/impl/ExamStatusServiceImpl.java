package com.ljx.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljx.entity.ExamStatus;
import com.ljx.entity.ExamStatus;
import com.ljx.mapper.ExamStatusMapper;
import com.ljx.mapper.ExamStatusMapper;
import com.ljx.service.ExamStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljx
 * @since 2025-06-04
 */
@Service
public class ExamStatusServiceImpl extends ServiceImpl<ExamStatusMapper, ExamStatus> implements ExamStatusService {


    @Autowired
    private ExamStatusMapper examStatusMapper;

    @Override
    public IPage pageCC(IPage<ExamStatus> page, Wrapper wrapper) {
        return examStatusMapper.pageCC(page,wrapper);
    }
}
