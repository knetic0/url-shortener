package com.mehmetsolak.urlshortener.dto.request;

import com.mehmetsolak.urlshortener.dto.BaseDto;
import com.mehmetsolak.urlshortener.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class UserRegisterRequest extends BaseDto {
    private String email;
    private String username;
    private String password;

    public User toUser(String hashedPassword) {
        return new User(username, email, hashedPassword);
    }
}
