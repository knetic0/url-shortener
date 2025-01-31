package com.mehmetsolak.urlshortener.core.result;

public final class SuccessResult<T> extends Result<T> {
    public SuccessResult() {
        super(true);
    }

    public SuccessResult(T data) {
        super(true, data);
    }

    public SuccessResult(String message) {
        super(true, message);
    }

    public SuccessResult(String message, T data) {
        super(true, message, data);
    }
}
