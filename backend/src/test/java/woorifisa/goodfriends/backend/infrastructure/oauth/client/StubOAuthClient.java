package woorifisa.goodfriends.backend.infrastructure.oauth.client;

import woorifisa.goodfriends.backend.auth.application.OAuthClient;
import woorifisa.goodfriends.backend.auth.dto.OAuthUser;
import woorifisa.goodfriends.backend.common.fixtures.OAuthFixtures;

public class StubOAuthClient implements OAuthClient {

    @Override
    public OAuthUser getOAuthUser(final String code, final String redirectUri) {
        return OAuthFixtures.parseOAuthUser(code);
    }
}
