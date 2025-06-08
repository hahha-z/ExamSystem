<template>
  <div class="question-container">
    <h2>{{ currentQuestion.question }}</h2>
    <div class="options">
        <!-- 选择的答案有样式selected -->
      <div 
        v-for="(option, index) in currentQuestion.options" 
        :key="index"
        class="option"
        :class="{ selected: selectedAnswer === option }"
        @click="selectAnswer(option)"
      >
      <!-- :class="{ selected: selectedAnswer ===  String.fromCharCode(65 + index) }"
      @click="selectAnswer(String.fromCharCode(65 + index)) -->

      <!-- A，B，C，D -->
        {{ String.fromCharCode(65 + index) }}. {{ option }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    currentQuestion: {
      type: Object,
      required: true
    },
    selectedAnswer: {
      type: String,
      default: ''
    }
  },
  methods: {
    // 触发自定义事件，子向父传
    selectAnswer(answer) {
      this.$emit('answer-selected', answer);
    }
  }
};
</script>

<style>
.question-container {
  margin-bottom: 20px;
}

.options {
  margin-top: 10px;
}

.option {
  padding: 10px;
  margin: 5px 0;
  border: 1px solid #ccc;
  cursor: pointer;
}

.option:hover {
  background-color: #f0f0f0;
}

.option.selected {
  background-color: #e0e0e0;
}
</style>