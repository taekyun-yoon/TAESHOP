package com.TAE.shopping.member.domain.repository;

import com.TAE.shopping.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public boolean existsByEmail(String email);


}
