<template>
  <div id="student-info" class="student-info-container">
    <h2>学生个人信息</h2>
    <div class="info-item">
      <label class="info-label">学号:</label>
      <span class="info-value">{{ studentInfo.stuId }}</span>
    </div>
    <div class="info-item">
      <label class="info-label">姓名:</label>
      <span class="info-value">{{ studentInfo.name }}</span>
    </div>
    <div class="info-item">
      <label class="info-label">性别:</label>
      <span class="info-value">{{ studentInfo.sex }}</span>
    </div>
    <div class="info-item">
      <label class="info-label">手机号:</label>
      <input 
        v-model="studentInfo.phone" 
        type="tel" 
        class="info-input"
        placeholder="请输入新手机号"
      >
    </div>
    <div class="info-item">
      <label class="info-label">所在学院:</label>
      <span class="info-value">{{ studentInfo.collegeName }}</span>
    </div>
    <div class="info-item">
      <button 
        @click="savePhoneNumber"
        :disabled="!isPhoneChanged"
        class="save-btn"
      >
        {{ saveLoading ? '保存中...' : '保存修改' }}
      </button>
    </div>
  </div>
</template>
 
<script>
import request from '../../utils/request'
 
export default {
  name: 'StudentInfo',
  data() {
    return {
      studentInfo: {
        stuId: '2024101',
        name: '小红',
        sex: '女',
        phone: '15007126301',
        collegeName: '公共管理学院'
      },
      originalPhone: '', // 原始手机号用于比较
      saveLoading: false
    }
  },
  computed: {
    isPhoneChanged() {
      return this.studentInfo.phone !== this.originalPhone
    }
  },
  created() {
    // this.fetchStudentInfo()
  },
  methods: {
    async fetchStudentInfo() {
      try {
        this.stuId = localStorage.getItem('stuId')
        // if (!stuId) throw new Error('未找到学生ID')
 
        const res = await request ({
          url:'/student/listPage',
          method:'GET',
          data:{
            param:{
              studentId:this.stuId
            }
          }
        })
        
        //data是数组？？
        this.studentInfo = {
          ...res.data[0],
          phone: res.data[0].phone || '' 
        }
        this.originalPhone = this.studentInfo.phone

      } catch (error) {
        console.error('获取信息失败:', error)
        // this.$router.push('/login/student')
       // 获取失败跳转登录,,,这个要保留
      }
    },
 
    async savePhoneNumber() {
      try {
        this.saveLoading = true
        const res = await request.post('/student/updataP', {
          stuId: this.studentInfo.stuId,
          phone: this.studentInfo.phone
        })
 
        if (res.code === 200) {
          this.originalPhone = this.studentInfo.phone
          alert('手机号更新成功！')
        }
      } catch (error) {
        console.error('保存失败:', error)
        alert('手机号更新失败，请重试')
      } finally {
        this.saveLoading = false
      }
    }
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Arial', sans-serif;
}

#student-info {
  max-width: 800px;
  margin: 30px auto;
  padding: 30px;
  border: 1px solid #e0e0e0;
  border-radius: 10px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
  background-color: #fff;
}

#student-info h2 {
  text-align: center;
  color: #2c3e50;
  margin-bottom: 30px;
  padding-bottom: 15px;
  border-bottom: 1px solid #eee;
  font-size: 1.8rem;
}

.student-info-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 0;
}

.info-item {
  display: flex;
  align-items: center;
  margin-bottom: 24px;
}

.info-label {
  min-width: 120px;
  color: #555;
  font-weight: 500;
  font-size: 16px;
}

.info-input {
  flex: 1;
  padding: 14px 16px;
  border: 1px solid #ddd;
  border-radius: 6px;
  font-size: 16px;
  transition: border-color 0.3s;
}

.info-input:focus {
  outline: none;
  border-color: #4a90e2;
  box-shadow: 0 0 0 3px rgba(74, 144, 226, 0.2);
}

.gender-radio {
  display: flex;
  align-items: center;
}

.radio-input {
  margin-right: 8px;
  width: 18px;
  height: 18px;
}

.course-list {
  list-style: none;
  border: 1px solid #ddd;
  border-radius: 6px;
  padding: 14px;
}

.course-list li {
  padding: 10px 0;
  border-bottom: 1px dashed #eee;
}

.course-list li:last-child {
  border-bottom: none;
}

.save-btn {
    width: 50%;
    display: block;
    margin: 0 auto;
    padding: 10px;
    background-color: #2980b9;
    color: white;
    border: none;
    border-radius: 3px;
    cursor: pointer;
}
</style>
