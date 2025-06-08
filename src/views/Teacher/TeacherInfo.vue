<template>
  <div id="teacher-info" class="teacher-info-container">
    <h2>教师个人信息</h2>
    <div class="info-item">
      <label class="info-label">工号:</label>
      <span class="info-value">{{ teacherInfo.workId }}</span>
    </div>
    <div class="info-item">
      <label class="info-label">姓名:</label>
      <span class="info-value">{{ teacherInfo.name }}</span>
    </div>
    <div class="info-item">
      <label class="info-label">性别:</label>
      <span class="info-value">{{ teacherInfo.sex }}</span>
    </div>
    <div class="info-item">
      <label class="info-label">手机号:</label>
      <span class="info-value">{{ teacherInfo.phone }}</span>
    </div>
    <div class="info-item">
      <label class="info-label">所在学院:</label>
      <span class="info-value">{{ teacherInfo.collegeName }}</span>
    </div>
    <div class="info-item">
      <label class="info-label">教授课程:</label>
      <ul class="course-list">
        <!-- 后端数据课程 -->
        <li v-for="(course, index) in teacherInfo.teachCourseId" :key="index">
          {{ course }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
// import request from '../../utils/request'

export default {
  name: 'TeacherInfo',
  data() {
    return {
      // 假数据
        teacherInfo : {
          workId:2024315220222,
          name:'刘老师',
          sex:'女',
          phone:1234567890,
          collegeName:'公共管理学院',
          teachCourseId:['Java','JavaScript'],
        },
      // teacherInfo:{
      //   workId:'',
      //   name:'',
      //   sex:'',
      //   phone:'',
      //   collegeName:'',
      //   teachCourseId:'',
      // }
    };
  },

  created() {
    this.fetchInfo();
  },
  methods: {
    async fetchInfo() {
      try {
        // 从本地保存获得工号以发送请求
        // const workId = localStorage.getItem('workId')       
        // if (!workId) {
        //   throw new Error('未找到教师ID，请重新登录')
        // }
 
        // 发送请求
        // const res = await request ({
        //   url:'/teacher/listPage',
        //   method:'GET',
        //   data:{
        //     param:{
        //       workId:workId
        //     }
        //   }
        // })

        // 接收数据
        // this.teacherInfo = res.data;
        // this.workId = this.teacherInfo.workId
        // this.name = this.teacherInfo.name
        // this.sex = this.teacherInfo.sex
        // this.phone = this.teacherInfo.phone
        // this.collegeName = this.teacherInfo.collegeName
        const n = this.teacherInfo.teachCourseId

        if(n){
          if(n===1){
            this.teachCourseId === '语文'
          }else if(n===2){
            this.teachCourseId === '数学'
          }else if(n===3){
            this.teachCourseId === '英语'
          }else if(n===4){
            this.teachCourseId === 'Java'
          }else if(n===5){
            this.teachCourseId === 'JavaScript'
          }else if(n===6){
            this.teachCourseId === '化学'
          }
        }

        // 给其他路由传课程
        // this.$router.push({
        //   path:'/teststatus',
        //   query:{
        //     courseId: n
        //   }
        // })
        // this.$router.push({
        //   path:'/courseexams',
        //   query:{
        //     courseId: n
        //   }
        // })

      } catch (error) {
        console.error('获取信息失败', error);
      }
    }
  }
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Arial', sans-serif;
}
 
#teacher-info {
  max-width: 700px;
  margin: 30px auto;
  padding: 25px;
  border: 1px solid #e0e0e0;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.06);
  background-color: #fff;
}
 
#teacher-info h2 {
  text-align: center;
  color: #2c3e50;
  margin-bottom: 25px;
  padding-bottom: 12px;
  border-bottom: 1px solid #eee;
  font-size: 1.8rem;
}
 
.info-item {
  display: flex;
  flex-direction: row;      
  align-items: center;      
  justify-content: center;  
  margin-bottom: 18px;
  gap: 15px;                
  flex-wrap: wrap;          
}
 
.info-label {
  color: #666;
  font-weight: 500;
  font-size: 16px;
  text-align: right;        
  min-width: 100px;         
  flex-shrink: 0;          
}
 
.info-value {
  flex: 1;
  font-size: 16px;
  color: #333;
  text-align: left;        
  word-break: break-word;
  padding: 8px 12px;
  background: #f8f9fa;
  border-radius: 6px;
  border: 1px solid #eee;
  min-width: 200px;        
}
 
.course-list {
  list-style: none;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  padding: 0;
  margin: 10px 0 0;
  justify-content: center; 
}
 
.course-list li {
  padding: 8px 16px;
  border: 1px solid #ddd;
  border-radius: 20px;
  background: #fff;
  font-size: 15px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.03);
}
 
</style>