package com.ssafy.a403.global.config.security.oauth;

import com.ssafy.a403.domain.member.service.AuthService;
import com.ssafy.a403.global.config.security.oauth.mapper.AttributeMapperFactory;
import com.ssafy.a403.global.config.security.oauth.mapper.AuthProvider;
import com.ssafy.a403.global.config.security.oauth.mapper.LoginUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * i. access token을 이용해 서드파티 서버로부터 사용자 정보를 받아옴
 * ii. 사용자 정보를 파탕으로 회원가입된 사용자인지 확인
 * iii. LoginUser(User Principal) 반환
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultUserService extends DefaultOAuth2UserService {
	
	private final AuthService authService;
    private final AttributeMapperFactory attributeMapperFactory;
    private final LoginUserMapper loginUserMapper;

    // access token으로 서드파티에 요청해서 사용자 정보 얻어온 상태
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        AuthProvider authProvider = AuthProvider.valueOf(userRequest.getClientRegistration().getClientName().toUpperCase());
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        log.trace("Access Token Value: {}", accessToken.getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2Request oAuth2Request = attributeMapperFactory.getAttributeMapper(authProvider)
                .mapToDto(oAuth2User.getAttributes());

        return loginUserMapper.toLoginUser(authService.saveIfNotExists(oAuth2Request)); // OAuth2User 객체 리턴
    }
}
