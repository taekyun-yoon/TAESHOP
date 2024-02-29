package com.TAE.shopping.auth.service;

import com.TAE.shopping.member.domain.entity.Member;
import com.TAE.shopping.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
    private final MemberService memberService;

    @Override
    public PrincipalDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member findMember = memberService.findMemberByEmail(email);

        if (findMember != null) {
            return new PrincipalDetails(findMember);
        }

        return null;
    }
}
