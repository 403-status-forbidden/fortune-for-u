package com.a403.ffu.global.security.oauth.mapper;

import java.util.EnumMap;
import org.springframework.stereotype.Component;

@Component
public class AttributeMapperFactory {

    private final EnumMap<AuthProvider, AttributeMapper> mapperMap =
            new EnumMap<>(AuthProvider.class);
    private final GoogleAttributeMapper googleAttributeMapper;
    private final KakaoAttributeMapper kakaoAttributeMapper;

    public AttributeMapperFactory(
            GoogleAttributeMapper googleAttributeMapper,
            KakaoAttributeMapper kakaoAttributeMapper) {
        this.googleAttributeMapper = googleAttributeMapper;
        this.kakaoAttributeMapper = kakaoAttributeMapper;
        initialize();
    }

    private void initialize() {
        mapperMap.put(AuthProvider.GOOGLE,
                googleAttributeMapper);
        mapperMap.put(AuthProvider.KAKAO,
                kakaoAttributeMapper);
    }

    public AttributeMapper getAttributeMapper(AuthProvider authProvider) {
        return mapperMap.get(authProvider);
    }
}
