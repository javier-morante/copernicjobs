import axios from "axios";
import Cookies from "js-cookie";

const axiosInstance = axios.create({
    headers: {
        "Authorization": "",
    }
})

// Axios instance request interceptor configuration
//  - This configuration is applied in each call of the
//    of the axiosInstance, so for each request is going to be checked
//
//  - In case that the user has a token, then inserts it into the Authorization
//    http header
//
//  - In case that theres no token existance, then redirects the user to the
//    signIn page
axiosInstance.interceptors.request.use(
    (config) => {
        const token = Cookies.get('token')

        if(token) {
            config.headers.Authorization = "Bearer " + token
            return config
        }
        
        config.headers.Authorization = ""
        window.location.href = '/sign-in'
        return config
    }
)

axiosInstance.interceptors.response.use(
    (response) => response,
    (error) => {
        if(error.response.status === 403) {
            console.log("Error 403: Forbidden")
            //window.location.href = '/sign-in'
        }

        return Promise.reject(error)
    }
)

export default axiosInstance