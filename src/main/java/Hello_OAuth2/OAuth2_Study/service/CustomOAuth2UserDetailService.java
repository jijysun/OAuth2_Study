package Hello_OAuth2.OAuth2_Study.service;

import Hello_OAuth2.OAuth2_Study.dto.CustomOAuth2User;
import Hello_OAuth2.OAuth2_Study.dto.GoogleResponseDto;
import Hello_OAuth2.OAuth2_Study.dto.NaverResponseDto;
import Hello_OAuth2.OAuth2_Study.dto.OAuth2ResponseDto;
import Hello_OAuth2.OAuth2_Study.entity.User;
import Hello_OAuth2.OAuth2_Study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserDetailService extends DefaultOAuth2UserService { // or OAuth2UserService를 상속받아도 됨!

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

//        log.info(oAuth2User.getAuthorities().toString());
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 응답 인증 데이터를 준 곳.

        log.info("registrationId:{}", registrationId);

        // 다 다르니까 DTO 로 변환 후 담을 예정
        OAuth2ResponseDto oAuth2ResponseDto;

        if (registrationId.equals("naver")){
            oAuth2ResponseDto = new NaverResponseDto(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")){
            oAuth2ResponseDto = new GoogleResponseDto(oAuth2User.getAttributes());
        }
        else{
            return null;
        }

        // DB 저장 or 업데이트

        String username = oAuth2ResponseDto.getProvider() + " " + oAuth2ResponseDto.getProviderId();
        Optional<User> byUsername = userRepository.findByUsername(username);


        String role = null;
        if (byUsername.isPresent()){

            User user = byUsername.get();
            user.setEmail(oAuth2ResponseDto.getEmail());
            user.setUsername(oAuth2ResponseDto.getProviderId());
            user.setRole("ROLE_USER");

            userRepository.save(user);

        }
        else{

            User user = byUsername.get();
            user.setEmail(oAuth2ResponseDto.getEmail());

            role = user.getRole();

            userRepository.save(user);
        }

        return new CustomOAuth2User(oAuth2ResponseDto, role);
    }
}
