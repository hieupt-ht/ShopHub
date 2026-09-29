import { tokenStore } from "@/app/lib/tokenStore";
import axios, { AxiosError, InternalAxiosRequestConfig } from "axios";
import { error } from "console";
import { config, promise } from "zod";
import { ca } from "zod/locales";

const api = axios.create({
    baseURL : process.env.NEXT_PUBLIC_API_UR,
    headers : {
        "Content-Type" : "application/json",
    },
    withCredentials : true
});
api.interceptors.request.use(
    (config)=>{
        const accessToken = tokenStore.getAccessToken();
        if(accessToken){
            config.headers.Authorization = `Beare ${accessToken}`;
        }
        return config;
    },
    (error)=>{
        return Promise.reject(error);
    }
)
api.interceptors.response.use(
    (response) =>{
        return response;
    },
    async (error : AxiosError)=>{
        const originalRequest = 
        error.config as InternalAxiosRequestConfig & {
            // mỗi lần server trả token mới, request gửi token mới nếu không hợp lệ thì ngắt luôn
            // tránh vòng lặp vô hạn yêu cầu server cấp token mới
            _retry?: boolean
        }
        if(error.status != 401){
            return Promise.reject(error);
        }
        if(originalRequest._retry){
            tokenStore.clearTokens();
            window.location.href = "/login";
        }
        originalRequest._retry = true;
        try{
            const refresh = tokenStore.getRefreshToken();
            const response = await api.post<LoginResponse>(
                "/refresh",
                {
                    refresh
                }
            );
            const {accesstoken, refreshtoken} = response.data.result
            tokenStore.setTokens(accesstoken, refreshtoken);
            originalRequest.headers.Authorization = `Bearer ${accesstoken}`;
            return api(originalRequest);
        }catch(refreshError){
            tokenStore.clearTokens();
            window.location.href = "/login";
            return Promise.reject(refreshError);
        }
    }
)
export default api;