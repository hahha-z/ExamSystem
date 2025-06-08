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
 * 
 * </p>
 *
 * @author ljx
 * @since 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ExamQuestion对象", description="")
public class ExamQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer courseId;

    @ApiModelProperty(value = "题号")
    private String questionId;

    @ApiModelProperty(value = "题目")
    private String question;

    @ApiModelProperty(value = "选项")
    private String options;

    @ApiModelProperty(value = "答案")
    private String answer;

    @ApiModelProperty(value = "分数")
    private Integer score;


}
