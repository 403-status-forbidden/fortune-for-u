package com.ssafy.a403.domain.member.service;

import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.MemberRepository;
import com.ssafy.a403.global.config.security.oauth.OAuth2Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    public Member saveIfNotExists(OAuth2Request oAuth2Request) {
        return memberRepository.findByOauth2AccountId(oAuth2Request.getAccountId())
                .orElseGet(() -> save(oAuth2Request));
    }

    private Member save(OAuth2Request oAuth2Request) {
        return memberRepository.save(
                Member.builder()
                        .email(oAuth2Request.getEmail())
                        .name(oAuth2Request.getName())
                        .authProvider(oAuth2Request.getAuthProvider())
                        .accountId(oAuth2Request.getAccountId())
                        .build()
        );
    }
}
