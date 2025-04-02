package Hello_OAuth2.OAuth2_Study.dto;

import java.io.Serializable;
import java.util.Map;

public class NaverResponseDto implements OAuth2ResponseDto {

    // JSON 담을 데이터
    private final Map<String, Object> attribute;

    public NaverResponseDto(Map<String, Object> attribute) {
        // 네이버인 경우 안에 감싸져 있어서 한 번더
        this.attribute = (Map<String, Object>) attribute.get("response");
    }


    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getNickname() {
        return attribute.get("nickname").toString();
    }
}
