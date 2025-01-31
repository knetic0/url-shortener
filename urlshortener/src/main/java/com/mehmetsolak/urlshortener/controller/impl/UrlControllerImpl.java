package com.mehmetsolak.urlshortener.controller.impl;

import com.mehmetsolak.urlshortener.controller.UrlController;
import com.mehmetsolak.urlshortener.core.result.Result;
import com.mehmetsolak.urlshortener.dto.request.UrlCreateRequest;
import com.mehmetsolak.urlshortener.dto.response.UrlDtoResponse;
import com.mehmetsolak.urlshortener.entity.Url;
import com.mehmetsolak.urlshortener.entity.User;
import com.mehmetsolak.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping
public class UrlControllerImpl implements UrlController {
    private final UrlService urlService;

    public UrlControllerImpl(UrlService urlService) {
        this.urlService = urlService;
    }

    @Override
    public Result<UrlDtoResponse> create(
            @RequestBody UrlCreateRequest urlCreateRequest,
            HttpServletRequest request
    ) throws NoSuchAlgorithmException {
        final User user = (User) request.getAttribute("user");
        final String shortenUrl = urlService.shortenUrl(urlCreateRequest.getOriginalUrl());
        final Url url = urlCreateRequest.toUrl(shortenUrl, user);
        return urlService.save(url);
    }

    @Override
    public void redirectToUrl(
            @PathVariable String shortUrl,
            HttpServletResponse response
    ) throws IOException {
        Result<UrlDtoResponse> url = urlService.findByShortUrl(shortUrl);
        if(url.isSuccess()) {
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            response.sendRedirect(url.getData().getOriginalUrl());
        }
    }
}
