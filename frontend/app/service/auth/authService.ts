import { tokenStore } from "@/app/lib/tokenStore";
import api from "../api/axious"
import { LoginRequest } from "@/app/type/auth/loginRequest";
import { LoginResponse } from "@/app/type/auth/loginResponse";

export const AuthService = {
    async login(loginData: LoginRequest){
        const response = await api.post(
            "/v1/auth/login",
            loginData
        );
        const { accesstoken, refreshToken: newRefreshToken } = response.data.result;
        tokenStore.setTokens(accesstoken, newRefreshToken);
        return response.data.result as LoginResponse;
    },
    async refresh(){
        const refreshToken = tokenStore.getRefreshToken();
        const response = await api.post(
            "/v1/refresh",
            {
                refreshtoken: refreshToken
            }
        );
        const { accesstoken, refreshToken: newRefreshToken } = response.data.result;
        tokenStore.setTokens(accesstoken, newRefreshToken);
    },
    logout(){
        tokenStore.clearTokens();
    }
}
