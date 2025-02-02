package com.mehmetsolak.urlshortener.core.result;

import java.util.Map;

public final class FailureResult<T> extends Result<T> {
    public FailureResult() {
        super(false);
    }

    public FailureResult(T data) {
        super(false, data);
    }

    public FailureResult(String message) {
        super(false, message);
    }

    public FailureResult(String message, T data) {
        super(false, message, data);
    }

    public FailureResult(String message, Map<String, String> errors){
        super(false, message, errors);
    }
}
