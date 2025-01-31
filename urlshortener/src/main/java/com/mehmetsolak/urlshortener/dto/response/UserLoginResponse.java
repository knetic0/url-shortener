package com.mehmetsolak.urlshortener.dto.response;

import com.mehmetsolak.urlshortener.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class UserLoginResponse extends BaseDto {
    private String token;
    private UserDtoResponse user;

    public UserLoginResponse() {}

    public UserLoginResponse(String token, UserDtoResponse user) {
        this.token = token;
        this.user = user;
    }
}
