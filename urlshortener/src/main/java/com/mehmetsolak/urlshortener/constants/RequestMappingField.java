package com.mehmetsolak.urlshortener.constants;

public final class RequestMappingField {
    public final static String BASE = "/api";
    public final static String PROTECTED_ROUTE = BASE + "/protected";
    public final static String AUTH = BASE + "/auth";
    public final static String REGISTER = AUTH + "/register";
    public final static String LOGIN = AUTH + "/login";
    public final static String TOKEN_CHECK = PROTECTED_ROUTE + "/token-check";
    public final static String USER_GET_ALL = PROTECTED_ROUTE + "/user/all";
    public final static String URL_CREATE  = PROTECTED_ROUTE + "/url/create";
    public final static String REDIRECT_TO_URL = "/{shortUrl}";
}
