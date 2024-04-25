package com.a403.ffu.member.service;

import com.a403.ffu.global.security.oauth.OAuth2Request;
import com.a403.ffu.member.entity.Member;
import com.a403.ffu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public Member saveIfNotExists(OAuth2Request oAuth2Request) {
        return memberRepository.findByOauth2AccountId(oAuth2Request.accountId())
                .orElseGet(() -> save(oAuth2Request));
    }

    private Member save(OAuth2Request oAuth2Request) {
        return memberRepository.save(
                Member.builder()
                        .email(oAuth2Request.email())
                        .name(oAuth2Request.name())
                        .authProvider(oAuth2Request.authProvider())
                        .accountId(oAuth2Request.accountId())
                        .build()
        );
    }
}
