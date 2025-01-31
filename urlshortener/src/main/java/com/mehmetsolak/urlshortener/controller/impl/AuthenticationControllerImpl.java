package com.mehmetsolak.urlshortener.controller.impl;

import com.mehmetsolak.urlshortener.controller.AuthenticationController;
import com.mehmetsolak.urlshortener.core.result.FailureResult;
import com.mehmetsolak.urlshortener.core.result.Result;
import com.mehmetsolak.urlshortener.core.result.SuccessResult;
import com.mehmetsolak.urlshortener.dto.request.UserLoginRequest;
import com.mehmetsolak.urlshortener.dto.request.UserRegisterRequest;
import com.mehmetsolak.urlshortener.dto.response.TokenCheckResponse;
import com.mehmetsolak.urlshortener.dto.response.UserLoginResponse;
import com.mehmetsolak.urlshortener.dto.response.UserDtoResponse;
import com.mehmetsolak.urlshortener.entity.User;
import com.mehmetsolak.urlshortener.service.AuthenticationService;
import com.mehmetsolak.urlshortener.service.TokenService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping
public final class AuthenticationControllerImpl implements AuthenticationController {
    private final AuthenticationService authenticationService;
    private final TokenService tokenService;

    public AuthenticationControllerImpl(
            AuthenticationService authenticationService,
            TokenService tokenService
    ) {
        this.authenticationService = authenticationService;
        this.tokenService = tokenService;
    }

    @Override
    public Result<UserDtoResponse> register(
            @RequestBody UserRegisterRequest userRegisterRequest
    ) {
        return authenticationService.register(userRegisterRequest);
    }

    @Override
    public Result<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        Result<User> result = authenticationService.login(userLoginRequest);
        if(!result.isSuccess()) {
            return new FailureResult<>(result.getMessage());
        }

        String token = tokenService.generateToken(result.getData());
        return new SuccessResult<>(new UserLoginResponse(token, result.getData().toDto()));
    }

    @Override
    public Result<TokenCheckResponse> tokenCheck() {
        return new SuccessResult<>(
                new TokenCheckResponse(
                        new Date(System.currentTimeMillis())
                )
        );
    }
}
