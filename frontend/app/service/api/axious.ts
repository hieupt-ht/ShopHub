import { tokenStore } from "@/app/lib/tokenStore";
import axios, { AxiosError, InternalAxiosRequestConfig } from "axios";

const api = axios.create({
    baseURL: process.env.NEXT_PUBLIC_API_URL,
    headers: {
        "Content-Type": "application/json",
    },
    withCredentials: true,
});

api.interceptors.request.use(
    (config) => {
        const accessToken = tokenStore.getAccessToken();
        if (accessToken) {
            config.headers.Authorization = `Bearer ${accessToken}`;
        }
        return config;
    },
    (error) => Promise.reject(error),
);

api.interceptors.response.use(
    (response) => response,
    async (error: AxiosError) => {
        const originalRequest =
            error.config as InternalAxiosRequestConfig & {
                _retry?: boolean;
            };

        if (error.status !== 401 || !originalRequest) {
            return Promise.reject(error);
        }

        if (originalRequest._retry) {
            tokenStore.clearTokens();
            //window.location.href = "/login";
            return Promise.reject(error);
        }

        originalRequest._retry = true;
        try {
            const refresh = tokenStore.getRefreshToken();
            const response = await api.post<LoginResponse>("/refresh", {
                refresh,
            });
            const { accesstoken, refreshtoken } = response.data.result;
            tokenStore.setTokens(accesstoken, refreshtoken);
            originalRequest.headers.Authorization = `Bearer ${accesstoken}`;
            return api(originalRequest);
        } catch (refreshError) {
            tokenStore.clearTokens();
            //window.location.href = "/login";
            return Promise.reject(refreshError);
        }
    },
);

export default api;
