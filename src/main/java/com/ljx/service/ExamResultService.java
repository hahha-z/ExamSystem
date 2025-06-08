package com.ljx.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljx.entity.ExamResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljx.entity.ExamStatus;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljx
 * @since 2025-06-04
 */
public interface ExamResultService extends IService<ExamResult> {

    IPage pageCC(IPage<ExamResult> page, Wrapper Wrapper);

}
