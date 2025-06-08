package com.ljx.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljx.entity.ExamResult;
import com.ljx.entity.ExamResult;
import com.ljx.mapper.ExamResultMapper;
import com.ljx.mapper.ExamResultMapper;
import com.ljx.service.ExamResultService;
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
public class ExamResultServiceImpl extends ServiceImpl<ExamResultMapper, ExamResult> implements ExamResultService {
    @Autowired
    private ExamResultMapper examResultMapper;

    @Override
    public IPage pageCC(IPage<ExamResult> page, Wrapper wrapper) {
        return examResultMapper.pageCC(page,wrapper);
    }
}
