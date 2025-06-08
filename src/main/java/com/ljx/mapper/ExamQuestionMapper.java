package com.ljx.mapper;

import com.ljx.entity.ExamQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ljx
 * @since 2025-06-04
 */
@Repository
@Mapper
public interface ExamQuestionMapper extends BaseMapper<ExamQuestion> {

}
