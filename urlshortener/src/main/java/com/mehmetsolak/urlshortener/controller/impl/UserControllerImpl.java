package com.mehmetsolak.urlshortener.controller.impl;

import com.mehmetsolak.urlshortener.controller.UserController;
import com.mehmetsolak.urlshortener.core.result.Result;
import com.mehmetsolak.urlshortener.core.result.SuccessResult;
import com.mehmetsolak.urlshortener.dto.response.UserDtoResponse;
import com.mehmetsolak.urlshortener.entity.User;
import com.mehmetsolak.urlshortener.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public final class UserControllerImpl implements UserController {
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Result<List<UserDtoResponse>> getAll() {
        List<UserDtoResponse> response = new ArrayList<>();

        Result<List<User>> result = userService.findAll();
        for(User user : result.getData()) {
            response.add(user.toDto());
        }

        return new SuccessResult<>(response);
    }
}
