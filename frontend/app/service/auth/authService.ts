import { tokenStore } from "@/app/lib/tokenStore";
import api from "../api/axious"
import { LoginRequest } from "@/app/type/auth/loginRequest";

export const AuthService = {
    async login(loginData: LoginRequest){
        const response = await api.post(
            "/v1/auth/login",
            loginData
        );
        const {accesstoken, refreshtoken} = response.data.result;
        tokenStore.setTokens(accesstoken, refreshtoken);
        return response.data.result as LoginResponse;
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