package com.mehmetsolak.urlshortener.service.impl;

import com.mehmetsolak.urlshortener.constants.Messages;
import com.mehmetsolak.urlshortener.core.result.FailureResult;
import com.mehmetsolak.urlshortener.core.result.Result;
import com.mehmetsolak.urlshortener.core.result.SuccessResult;
import com.mehmetsolak.urlshortener.dto.request.UserLoginRequest;
import com.mehmetsolak.urlshortener.dto.request.UserRegisterRequest;
import com.mehmetsolak.urlshortener.dto.response.UserDtoResponse;
import com.mehmetsolak.urlshortener.entity.User;
import com.mehmetsolak.urlshortener.service.AuthenticationService;
import com.mehmetsolak.urlshortener.service.PasswordService;
import com.mehmetsolak.urlshortener.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public final class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordService passwordService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(
            PasswordService passwordService,
            UserService userService,
            AuthenticationManager authenticationManager
    ) {
        this.passwordService = passwordService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Result<UserDtoResponse> register(
            UserRegisterRequest userRegisterRequest
    ) {
        if(userService.existsByEmailOrUsername(
                userRegisterRequest.getEmail(),
                userRegisterRequest.getUsername())
        ) {
            return new FailureResult<>(Messages.USER_ALREADY_EXISTS);
        }

        User mappedUser = userRegisterRequest.toUser(
                passwordService.encryptPassword(userRegisterRequest.getPassword())
        );
        User user = userService.save(mappedUser).getData();

        return new SuccessResult<>(
                Messages.USER_REGISTERED,
                user.toDto()
        );
    }

    @Override
    public Result<User> login(UserLoginRequest userLoginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getUsername(), userLoginRequest.getPassword()
                )
        );
        String username = userLoginRequest.getUsername();

        Result<User> result = userService.findByEmailOrUsername(username, username);
        if(!result.isSuccess()) {
            return new FailureResult<>(result.getMessage());
        }

        return new SuccessResult<>(result.getData());
    }
}
