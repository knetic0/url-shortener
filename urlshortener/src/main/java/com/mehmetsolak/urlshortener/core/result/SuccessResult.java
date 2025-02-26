package com.mehmetsolak.urlshortener.core.result;

import java.util.Map;

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

    public SuccessResult(String message, Map<String, String> errors) {
        super(true, message, errors);
    }
}
