import api from "../api/axious";
import type { UserResponse } from "@/app/type/user/UserResponse";

export const UserService = {
    async getCurrentUser(): Promise<UserResponse> {
        const response = await api.get("/v1/user/me");
        return response.data.result as UserResponse;
    }
};
