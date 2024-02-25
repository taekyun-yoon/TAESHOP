package com.TAE.shopping.member.service;

import com.TAE.shopping.member.domain.entity.Member;
import com.TAE.shopping.member.domain.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralMemberService implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void registrationMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public boolean isDuplicatedEmail(String email) {
        return  memberRepository.existsByEmail(email);
    }
}
