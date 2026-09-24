import { tokenStore } from "@/app/lib/tokenStore";
import axios from "axios";
import { error } from "console";
import { promise } from "zod";

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
export default api;