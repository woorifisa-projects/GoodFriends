package woorifisa.goodfriends.backend.auth.application;

import woorifisa.goodfriends.backend.auth.dto.OAuthMember;

public interface OAuthClient {
    OAuthMember getOAuthmember(final String code, final String redirectUri);
}
