import { tokenStore } from "@/app/lib/tokenStore";
import api from "../api/axious"

export const AuthService = {
    async login(loginData : LoginRequest){
        const response = await api.post<LoginResponse>(
            "/v1/auth/login",
            loginData
        );
        const {accesstoken, refreshtoken} = response.data.result;
        tokenStore.setTokens(accesstoken, refreshtoken);
        return response.data;
    },
    async refresh(){
        const refreshToken = tokenStore.getRefreshToken();
        const response = await api.post<LoginResponse>(
            "/v1/auth/refresh",
            {
                refreshToken
            }
        );
        const {accesstoken, refreshtoken} = response.data.result;
        tokenStore.setTokens(accesstoken, refreshtoken);
    },
    logout(){
        tokenStore.clearTokens();
    }
}