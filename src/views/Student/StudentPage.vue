<template>
  <div id="student-page">
    <BaseTem :navItems="studentNav" class="student-base"></BaseTem>
  </div>
</template>

<script>
import BaseTem from '@/components/BaseTem.vue';

export default {
    name:'StudentPage',
    components:{BaseTem},
    data(){
        return {
            studentNav:[
                {path:'/student/studentinfo',title:'学生信息'},
                {path:'/student/examinfo',title:'我的考试'},
            ]
        }
    }
}
</script>

<style>

</style>