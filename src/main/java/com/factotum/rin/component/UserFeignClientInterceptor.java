package com.factotum.rin.component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
@Profile({"!test"})
public class UserFeignClientInterceptor implements RequestInterceptor {

    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    public UserFeignClientInterceptor(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager) {
        this.oAuth2AuthorizedClientManager = oAuth2AuthorizedClientManager;
    }

    @Override
    public void apply(RequestTemplate template) {
        OAuth2AuthorizedClient client = this.oAuth2AuthorizedClientManager.authorize(
                OAuth2AuthorizeRequest
                        .withClientRegistrationId("rin-budget-service")
                        .principal(createPrincipal()).build());
        if (client != null) {
            String accessToken = client.getAccessToken().getTokenValue();
            template.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        } else {
            throw new AccessDeniedException("Valid client was not able to be constructed");
        }
    }

    private Authentication createPrincipal() {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.emptySet();
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "rin-budget-service";
            }
        };
    }
}
