package com.ljx.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ljx.entity.ExamResult;
import com.ljx.entity.ExamStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ljx
 * @since 2025-06-04
 */
public interface ExamStatusMapper extends BaseMapper<ExamStatus> {

    IPage pageCC(IPage<ExamStatus> page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
