package Hello_OAuth2.OAuth2_Study.dto;

public interface OAuth2ResponseDto { // 포털 사이트 마다 응답해주는 데이터 규격이 다르므로, 인터페이스로

    String getProvider();

    String getProviderId(); // 각 사용자 별 고유 ID

    String getName();

    String getEmail();

    String getNickname();



}
