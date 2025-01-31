package com.mehmetsolak.urlshortener.dto.request;

import com.mehmetsolak.urlshortener.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class UserLoginRequest extends BaseDto {
    private String username;
    private String password;
}
