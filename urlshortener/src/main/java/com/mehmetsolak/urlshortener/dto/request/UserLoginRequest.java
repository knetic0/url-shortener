package com.mehmetsolak.urlshortener.dto.request;

import com.mehmetsolak.urlshortener.constants.Messages;
import com.mehmetsolak.urlshortener.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class UserLoginRequest extends BaseDto {
    @NotBlank(message = Messages.USERNAME_CANNOT_EMPTY)
    private String username;

    @NotBlank(message = Messages.PASSWORD_CANNOT_EMPTY)
    private String password;
}
