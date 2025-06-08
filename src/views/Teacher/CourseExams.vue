<template>
  <div class="course-table-container">
    <h2 class="table-title">课程列表</h2>
    <table class="course-table">
      <thead>
        <tr>
          <th>课程名称</th>
          <th>课程时间</th>
          <th>课程班级</th>
        </tr>
      </thead>
      <tbody>
        <!-- 接口③，data里courses数组，id,name,time, -->
        <tr v-for="course in courseList" :key="course.id" class="table-row">
          <td class="table-cell">{{ course.name }}</td>
          <td class="table-cell">{{ course.time }}</td>
          <td class="table-cell">{{ course.class }}</td>
        </tr>
      </tbody>
    </table>
    <div v-if="courseList.length === 0" class="empty-tip">暂无课程数据</div>
  </div>
</template>

<script>
// import request from '../../utils/request'

export default {
  data(){
    return {
      courseData: {},
      courseList: []
    }
  },
  mounted(){
    // this.fetchCourseData()
    // 假数据
    this.courseData = {
      code: 200,
      message:"获取数据成功",
      data: {
        courses: [
          { id: 1, name: "语文", time: "2025-06-05", class: "高三1班" },
          { id: 2, name: "数学", time: "2025-06-06", class: "高三1班" }
        ],
        total: 2
      }
    },
    this.courseList = this.courseData.data.courses
  },
  methods: {
//     async fetchCourseData() {
//       try {
//         // 从localStorage获取教师ID
//            const workId = localStorage.getItem('workId')             
//         // if (!workId) {
//         //   throw new Error('未找到教师ID，请重新登录')
//         // }

          // 发送请求
//         const res = await request.post('/course/selectC',{
//           workId
//         })
//         this.courseData = res.data
//         this.courseList = this.courseData.data.courses || []
//       } catch (err) {
//         console.error('数据获取失败', err)
//       }
//     },
  }
}
</script>

<style scoped>
.course-table-container {
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.05);
}
.table-title {
  font-size: 18px;
  font-weight: 600;
  margin-bottom: 15px;
  color: #333;
}
.course-table {
  width: 100%;
  border-collapse: collapse;
}
.course-table th {
  background-color: #f5f7fa;
  font-weight: 500;
  padding: 12px 15px;
  text-align: left;
  border-bottom: 2px solid #e5e9f2;
  color: #4e5969;
}
.course-table td {
  padding: 12px 15px;
  border-bottom: 1px solid #ebf0f7;
  color: #333;
  font-size: 14px;
}
.table-row:hover {
  background-color: #f9fafc;
}
.empty-tip {
  padding: 30px 0;
  text-align: center;
  color: #909399;
  font-size: 14px;
}
</style>