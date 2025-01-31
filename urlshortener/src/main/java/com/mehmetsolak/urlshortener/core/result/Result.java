package com.mehmetsolak.urlshortener.core.result;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private boolean success;
    private String message;
    private T data;

    @Serial
    private static final long serialVersionUID = 1L;

    public Result() { }

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, T data) {
        this(success);
        this.data = data;
    }

    public Result(boolean success, String message) {
        this(success);
        this.message = message;
    }

    public Result(boolean success, String message, T data) {
        this(success, message);
        this.data = data;
    }
}
