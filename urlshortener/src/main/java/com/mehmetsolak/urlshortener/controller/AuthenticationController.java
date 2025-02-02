package com.mehmetsolak.urlshortener.controller;

import com.mehmetsolak.urlshortener.constants.RequestMappingField;
import com.mehmetsolak.urlshortener.core.result.Result;
import com.mehmetsolak.urlshortener.dto.request.UserLoginRequest;
import com.mehmetsolak.urlshortener.dto.request.UserRegisterRequest;
import com.mehmetsolak.urlshortener.dto.response.TokenCheckResponse;
import com.mehmetsolak.urlshortener.dto.response.UserLoginResponse;
import com.mehmetsolak.urlshortener.dto.response.UserDtoResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationController {
    @PostMapping(RequestMappingField.REGISTER)
    Result<UserDtoResponse> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest);

    @PostMapping(RequestMappingField.LOGIN)
    Result<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest);

    @PostMapping(RequestMappingField.TOKEN_CHECK)
    Result<TokenCheckResponse> tokenCheck();
}
