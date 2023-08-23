package woorifisa.goodfriends.backend.auth.application;

import woorifisa.goodfriends.backend.auth.dto.OAuthUser;

public interface OAuthClient {
    OAuthUser getOAuthUser(final String code, final String redirectUri);
}
