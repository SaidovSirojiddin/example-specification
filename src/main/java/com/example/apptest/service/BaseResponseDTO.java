package com.example.apptest.service;

public record BaseResponseDTO<T>(Integer code, String id, T result) {
    public static <T> BaseResponseDTO<T> ok(String id, T result) {
        return new BaseResponseDTO<>(null, id, result);
    }

    public static <T> BaseResponseDTO<T> ok(T result) {
        return new BaseResponseDTO<>(null, null, result);
    }

    public static <T> BaseResponseDTO<T> fail(Integer code, T result) {
        return new BaseResponseDTO<>(code, null, result);
    }

    public static <T> BaseResponseDTO<T> fail(T result) {
        return new BaseResponseDTO<>(null, null, result);
    }

    public static <T> BaseResponseDTO<T> fail(String id, T result) {
        return new BaseResponseDTO<>(null, id, result);
    }
}
