package com.mehmetsolak.urlshortener.service;

import com.mehmetsolak.urlshortener.core.result.Result;
import com.mehmetsolak.urlshortener.dto.response.UrlDtoResponse;
import com.mehmetsolak.urlshortener.entity.Url;

import java.security.NoSuchAlgorithmException;

public interface UrlService {
    Result<UrlDtoResponse> save(Url url);
    String shortenUrl(String originalUrl) throws NoSuchAlgorithmException;
    Result<UrlDtoResponse> findByShortUrl(String shortUrl);
}
