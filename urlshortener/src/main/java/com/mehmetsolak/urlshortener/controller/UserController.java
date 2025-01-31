package com.mehmetsolak.urlshortener.controller;

import com.mehmetsolak.urlshortener.constants.RequestMappingField;
import com.mehmetsolak.urlshortener.core.result.Result;
import com.mehmetsolak.urlshortener.dto.response.UserDtoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

public interface UserController {
    @GetMapping(RequestMappingField.USER_GET_ALL)
    Result<List<UserDtoResponse>> getAll();
}
