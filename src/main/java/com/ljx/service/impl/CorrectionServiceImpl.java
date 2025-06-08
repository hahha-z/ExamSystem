package com.ljx.service.impl;

import com.ljx.entity.Answer;
import com.ljx.entity.ExamQuestion;
import com.ljx.service.CorrectionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CorrectionServiceImpl implements CorrectionService {

    @Override
    public int correctSingleChoice(List<Answer> studentAnswers, List<ExamQuestion> examQuestions) {
        // 将题目按ID分组，便于快速匹配
        Map<Integer, ExamQuestion> questionMap = examQuestions.stream()
                .collect(Collectors.toMap(ExamQuestion::getId, q -> q));

        return studentAnswers.stream()
                .map(ans -> {
                    ExamQuestion q = questionMap.get(ans.getQuestionId());
                    return q != null && ans.getAnswer().equals(q.getAnswer()) ? q.getScore() : 0;
                })
                .reduce(Integer::sum)
                .orElse(0);
    }
}