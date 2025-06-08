import axios from "axios";
import router from '../router';

//创建axios实例
const service = axios.create({
    //后端？基础路径:http://服务器ip/端口号
    // baseURL:'http://192.168.43.247:8090',
    baseURL:'api',
    timeout:10000,//请求超时时间
    headers: {
        'Content-Type': 'application/json;charset=UTF-8'
    }
})

//请求拦截器：添加token到请求头
service.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token')
        if(token){
            config.headers.Authorization = `Bearer ${token}` 
        }
        return config
    },
    error => {
        console.log('请求拦截错误：',error)
        return Promise.reject(error)
    }
)

//响应拦截器：处理token过期、业务错误
service.interceptors.response.use(
    response => {
        const res = response.data
        //后端成功码
        if(res.code !== 200 ){
            alert(res.message || '请求失败')
            //401为登录失效，后端？
            if(res.code === 401){
                localStorage.removeItem('token')
                // localStorage.removeItem('roleId')
                localStorage.removeItem('stuId')
                localStorage.removeItem('workId')
                router.push('/')
            }
            return Promise.reject(new Error(res.message || '请求失败'))
        }else{
            return res
        }
    },
    error => {
        console.error('响应错误',error)
        // 网络错误提示
    let message = '网络错误，请稍后再试';
    if (error.response && error.response.data) {
      message = error.response.data.message || message;
    }
    alert(message);

    // token过期时跳转登录页（后端返回401状态码）,hhhhh后端
    // if (error.response && error.response.status === 401) {
    if (error.response) {
      localStorage.removeItem('token');
      localStorage.removeItem('stuId');
      localStorage.removeItem('workId');
    //   router.push('/');
    }
    return Promise.reject(error);
  }
);

// 导出封装好的request工具
export default service;