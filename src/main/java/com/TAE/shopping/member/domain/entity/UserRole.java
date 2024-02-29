package com.TAE.shopping.member.domain.entity;

import lombok.Getter;

@Getter
public enum UserRole {
    MEMBER("회원","MEMBER"),
    SELLER("판매자","SELLER"),
    ADMIN("관리자","ADMIN");

    private final String roleName;
    private final String authority;

    UserRole(String roleName, String authority) {
        this.roleName = roleName;
        this.authority = authority;
    }
}
