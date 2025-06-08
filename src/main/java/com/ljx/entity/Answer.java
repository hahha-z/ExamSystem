package com.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学生答案表
 * </p>
 *
 * @author ljx
 * @since 2025-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Answer对象", description="学生答案表")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "答案ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "考试提交ID（关联exam_submission表）")
    private Integer submissionId;

    @ApiModelProperty(value = "题目ID（关联exam_question表）")
    private Integer questionId;

    @ApiModelProperty(value = "学生答案（如'A'）")
    private String answer;


}
