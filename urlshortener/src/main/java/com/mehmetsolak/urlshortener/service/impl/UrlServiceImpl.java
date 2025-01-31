package com.mehmetsolak.urlshortener.service.impl;

import com.mehmetsolak.urlshortener.constants.Messages;
import com.mehmetsolak.urlshortener.core.result.FailureResult;
import com.mehmetsolak.urlshortener.core.result.Result;
import com.mehmetsolak.urlshortener.core.result.SuccessResult;
import com.mehmetsolak.urlshortener.dto.response.UrlDtoResponse;
import com.mehmetsolak.urlshortener.entity.Url;
import com.mehmetsolak.urlshortener.repository.UrlRepository;
import com.mehmetsolak.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    @Value("${url-short.base62}")
    private String base62;

    private static final String DIGEST_ALGORITHM = "SHA-256";

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    @CachePut(value = "shortUrl", key = "#url.shortUrl")
    public Result<UrlDtoResponse> save(Url url) {
        return new SuccessResult<>(urlRepository.save(url).toDto());
    }

    @Override
    public String shortenUrl(String originalUrl) throws NoSuchAlgorithmException {
        String shortUrl;
        do {
            final String hash = generateHash(originalUrl);
            shortUrl = base62Encode(hash);
        } while(urlRepository.findByShortUrl(shortUrl).isPresent());
        return shortUrl;
    }

    @Override
    @Cacheable(value = "shortUrl", key = "#shortUrl")
    public Result<UrlDtoResponse> findByShortUrl(String shortUrl) {
        Optional<Url> url = urlRepository.findByShortUrl(shortUrl);
        if (url.isPresent()) {
            return new SuccessResult<>(url.get().toDto());
        }
        return new FailureResult<>(Messages.URL_NOT_FOUND);
    }

    private String generateHash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(DIGEST_ALGORITHM);
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    private String base62Encode(String hash) {
        long value = Math.abs(hash.hashCode());
        StringBuilder shortCode = new StringBuilder();

        while(value > 0) {
            shortCode.append(base62.charAt((int) (value % 62)));
            value /= 62;
        }
        return shortCode.toString();
    }
}
