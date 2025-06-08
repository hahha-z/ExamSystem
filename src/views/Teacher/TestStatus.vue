<template>
  <div class="teacher-exam-dashboard">
    <h3>{{ courseNameMap[currentCourse.courseId] }}考试信息</h3>
 
    <!-- 加载状态 -->
    <div v-if="isLoading" class="loading-container">
      <div class="custom-spinner"></div>
      <span class="loading-text">正在加载考试数据...</span>
    </div>
 
    <!-- 无教师权限提示 -->
    <div v-else-if="!workId" class="permission-tip">
      未获取到教师身份信息，请重新登录
    </div>
 
    <!-- 学生表格 -->
    <div v-else>
      <table v-if="currentCourse.studentExamData && currentCourse.studentExamData.length" class="exam-table">
        <thead>
          <tr>
            <th>学生姓名</th>
            <th>学生号</th>
            <th>考试状态</th>
            <th>最近成绩</th>
            <th>所有成绩</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="student in currentCourse.studentExamData" :key="student.studentId">
            <td>{{ student.studentName }}</td>
            <td>{{ student.studentId }}</td>
            <td>
              <span :class="statusClass(student.hastaken)">
                {{ examStatusText(student.hastaken) }}
              </span>
            </td>
            <td>{{ formatScore(student.latestScore) }}</td>
            <td>
              <span v-if="student.allScores && student.allScores.length" class="scores-list">
                {{ student.allScores.join('、') }}分
              </span>
              <span v-else>-</span>
            </td>
          </tr>
        </tbody>
      </table>
 
      <!-- 空状态 -->
      <div v-else class="empty-tip">
        {{ courseNameMap[currentCourse.courseId] }} 课程暂无学生考试数据
      </div>
    </div>
  </div>
</template>
 
<script>
export default {
  data() {
    return {
      workId: '20241001', // 教师ID（从本地存储获取）
      currentCourse: {
        courseId: 1, // 默认显示语文课程
        studentExamData: [
          {
            studentId: 2024101,
            studentName: '喜羊羊',
            hastaken: 0,
            latestScore: null,
            allScores: null,
          },
          {
            studentId: 2024102,
            studentName: '美羊羊',
            hastaken: 1,
            latestScore: 90,
            allScores: [80, 85, 90],
          },
          {
            studentId: 2024103,
            studentName: '懒羊羊',
            hastaken: 1,
            latestScore: 95,
            allScores: [80, 90],
          }
        ]
      },
      isLoading: false,
      courseNameMap: { // 课程ID映射表
        1: '语文',
        2: '数学',
        3: '英语',
        4: 'java',
        5: 'javascript',
        6: '化学',
      }
    }
  },
 
  created() {
    // 初始化课程ID
    this.initCourseIdFromRoute();
  },
 
  methods: {
    // 从路由初始化课程ID
    initCourseIdFromRoute() {
      // 这里可以添加从路由获取courseId的逻辑
      // 模拟从路由获取courseId
      const courseId = 1; // 默认值
      if (courseId) {
        this.currentCourse.courseId = Number(courseId);
      }
      // 这里可以调用获取数据的方法
      // this.fetchCourseData();
    },

    // async fetchCourseData() {
//       // 检查教师ID是否存在
//       if (!this.workId) {
//         console.warn('未获取到教师ID，无法加载数据');
//         return;
//       }

//       this.isLoading = true;

//       try {
//         // 发送请求
//         const res = await request.post('/api/exam/course/students', {
//           workId: this.workId,    
//           courseId: this.currentCourse.courseId
//         });

//         if (res.code === 200) {
//           this.currentCourse = {
//             ...this.currentCourse,
//             studentExamData: this.processStudentData(res.data.students)
//           }
//         }
//          else {
//           alert(res.message || '获取学生考试数据失败');
//         }
//       } catch (err) {
//         console.error('数据加载失败:', err);
//         alert('网络错误，请稍后重试');
//       } finally {
//         this.isLoading = false;
//       }
//     },
 
    examStatusText(hastaken) {
      return hastaken === 1 ? '已考' : '未考';
    },
 
    statusClass(hastaken) {
      return {
        'status-completed': hastaken === 1,
        'status-pending': hastaken === 0
      };
    },
 
    formatScore(score) {
      return score !== null ? `${score}分` : '-';
    }
  }
}
</script>

<style scoped>
.teacher-exam-dashboard {
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  min-height: 80vh;
}

.loading-container {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px 0;
  gap: 12px;
}

.custom-spinner {
  width: 24px;
  height: 24px;
  border: 3px solid #409eff;
  border-top-color: transparent;
  border-radius: 50%;
  animation: spin 0.8s linear infinite;
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}

.exam-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
  background: #fff;
  box-shadow: 0 2px 12px rgba(0,0,0,0.06);
}

.exam-table th {
  background: #f8f9fa;
  padding: 14px;
  text-align: left;
  font-weight: 500;
  color: #2c3e50;
  border-bottom: 2px solid #ebf0f7;
}

.exam-table td {
  padding: 12px 14px;
  color: #606266;
  border-bottom: 1px solid #ebf0f7;
  transition:  0.2s;
}

.exam-table tr:hover td {
  background: #f8f9fa;
}

.status-completed {
  color: #67c23a;
  font-weight: 500;
}

.status-pending {
  color: #909399;
  font-style: italic;
}

.scores-list {
  display: inline-block;
  max-width: 200px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  vertical-align: middle;
}

.empty-tip {
  padding: 40px;
  text-align: center;
  color: #909399;
  font-size: 14px;
}
</style>
