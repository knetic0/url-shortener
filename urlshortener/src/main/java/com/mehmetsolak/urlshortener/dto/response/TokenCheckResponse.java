package com.mehmetsolak.urlshortener.dto.response;

import com.mehmetsolak.urlshortener.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public final class TokenCheckResponse extends BaseDto {
    private Date serverTime;

    public TokenCheckResponse() {}

    public TokenCheckResponse(Date serverTime) {
        this.serverTime = serverTime;
    }
}
