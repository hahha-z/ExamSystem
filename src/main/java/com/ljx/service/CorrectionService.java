package com.ljx.service;

import com.ljx.entity.Answer;
import com.ljx.entity.ExamQuestion;
import com.ljx.entity.Answer;
import com.ljx.entity.ExamQuestion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 批改服务
 */

public interface CorrectionService {

    /**
     * 批改单选题并计算分数
     * @param studentAnswers 学生答案列表
     * @param examQuestions 题目列表（含正确答案和分值）
     * @return 总分
     */

    int correctSingleChoice(List<Answer> studentAnswers, List<com.ljx.entity.ExamQuestion> examQuestions);
}