package com.ljx.entity;

import lombok.Data;

import java.util.Date;

@Data
public class StudentStatus extends ExamStatus{

    private String studentName;
    private String time;
}
