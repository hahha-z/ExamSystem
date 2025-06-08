<template>
  <div class="exam-container">
    <header class="exam-header">
      <h1>线上考试系统</h1>
    </header>
 
    <div class="timer" v-if="isExamActive">剩余时间: {{ formattedTime }}</div>
 
    <QuestionCom 
      :currentQuestion="questionList[currentIndex]" 
      :selectedAnswer="selectedAnswers[questionList[currentIndex].questionId]"
      @answer-selected="answer => selectAnswer(questionList[currentIndex].questionId, answer)"
    />
 
    <div class="navigation-buttons" v-if="isExamActive">
      <button 
        @click="previousQuestion" 
        :disabled="currentIndex === 0 || isTimeUp"
      >
        上一题
      </button>
      <button 
        @click="nextQuestion" 
        :disabled="currentIndex === questionList.length - 1 || isTimeUp"
      >
        {{ currentIndex === questionList.length - 1 ? '提交试卷' : '下一题' }}
      </button>
    </div>
  </div>
</template>
 
<script>
import QuestionCom from '../../components/QuestionCom.vue';
// import request from '../../utils/request';
 
export default {
  components: {
    QuestionCom
  },
  data() {
    return {
      courseId: 1,
      questionList: [],
      currentIndex: 0,
      selectedAnswers: [],
      // selectedAnswers: {},
      timeLeft: 3600,
      timer: null,
      isTimeUp: false,
      isExamActive: true
    };
  },
  mounted() {
    this.courseId = this.$route.query.examId;
    this.fetchQuestions();
    this.startTimer();
  },
  computed: {
    formattedTime() {
      const minutes = Math.floor(this.timeLeft / 60);
      const seconds = this.timeLeft % 60;
      return `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    }
  },
  methods: {
    async fetchQuestions() {
      // try {
      //   const response = await request.post('/student/fetch-questions', {
      //     courseId: this.courseId
      //   });
      //   // 5道题的数组,q,opt,qId
      //   this.questionList = response.data;
      //   this.selectedAnswers = {};
      //   this.questionList.forEach(q => {
      //     this.selectedAnswers[q.questionId] = null;
      //   })
      // } catch (error) {
      //   console.error('获取题目失败:', error);
      // }

      // 假数据
      this.questionList = [
    {
      questionId: 1,
      question: "JavaScript是什么类型的语言？",
      options: [
        "编译型语言",
        "解释型语言",
        "汇编语言",
        "标记语言"
      ],
      correctAnswer: "B",
      explanation: "JavaScript是一种动态类型的解释型语言，由浏览器解释执行。"
    },
    {
      questionId: 2,
      question: "Vue.js是由哪位开发者创建的？",
      options: [
        "React团队",
        "Evan You",
        "Angular团队",
        "Facebook团队"
      ],
      correctAnswer: "B",
      explanation: "Vue.js是由Evan You于2014年创建的渐进式JavaScript框架。"
    },
    {
      questionId: 3,
      question: "以下哪个不是ES6新增的特性？",
      options: [
        "箭头函数",
        "类继承",
        "let/const",
        "Promise"
      ],
      correctAnswer: "B",
      explanation: "类继承在ES6之前就存在，ES6只是对其进行了语法改进。"
    },
    {
      questionId: 4,
      question: "Vue组件间通信方式有哪些？",
      options: [
        "props和$emit",
        "Vuex",
        "Event Bus",
        "以上都是"
      ],
      correctAnswer: "D",
      explanation: "Vue组件间通信方式包括props/$emit、Vuex、Event Bus、$refs等。"
    },
    {
      questionId: 5,
      question: "以下哪个是Vue的生命周期钩子？",
      options: [
        "mounted",
        "compiled",
        "linked",
        "attached"
      ],
      correctAnswer: "A",
      explanation: "mounted是Vue的标准生命周期钩子，表示DOM已挂载完成。"
    }
  ];
    },
    startTimer() {
      this.timer = setInterval(() => {
        if (this.timeLeft > 0) {
          this.timeLeft--;
        } else {
          this.timeIsUp();
        }
      }, 1000);
    },
    timeIsUp() {
      clearInterval(this.timer);
      this.isTimeUp = true;
      this.submitExam();
    },
    selectAnswer(questionId, answer) {
      if (this.isTimeUp) return;
      this.selectedAnswers[questionId] = answer;
    },
    previousQuestion() {
      if (this.currentIndex > 0) {
        this.currentIndex--;
      }
    },
    nextQuestion() {
      if (this.currentIndex < this.questionList.length - 1) {
        this.currentIndex++;
      } else {
        this.submitExam();
      }
    },
    async submitExam() {
      this.isExamActive = false;
      clearInterval(this.timer);
 
      // try {
      //   // 批改试卷
      //   const response = await request.post('/submit-exam', {
      //     courseId: this.courseId,
      //     // 数组[A,B,C,A,D]这种
      //     answers: this.selectedAnswers
      //   });
      //   this.score = response.data.score;
      //   this.$router.push({
      //     path:'/student/examinfo',
      //     query:{score:this.score}
      //   });
        
      // } catch (error) {
      //   console.error('提交考试失败:', error);
      // }

      const mockResponse = {
      data: {
            score: 80, // 假设得分为80
            correctAnswers: [1, 3, 5], // 正确的题目ID
            userAnswers: {
              1: "B",
              2: "A", // 错误答案
              3: "B", // 错误答案
              4: "D",
              5: "A"
            },
            feedback: "考试已完成，你的得分是80分，继续努力！"
          }
        };
      
        this.score = mockResponse.data.score;
        this.$router.push({
          path: '/student/examinfo',
          query: {
            score: this.score,
            details: JSON.stringify(mockResponse.data) // 传递详细结果
          }
        });
    }
  },
  beforeDestroy() {
    clearInterval(this.timer);
  }
};
</script>

<style>
.exam-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.exam-header {
  text-align: center;
  margin-bottom: 20px;
}

.timer {
  margin-bottom: 20px;
  text-align: center;
  font-size: 18px;
  color:red
}

.navigation-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.navigation-buttons button {
  padding: 10px 20px;
  cursor: pointer;
}

.navigation-buttons button:disabled {
  cursor: not-allowed;
  opacity: 0.5;
}

.result {
  text-align: center;
  margin-top: 20px;
}
</style>