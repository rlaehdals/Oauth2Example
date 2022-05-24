package com.example.oauth2.restouath2;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final SocialOauth socialOauth;

    public String request(String type) throws IOException {
        String redirectURL;
        redirectURL=socialOauth.getOauthRedirectURL();
        return redirectURL;
    }

    public GetSocialOAuthRes oAuthLogin(String code) throws JsonProcessingException {
        ResponseEntity<String> accessToken =socialOauth.requestAccessToken(code);
        GoogleOAuthToken googleOAuthToken =socialOauth.getAccessToken(accessToken);

        ResponseEntity<String> userInfoResponse=socialOauth.requestUserInfo(googleOAuthToken);

        GoogleUser googleUser =socialOauth.getUserInfo(userInfoResponse);

        String user_id = googleUser.getEmail();

        return new GetSocialOAuthRes("1234",1,"asdf", googleOAuthToken.getToken_type());
    }
}
