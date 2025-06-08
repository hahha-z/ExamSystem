package com.ljx.common;

import com.ljx.entity.Answer;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class AnswerCorrect {


    private HashMap examSubmission;
    private List<Answer> answer;
}
