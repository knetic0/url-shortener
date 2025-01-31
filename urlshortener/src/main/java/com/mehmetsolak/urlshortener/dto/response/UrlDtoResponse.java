package com.mehmetsolak.urlshortener.dto.response;

import com.mehmetsolak.urlshortener.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class UrlDtoResponse extends BaseDto {
    private String shortUrl;
    private String originalUrl;

    public UrlDtoResponse() {}

    public UrlDtoResponse(String shortUrl, String originalUrl) {
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
    }
}
