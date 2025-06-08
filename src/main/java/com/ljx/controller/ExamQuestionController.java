package com.ljx.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljx.common.QueryPageParam;
import com.ljx.common.Result;
import com.ljx.entity.Answer;
import com.ljx.entity.ExamQuestion;
import com.ljx.entity.ExamResult;
import com.ljx.mapper.ExamResultMapper;
import com.ljx.service.ExamQuestionService;
import com.ljx.service.ExamResultService;
import com.ljx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import com.ljx.common.AnswerCorrect;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljx
 * @since 2025-06-04
 */
@RestController
@RequestMapping("/exam-question")
public class ExamQuestionController {

    @Autowired
    private ExamQuestionService examQuestionService;

    @Autowired
    private ExamResultService examResultService;


    @Autowired
    private StudentService studentService; // 学生服务（用于查询姓名）
    @Qualifier("examResultMapper")
    @Autowired
    private ExamResultMapper examResultMapper;

    @PostMapping("/save")
    public Result save(@RequestBody ExamQuestion examQuestion) {
        return examQuestionService.save(examQuestion) ? Result.success(examQuestion) : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody ExamQuestion examQuestion) {
        LambdaQueryWrapper<ExamQuestion> queryWrapper = new LambdaQueryWrapper<>();
        String cid = String.valueOf(examQuestion.getCourseId());
        String qid = String.valueOf(examQuestion.getQuestionId());
        if (StringUtils.isBlank(cid)&& "null".equals(cid)) {
            return Result.fail();
        }
        if (StringUtils.isBlank(qid)&& "null".equals(qid)) {
            return Result.fail();
        }

        if(StringUtils.isNotBlank(qid) && !"null".equals(qid)){
            queryWrapper.eq(ExamQuestion::getQuestionId, qid);
        }

        if(StringUtils.isNotBlank(cid) && !"null".equals(cid)){
            queryWrapper.eq(ExamQuestion::getCourseId, cid);
        }
        return examQuestionService.update(examQuestion,queryWrapper) ? Result.success(examQuestion) : Result.fail();
    }

    @GetMapping("/del")
    public Result del(@RequestBody ExamQuestion examQuestion) {

        LambdaQueryWrapper<ExamQuestion> queryWrapper = new LambdaQueryWrapper<>();
        String cid = String.valueOf(examQuestion.getCourseId());
        String qid = String.valueOf(examQuestion.getQuestionId());
        if (StringUtils.isBlank(cid)&& "null".equals(cid)) {
            return Result.fail();
        }
        if (StringUtils.isBlank(qid)&& "null".equals(qid)) {
            return Result.fail();
        }

        if(StringUtils.isNotBlank(qid) && !"null".equals(qid)){
            queryWrapper.eq(ExamQuestion::getQuestionId, qid);
        }

        if(StringUtils.isNotBlank(cid) && !"null".equals(cid)){
            queryWrapper.eq(ExamQuestion::getCourseId, cid);
        }
        return examQuestionService.remove(queryWrapper) ? Result.success() : Result.fail();
    }

    @GetMapping("/listPage")
    public List<ExamQuestion> listPage(@RequestBody QueryPageParam query) {
        /*LambdaQueryWrapper<ExamQuestion> queryWrapper = new LambdaQueryWrapper<>();
        //模糊查询（名字或id或……）
        queryWrapper.like(ExamQuestion::getQuestionId ,examQuestion.getQuestionId());
        //精准匹配
        queryWrapper.eq(ExamQuestion::getQuestionId ,examQuestion.getQuestionId());*/

/*
        System.out.println("num===" + query.getPageNum());
        System.out.println("size===" + query.getPageSize());*/

        LambdaQueryWrapper<ExamQuestion> queryWrapper = new LambdaQueryWrapper<>();

        HashMap param = query.getParam();
        String qid = String.valueOf(param.get("qid"));
        String cid = String.valueOf(param.get("courseId"));
      /*  System.out.println("name===" + (String)param.get("name"));
        System.out.println("no===" + (String)param.get("no"));*/

        Page<ExamQuestion> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        if(StringUtils.isNotBlank(qid) && !"null".equals(qid)){
            queryWrapper.eq(ExamQuestion::getQuestionId, qid);
        }

        if(StringUtils.isNotBlank(cid) && !"null".equals(cid)){
            queryWrapper.eq(ExamQuestion::getCourseId, cid);
        }

        IPage result = examQuestionService.page(page, queryWrapper);
        System.out.println("total===" + result.getTotal());


        return result.getRecords();
    }

    @PostMapping("/correct")
    public Map<String, Object> correctExam(@RequestBody AnswerCorrect query) {
        // 解析请求参数
        /*ExamSubmission submission = (ExamSubmission) requestBody.get("examSubmission");
        List<Answer> studentAnswers = (List<Answer>) requestBody.get("studentAnswers");
*/
        HashMap submission = query.getExamSubmission();
        List<Answer> answer = query.getAnswer();
        int sid = Integer.parseInt(submission.get("studentId").toString());
        String cid = String.valueOf(submission.get("courseId"));
        int retakeCount = (int) submission.get("retakeCount");
        String examTime = (String) submission.get("examTime");
        // 从数据库获取学生姓名（仅需ID即可查询）
        String studentName = studentService.getStudentNameByStuId(sid);

        int totalScore = 0;

        for(int i = 0 ; i < answer.size() ; i++){
            // 计算总分
            String qid = String.valueOf(answer.get(i).getQuestionId());
            String ans = answer.get(i).getAnswer();

            Page<ExamQuestion> page = new Page();


            LambdaQueryWrapper<ExamQuestion> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ExamQuestion::getCourseId, cid);
            queryWrapper.eq(ExamQuestion::getQuestionId, qid);
            queryWrapper.eq(ExamQuestion::getAnswer, ans);

            IPage result = examQuestionService.page(page, queryWrapper);
            System.out.println("total===" + result.getTotal());



            if(result.getTotal() > 0){
                totalScore += 10;
            }


        }
        ExamResult examResult = new ExamResult();
        examResult.setCourseId(Integer.parseInt(cid));
        examResult.setStudentId(sid);
        examResult.setExamTime(LocalDateTime.parse(examTime));
        examResult.setRetakeCount(retakeCount);
        examResult.setScore(BigDecimal.valueOf(totalScore));

        examResultMapper.insert(examResult);



        // 封装结果
        Map<String, Object> map = new HashMap<>();
        map.put("studentName", studentName);
        map.put( "courseId", submission.get("courseId"));
        map.put( "totalScore", totalScore);
        return map;

    }


}
