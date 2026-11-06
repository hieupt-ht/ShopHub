interface User{
    id: number;
    username: string;
    fullname: string;
}
export interface LoginResponse {
    accesstoken: string,
    refreshToken: string,
    user?: User
}
