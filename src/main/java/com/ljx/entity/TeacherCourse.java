package com.ljx.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherCourse extends Teacher{
    private String courseName;
    private Date examTime;
}
