interface User{
    id: number;
    username: string;
    fullname: string;
}
interface LoginResponse{
    code: number,
    message: string,
    result:
        {
            accesstoken: string,
            refreshtoken: string
            user: User
        }
}