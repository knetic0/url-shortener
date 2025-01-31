package com.mehmetsolak.urlshortener.dto.response;

import com.mehmetsolak.urlshortener.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public final class UserDtoResponse extends BaseDto {
    private String username;

    private String email;

    private Date creationDate;

    private List<UrlDtoResponse> urls;

    public UserDtoResponse() {}

    public UserDtoResponse(String username, String email, Date creationDate, List<UrlDtoResponse> urls) {
        this.username = username;
        this.email = email;
        this.creationDate = creationDate;
        this.urls = urls;
    }
}
