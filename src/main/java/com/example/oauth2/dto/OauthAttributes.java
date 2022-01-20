package com.example.oauth2.dto;

import com.example.oauth2.domain.Role;
import com.example.oauth2.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OauthAttributes {

    public Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OauthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email
            ,String picture){
        this.attributes=attributes;
        this.nameAttributeKey=nameAttributeKey;
        this.name=name;
        this.email=email;
        this.picture=picture;
    }

    public static OauthAttributes of(String registrationId, String userNameAttributeName, Map<String,Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OauthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){

        return OauthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String)attributes.get("email"))
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .picture((String) attributes.get("picture"))
                .build();
    }

    public User toEntity(){
        return User.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }


}
