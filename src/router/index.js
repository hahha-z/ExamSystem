import VueRouter from "vue-router"
//引入组件
//登陆页面
import MyStudent from "../views/Login/MyStudent.vue"
import MyTeacher from "../views/Login/MyTeacher.vue"
//学生端
import StudentPage from "@/views/Student/StudentPage.vue"
import StudentInfo from "@/views/Student/StudentInfo.vue"
import ExamInfo from "@/views/Student/ExamInfo.vue"
//教师端
import TeacherPage from "@/views/Teacher/TeacherPage.vue"
import CourseExams from "@/views/Teacher/CourseExams.vue"
import TeacherInfo from "@/views/Teacher/TeacherInfo.vue"
import SubQues from "@/views/Teacher/SubQues.vue"
import QuestionEditor from "@/components/QuestionEditor.vue"
import TestStatus from "@/views/Teacher/TestStatus.vue"
//考试页面
import ToExam from "@/views/ToExam/ToExam.vue"

//创建并暴露一个路由器
const router = new VueRouter({
    routes:[
        {
            path:'/login/student',
            name:'mystudent',
            component:MyStudent
        },
        {
            path:'/login/teacher',
            name:'myteacher',
            component:MyTeacher
        },
        {
            path: '/student',
            component:StudentPage, 
            redirect: '/student/studentinfo',
            // meta:{requireRole:1},
            children: [
                {
                    path:'studentinfo',
                    name:'StudentInfo',
                    component:StudentInfo,
                },
                {
                    path:'examinfo',
                    name:'ExamInfo',
                    component:ExamInfo,
                },
            ]
        },
        {
            path: '/teacher',
            component:TeacherPage,
            redirect: '/teacher/teacherinfo',
            // meta:{requireRole:0},
            children: [
                {
                    path:'teacherinfo',
                    name:'TeacherInfo',
                    component:TeacherInfo
                },
                {
                    path:'courseexams',
                    name:'CourseExams',
                    component:CourseExams
                },
                {
                    path:'subques',
                    name:'SubQues',
                    component:SubQues,
                    children:[
                        {
                            path:'questioneditor',
                            name:'QuestionEditor',
                            component:QuestionEditor,
                        }
                    ]
                },
                {
                    path:'teststatus',
                    name:'TestStatus',
                    component:TestStatus
                },
            ]
        },
        {
            path:'/toexam/student',
            name:'ToExam',
            component:ToExam,
        }
    ]
})
//全局前置守卫(去读localStorage)
router.beforeEach((to,from,next) => {
    const roleId = localStorage.getItem('roleId')
    const token = localStorage.getItem('token')

    //访问登录页放行
    if (to.path === '/') return next();
    //校验权限
    if (to.meta.requireRole !== undefined){
        //未登录或无token
        if(!token || !roleId){
            next('/');
        }else if( Number(roleId) === to.meta.requireRole ){
            //身份匹配，放行
            next();
        }else{
            //不匹配,警告
            next(alert('无权限'));
        }
    }else{
        //无需权限页面，放行
        next()
    }
})

export default router

