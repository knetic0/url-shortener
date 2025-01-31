package com.mehmetsolak.urlshortener.controller;

import com.mehmetsolak.urlshortener.constants.RequestMappingField;
import com.mehmetsolak.urlshortener.core.result.Result;
import com.mehmetsolak.urlshortener.dto.request.UrlCreateRequest;
import com.mehmetsolak.urlshortener.dto.response.UrlDtoResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface UrlController {
    @PostMapping(RequestMappingField.URL_CREATE)
    Result<UrlDtoResponse> create(
            @RequestBody UrlCreateRequest urlCreateRequest,
            HttpServletRequest request
    ) throws NoSuchAlgorithmException;

    @GetMapping(RequestMappingField.REDIRECT_TO_URL)
    void redirectToUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException;
}
