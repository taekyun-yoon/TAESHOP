package com.TAE.shopping.auth.jwt;


import io.jsonwebtoken.IncorrectClaimException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Access Token 추출
        String accessToken = resolveToken(request);

        try { // 정상 토큰인지 검사
            if (accessToken != null && tokenProvider.validateAccessToken(accessToken)) {
                Authentication authentication = tokenProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.debug("Save authentication in SecurityContextHolder.");
            }
        } catch (IncorrectClaimException e) { // 잘못된 토큰일 경우
            SecurityContextHolder.clearContext();
            log.debug("잘못된 토근입니다. Invalid JWT token.");
            response.sendError(403);
        } catch (UsernameNotFoundException e) { // 회원을 찾을 수 없을 경우
            SecurityContextHolder.clearContext();
            log.debug("회원을 찾을 수 없습니다.");
            response.sendError(403);
        }

        filterChain.doFilter(request, response);
    }

    public String resolveToken(HttpServletRequest httpServletRequest) {
        String authorizationToken = httpServletRequest.getHeader("Authorization");
        if (authorizationToken != null) {
            return authorizationToken.replace(TOKEN_PREFIX, "");
        }
        return null;
    }
}