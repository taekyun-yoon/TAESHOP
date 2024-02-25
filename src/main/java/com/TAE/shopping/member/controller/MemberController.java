package com.TAE.shopping.member.controller;

import com.TAE.shopping.member.domain.entity.Member;
import com.TAE.shopping.member.dto.MemberDto;
import com.TAE.shopping.member.service.LoginService;
import com.TAE.shopping.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.TAE.shopping.member.controller.MemberController.MEMBER_API_URI;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(MEMBER_API_URI)
public class MemberController {

    public static final String MEMBER_API_URI = "/api/members";

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid MemberDto memberDto) {

        boolean isDuplicated = memberService.isDuplicatedEmail(memberDto.getEmail());

        if (isDuplicated) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Member member = MemberDto.toEntity(memberDto, passwordEncoder);
        memberService.registrationMember(member);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/duplicated/{email}")
   public ResponseEntity<HttpStatus> isDuplicatedEmail(@PathVariable String email) {
        boolean isDuplicated = memberService.isDuplicatedEmail(email);

        if (isDuplicated) {
            return  ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
