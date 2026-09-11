package com.example.shophub.common;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
    int code,
    String message,
    T result
) {
    public static <T> ApiResponse<T> success(T result){
        return new ApiResponse<T>(100, "success", result);
    }
    public static <T> ApiResponse<T> error(int code, String messgae){
        return new ApiResponse<T>(code, messgae, null);
    }
}
