package com.TAE.shopping.member.service;

import com.TAE.shopping.member.domain.entity.Member;

public interface MemberService {

    public void registrationMember(Member member);

    public boolean isDuplicatedEmail(String email);


}
