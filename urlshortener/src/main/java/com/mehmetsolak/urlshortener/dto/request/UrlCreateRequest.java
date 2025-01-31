package com.mehmetsolak.urlshortener.dto.request;

import com.mehmetsolak.urlshortener.dto.BaseDto;
import com.mehmetsolak.urlshortener.entity.Url;
import com.mehmetsolak.urlshortener.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class UrlCreateRequest extends BaseDto {
    private String originalUrl;

    public UrlCreateRequest() {}

    public UrlCreateRequest(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Url toUrl(String shortUrl, User user) {
        return new Url(shortUrl, originalUrl, user);
    }
}
