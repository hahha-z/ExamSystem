<template>
  <div class="question-editor">
    <!-- 题目编辑区域 -->
    <div class="edit-container">
      <input 
        v-model="editedQuestion.question" 
        placeholder="输入题目内容" 
        class="question-input"
      />
      
      <div class="options-edit">
        <input 
          v-for="(opt, index) in editedQuestion.options" 
          :key="index"
          v-model="editedQuestion.options[index]"
          placeholder="选项内容"
          class="option-input"
        />
        <button @click="addOption" class="btn add">+ 新增选项</button>
        <button v-if="editedQuestion.options.length > 2" @click="removeOption" class="btn remove">- 移除选项</button>
      </div>
    </div>

    <!-- 操作按钮 -->
    <div class="action-buttons">
      <button class="btn save" @click="saveQuestion">保存修改</button>
      <button class="btn cancel" @click="cancelEdit">取消编辑</button>
      <button class="btn danger" @click="deleteQuestion">删除题目</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'QuestionEditor',
  props: {
    questionData: {
      type: Object,
      required: true,
      default: () => ({
        id: '',
        question: '',
        options: []
      })
    }
  },
  data() {
    return {
      editedQuestion: { ...this.questionData }, // 编辑副本
      originalQuestion: null // 原始题目，用于取消编辑
    };
  },
  created() {
    this.originalQuestion = { ...this.questionData };
  },
  watch: {
    // 替换改动的值
    questionData(newVal) {
      this.editedQuestion = { ...newVal };
      this.originalQuestion = { ...newVal };
    }
  },
  methods: {
    // 新增选项
    addOption() {
      this.editedQuestion.options.push('');
    },

    // 移除选项
    removeOption() {
      if (this.editedQuestion.options.length > 2) {
        this.editedQuestion.options.pop();
      }
    },

    // 保存修改
    saveQuestion() {
      this.$emit('update-question', this.editedQuestion);
    },

    // 取消编辑
    cancelEdit() {
      this.editedQuestion = { ...this.originalQuestion };
      this.$emit('cancel-editing');
    },

    // 删除题目
    deleteQuestion() {
      this.$emit('delete-question', this.editedQuestion.id);
    }
  }
};
</script>

<style scoped>
.question-editor {
  margin-bottom: 24px;
  padding: 24px;
  border: 1px solid #ebeef5;
  border-radius: 8px;
  background: #ffffff;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.04);
  transition: all 0.3s ease;
}
 
.question-editor:hover {
  box-shadow: 0 2px 16px rgba(0, 0, 0, 0.08);
}
 
.edit-container {
  margin-bottom: 16px;
}
 
.question-input {
  width: 100%;
  padding: 12px 16px;
  margin-bottom: 16px;
  border: 1px solid #dcdfe6;
  border-radius: 6px;
  font-size: 16px;
  transition: border-color 0.2s;
}
 
.question-input:focus {
  border-color: #409eff;
  outline: none;
}
 
.options-edit {
  margin-top: 16px;
}
 
.option-input {
  width: 100%;
  padding: 10px 12px;
  margin-bottom: 8px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  box-sizing: border-box;
  transition: border-color 0.2s;
}
 
.option-input:focus {
  border-color: #409eff;
  outline: none;
}
 
.action-buttons {
  display: flex;
  gap: 12px;
  margin-top: 20px;
  flex-wrap: wrap;
}
 
.btn {
  padding: 8px 16px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 14px;
  font-weight: 500;
  transition: all 0.2s ease;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: 80px;
}
 
.btn:hover {
  opacity: 0.9;
  transform: translateY(-1px);
}
 
.btn.save {
  background-color: #67c23a;
  color: #fff;
}
 
.btn.save:hover {
  background-color: #85ce61;
}
 
.btn.cancel {
  background-color: #909399;
  color: #fff;
}
 
.btn.cancel:hover {
  background-color: #a6a9ad;
}
 
.btn.add {
  background-color: #409eff;
  color: #fff;
}
 
.btn.add:hover {
  background-color: #66b1ff;
}
 
.btn.remove {
  background-color: #e6a23c;
  color: #fff;
}
 
.btn.remove:hover {
  background-color: #ebb563;
}
 
.btn.danger {
  background-color: #f56c6c;
  color: #fff;
}
 
.btn.danger:hover {
  background-color: #f78989;
}
</style>

