package com.ljx.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ljx.entity.ExamResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljx.entity.ExamStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ljx
 * @since 2025-06-04
 */
@Mapper
@Repository
public interface ExamResultMapper extends BaseMapper<ExamResult> {

    IPage pageCC(IPage<ExamResult> page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
