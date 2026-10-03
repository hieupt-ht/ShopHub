import axios from "axios"

export const UserService = {
    async getCurrentUser(){
        try {
            const response = await axios.get("/v1/users/me");
            return response.data.result as UserResponse;
        } catch (error) {
        }
    }
} 