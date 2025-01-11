package com.arterp.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Integer code;

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(true, "Success", data, 200);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(true, message, data, 200);
    }

    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<>(false, message, null, 500);
    }

    public static <T> BaseResponse<T> error(String message, Integer code) {
        return new BaseResponse<>(false, message, null, code);
    }
} 