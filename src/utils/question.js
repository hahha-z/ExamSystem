import request from '../utils/request';

// 题目相关接口
export default {
  // 获取题目列表（分页）
  getQuestions(courseId, pageNum = 1, pageSize = 2) {
    return request({
      url: '/questions',
      method: 'post',
      data: { courseId, pageNum, pageSize }
    });
  },
  // 新增题目
  addQuestion(courseId, questionData) {
    return request({
      url: '/questions/add',
      method: 'post',
      data: { courseId, ...questionData }
    });
  },
  // 删除题目
  deleteQuestion(questionId) {
    return request({
      url: '/questions/delete',
      method: 'post',
      data: { questionId }
    });
  },
  // 编辑题目
  editQuestion(questionId, questionData) {
    return request({
      url: '/questions/edit',
      method: 'post',
      data: { questionId, ...questionData }
    });
  }
};
