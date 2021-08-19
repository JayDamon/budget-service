package com.factotum.rin.component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@Profile({"!test"})
public class UserFeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String accessToken = ((Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTokenValue();
        template.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
    }

}
