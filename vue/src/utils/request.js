import axios from "axios";

const request = axios.create({
    baseURL: import.meta.env.VITE_BASE_URL,
    timeout: 30000  // 后台接口超时时间
})

// 设置请求头
const res = await axios.post('http://localhost:9090/order', payload, {
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    }
})

export default request


