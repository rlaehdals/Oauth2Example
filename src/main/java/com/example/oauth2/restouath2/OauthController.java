package com.example.oauth2.restouath2;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/accounts")
public class OauthController {
    private final OauthService oauthService;


    @GetMapping("/auth/{type}")
    public void socialLoginRequest(@PathVariable("type")String type, HttpServletResponse response) throws IOException {
        String requestURL = oauthService.request(type.toUpperCase());
        response.sendRedirect(requestURL);
    }

    @GetMapping("/auth/{type}/redirect")
    public ResponseEntity<?> callback(@PathVariable(name = "type") String type,
                                      @RequestParam(name = "code") String code) throws JsonProcessingException {
        GetSocialOAuthRes getSocialOauthRes =oauthService.oAuthLogin(code);
        return new ResponseEntity<>(getSocialOauthRes, HttpStatus.OK);
    }
}
