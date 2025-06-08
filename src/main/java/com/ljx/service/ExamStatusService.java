package com.ljx.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljx.entity.ExamResult;
import com.ljx.entity.ExamStatus;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljx
 * @since 2025-06-04
 */
public interface ExamStatusService extends IService<ExamStatus> {
    IPage pageCC(IPage<ExamStatus> page, Wrapper Wrapper);
}
