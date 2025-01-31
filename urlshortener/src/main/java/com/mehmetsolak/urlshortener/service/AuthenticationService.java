package com.mehmetsolak.urlshortener.service;

import com.mehmetsolak.urlshortener.core.result.Result;
import com.mehmetsolak.urlshortener.dto.request.UserLoginRequest;
import com.mehmetsolak.urlshortener.dto.request.UserRegisterRequest;
import com.mehmetsolak.urlshortener.dto.response.UserDtoResponse;
import com.mehmetsolak.urlshortener.entity.User;

public interface AuthenticationService {
    Result<UserDtoResponse> register(UserRegisterRequest userRegisterRequest);
    Result<User> login(UserLoginRequest userLoginRequest);
}
