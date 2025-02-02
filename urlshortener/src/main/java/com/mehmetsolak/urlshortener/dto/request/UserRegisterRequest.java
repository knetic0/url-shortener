package com.mehmetsolak.urlshortener.dto.request;

import com.mehmetsolak.urlshortener.constants.Messages;
import com.mehmetsolak.urlshortener.dto.BaseDto;
import com.mehmetsolak.urlshortener.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class UserRegisterRequest extends BaseDto {
    @NotBlank(message = Messages.EMAIL_CANNOT_EMPTY)
    private String email;

    @NotBlank(message = Messages.USERNAME_CANNOT_EMPTY)
    private String username;

    @NotBlank(message = Messages.PASSWORD_CANNOT_EMPTY)
    private String password;

    public User toUser(String hashedPassword) {
        return new User(username, email, hashedPassword);
    }
}
