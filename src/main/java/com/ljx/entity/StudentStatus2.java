package com.ljx.entity;

import lombok.Data;

@Data
public class StudentStatus2 extends ExamStatus{


    private String studentName;
    private String time;
    private int retakeCount;
}
