package com.ljx.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@ApiModel(value="ExamResult对象", description="")
public class  ExamResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer studentId;

    private Integer courseId;

    private BigDecimal score;

    private LocalDateTime examTime;

    private Integer retakeCount;


}
