<template>
  <div id="exam-info">
    <h2>我的考试</h2>
    <table class="exam-table">
      <thead>
        <tr>
          <th>考试科目</th>
          <th>考试状态</th>
          <th>考试分数</th>
          <th>进入考试</th>
          <th>进入重考</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="exam in exams" :key="exam.id">
          <td>{{ exam.subject }}</td>
          <td>{{ exam.hasTaken }}</td>
          <td>{{ exam.score || '-' }}</td>
          <td>
            <button v-if="exam.hasTaken === '未考'" @click="enterExam(exam.id)">进入考试</button>
          </td>
          <td>
            <button v-if="exam.hasTaken === '已考'" @click="retakeExam(exam.id)">进入重考</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import request from '../../utils/request'

export default {
  data() {
    return {
      exams: [
        // 模拟考试数据，实际需从后端获取
        {
          id: 1,
          subject: '数学',
          hasTaken: '未考',
          score: null
        },
        {
          id: 2,
          subject: '英语',
          hasTaken: '已考',
          score: 85
        }
      ],
      subjectMap:{
        1: '语文',
        2: '数学',
        3: '英语',
        4: 'java',
        5: 'javascript',
        6: '化学',
      },
      hasTakenMap:{
        0:'未考',
        1:'已考',
      }
    }
  },
  mounted() {
    // this.getExamsData()
    this.score = this.$route.query.score
  },
  methods: {
    // 直接获取后端数据，假设返回格式为对象数组
    async getExamsData() {
      try {
        const stuId = localStorage.getItem('stuId')
        // if (!stuId) throw new Error('未找到学生ID')

        const res = await request({
          url:'/course/select',
          method:'POST',
          data:{
            param:{
              studentId:stuId
            }
          }
        })
        const backData = res.data  

        //数组[{},{}]
        this.exams = backData.map(exam => ({
          id:exam.id,//课程id
          subject:this.subjectMap[exam.id],
          score:exam.score,
          hasTaken:this.hasTakenMap[exam.hasTaken] || '-'
        }))
        
      } catch (err) {
        console.error('数据获取失败', err)
      }
    },
    
    // 进入考试,数字标识？
    enterExam(examId) {
      this.$router.push({
        path:'/toexam/student',
        query:{examId}
      })
    },
    
    // 重考（仅前端清空分数，不调用后端）,,,,要改
    retakeExam(examId) {
      const exam = this.exams.find(e => e.id === examId)
      if (exam) {
        exam.score = null
        exam.hasTaken = 0  // 0表示未考
      }

      this.enterExam(examId)
    }
  }
}
</script>

<style scoped>
.exam-table {
  border-collapse: collapse;
  width: 100%;
  font-size: 16px;
}

.exam-table th,
.exam-table td {
  border: 1px solid #ccc;
  padding: 15px;
  text-align: center;
}

.exam-table th {
  background-color: #f2f2f2;
}
button {
  width: 70px;
  height: 35px;
  color: white;
  background-color: #4a7ac7;
  border: 1px solid #4a7ac7;
  border-radius: 4px;
}
button:hover {
  cursor: pointer;
}
</style>