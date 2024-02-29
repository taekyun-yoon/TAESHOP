package com.TAE.shopping.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDto {

    private String accessToekn;
    private String refreshToken;

    public TokenDto(String accessToekn, String refreshToken) {
        this.accessToekn = accessToekn;
        this.refreshToken = refreshToken;
    }
}
