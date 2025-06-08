<template>
  <div id="teacher-page">
    <div id="teacher-page">
        <!-- 给子组件传参 -->
        <BaseTem :navItems="teacherNav" class="teacher-base"></BaseTem>
    </div>
  </div>
</template>

<script>
import BaseTem from '@/components/BaseTem.vue';

export default {
    name:'TeacherPage',
    components:{BaseTem},
    data(){
        return {
            teacherNav:[
                {path:'/teacher/teacherinfo',title:'老师信息'},
                {path:'/teacher/courseexams',title:'我的课程'},
                {path:'/teacher/subques',title:'试卷题目'},
                {path:'/teacher/teststatus',title:'考试情况'},
            ]
        }
    }
}
</script>

<style>

</style>