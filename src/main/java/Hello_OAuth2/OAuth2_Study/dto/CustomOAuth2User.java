package Hello_OAuth2.OAuth2_Study.dto;

import Hello_OAuth2.OAuth2_Study.service.CustomOAuth2UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final OAuth2ResponseDto oAuth2ResponseDto;
    private final String role;

    @Override
    public Map<String, Object> getAttributes() { // 로그인 진행 시 넘어오는 데이터 주입.
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // ROLE 값

        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return oAuth2ResponseDto.getName();
    }

    public String getUsername (){
        return oAuth2ResponseDto.getProvider() + " " + oAuth2ResponseDto.getProviderId();
    }
}