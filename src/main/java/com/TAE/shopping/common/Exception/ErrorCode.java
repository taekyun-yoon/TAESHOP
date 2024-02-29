package com.TAE.shopping.common.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, "email 또는 비밀번호가 맞지 않습니다.", "다른 이메일 또는 비밀번호를 사용해야합니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저 정보를 찾지 못했습니다.", "email 과 password 를 올바르게 입력했는지 확인해주세요");


    private final HttpStatus httpStatus;
    private final String message;
    private final String solution;
}
