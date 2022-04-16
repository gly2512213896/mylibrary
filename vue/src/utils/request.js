import axios from 'axios'
import router from "../router";
import {createRouter as $router} from "vue-router";

const request = axios.create({
    baseURL: '/api',
    timeout: 5000
})


request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    // config.headers['token'] = user.token;
    let userJson=sessionStorage.getItem("admin");
    if(!userJson){
        router.push("/login");
    }
    return config
}, error => {
    return Promise.reject(error)
});

request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }

        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error)
        return Promise.reject(error)
    }
)


export default request

