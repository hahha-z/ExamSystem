<template>
  <div id="teacher-question-list">
    <!-- 加载态 -->
    <div class="loading" v-if="loading">
      <div class="spinner"></div>
      <p>加载中...</p>
    </div>

    <!-- 题目列表 -->
    <div v-else>
      <div v-for="(q, index) in currentPageQuestions" :key="q.id">
        <!-- 题目展示 -->
        <QuestionCom 
          :currentQuestion="q"
          :selectedAnswer="''"
          v-if="!editMode[index]"
        />
        
        <!-- 题目编辑 -->
        <QuestionEditor 
          :questionData="q"
          v-if="editMode[index]"
          @update-question="updateQuestion(index, $event)"
          @cancel-editing="cancelEdit(index)"
          @delete-question="deleteQuestion($event)"
        />
        <!-- 上面子传父 -->
        
        <!-- 编辑按钮 -->
        <div class="edit-buttons">
          <button 
            class="btn edit" 
            v-if="!editMode[index]"
            @click="startEdit(index)"
          >
            编辑题目
          </button>
          <button 
            class="btn back" 
            v-if="editMode[index]"
            @click="cancelEdit(index)"
          >
            返回查看
          </button>
        </div>
      </div>

      <!-- 分页按钮 -->
      <div class="pagination">
        <button 
          class="btn" 
          :disabled="pageNum === 1" 
          @click="prevPage"
        >
          上一页
        </button>
        <button 
          class="btn" 
          :disabled="pageNum >= totalPages" 
          @click="nextPage"
        >
          下一页
        </button>
      </div>

      <!-- 新增题目按钮 -->
      <button class="btn add-question" @click="addQuestion">
        + 新增题目
      </button>

      <!-- 提交按钮 -->
      <button class="btn submit-btn" @click="saveAllChanges">
        提交修改
      </button>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request';
import QuestionCom from '@/components/QuestionCom.vue';
import QuestionEditor from '@/components/QuestionEditor.vue';

export default {
  name: 'TeacherQuestionList',
  components: { QuestionCom, QuestionEditor },
  data() {
    return {
      workId: 'T001',
      courseId:1,
      pageNum: 1,
      pageSize: 5,
      total: 5,
      questions: [
        {
          id: '1',
          question: '下列哪个是JavaScript的基本数据类型？',
          options: ['String', 'Array', 'Object', 'Function'],
          answer: 'String'
        },
        {
          id: '2',
          question: 'HTML的全称是什么？',
          options: ['Hyper Text Markup Language', 'Home Tool Markup Language', 'Hyperlinks and Text Markup Language', 'Hyper Tool Markup Language'],
          answer: 'Hyper Text Markup Language'
        },
        {
          id: '3',
          question: 'CSS中，如何选择所有p元素？',
          options: ['p {}', '.p {}', '#p {}', '*p {}'],
          answer: 'p {}'
        },
        {
          id: '4',
          question: '下列哪个不是JavaScript的循环语句？',
          options: ['for', 'while', 'do...while', 'loop'],
          answer: 'loop'
        },
        {
          id: '5',
          question: '在Vue中，v-if指令的作用是什么？',
          options: ['循环渲染元素', '条件渲染元素', '绑定样式', '绑定事件'],
          answer: '条件渲染元素'
        }
      ],
      editMode: [], // 控制每个题目的编辑状态
      loading: false,
      addingNew: false
    };
  },
  computed: {
    currentPageQuestions() {
      const start = (this.pageNum - 1) * this.pageSize;
      return this.questions.slice(start, start + this.pageSize);//对题目切片
    },
    totalPages() {
      return Math.ceil(this.total / this.pageSize);//向上取整
    }
  },
  created() {
    // this.workId = this.$route.query.workId || 'T001';
    this.workId = localStorage.getItem('workId')
    this.courseId = this.$route.query.courseId
    // this.fetchQuestions();
  },
  methods: {
    // 分页获取题目
    async fetchQuestions() {
      this.loading = true;
      try {
        const res = await request.post('/exam-question/listPage',  {
            courseId:this.courseId,
            pageNum: this.pageNum,
            pageSize: this.pageSize
          }
        );
        if (res) {
          this.questions = res.data;
          // this.total = res.total;
          this.editMode = this.questions.map(() => false); // 初始化编辑状态
        } else {
          alert(res.message || '获取题目失败');
        }
      } catch (error) {
        console.error('获取题目失败', error);
        // alert('网络错误，请重试');
      } finally {
        this.loading = false;
      }
    },

    // 开始编辑题目
    startEdit(index) {
      this.editMode[index] = true;
    },

    // 取消编辑
    cancelEdit(index) {
      this.editMode[index] = false;
    },

    // 更新题目
    updateQuestion(index, updatedQuestion) {
      this.questions[index] = updatedQuestion;
      this.editMode[index] = false;
    },

    // 删除题目
    deleteQuestion(questionId) {
      if (confirm('确定删除该题目？')) {
        this.questions = this.questions.filter(q => q.id !== questionId);
        this.total = this.questions.length;
        this.editMode = this.editMode.filter((_, index) => this.questions[index]);
      }
    },

    // 新增题目
    addQuestion() {
      const newQuestion = {
        id: Date.now().toString(),
        question: '新增题目内容',
        options: ['选项A', '选项B']
      };
      this.questions.unshift(newQuestion);
      this.total = this.questions.length;
      this.editMode.unshift(true); // 新增后直接进入编辑
    },

    // 上一页
    prevPage() {
      if (this.pageNum > 1) {
        this.pageNum--;
        this.fetchQuestions();
      }
    },

    // 下一页
    nextPage() {
      if (this.pageNum < this.totalPages) {
        this.pageNum++;
        this.fetchQuestions();
      }
    },

    // 提交所有修改
    async saveAllChanges() {
      try {
        const res = await request.post('/api/teacher/questions/save', {
            workId: this.workId,
            questions: this.questions
          }
        );
        if (res.code === 200) {
          alert('提交成功！');
        } else {
          alert(res.message || '提交失败');
        }
      } catch (error) {
        console.error('提交失败', error);
        alert('网络错误，请重试');
      }
    }
  }
};
</script>

<style scoped>
#teacher-question-list {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}

.loading {
  text-align: center;
  padding: 50px 0;
}

.spinner {
  width: 40px;
  height: 40px;
  border: 4px solid #f3f3f3;
  border-top: 4px solid #42b983;
  border-radius: 50%;
  animation: spin 1s linear infinite;
  margin: 0 auto 10px;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.edit-buttons {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
  gap: 10px;
}

.btn.edit {
  background-color: #4a7ac7;
  color: #fff;
}

.btn.back {
  background-color: #909399;
  color: #fff;
}

.pagination {
  display: flex;
  justify-content: center;
  gap: 10px;
  margin: 20px 0;
}

.btn {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  background-color: #4a7ac7;
  color: #fff;
  transition:  0.3s;
}

.btn:hover {
  opacity: 0.9;
}

.btn:disabled {
  background-color: #ccc;
  cursor: not-allowed;
  opacity: 0.6;
}

.add-question {
  background-color: #52c41a;
  display: block;
  margin: 20px auto 15px;
}

.submit-btn {
  background-color: #4caf50;
  display: block;
  margin: 20px auto 0;
}
</style>