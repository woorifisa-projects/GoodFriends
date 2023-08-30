package woorifisa.goodfriends.backend.auth.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorifisa.goodfriends.backend.auth.domain.AuthToken;
import woorifisa.goodfriends.backend.auth.domain.OAuthToken;
import woorifisa.goodfriends.backend.auth.domain.OAuthTokenRepository;
import woorifisa.goodfriends.backend.auth.dto.OAuthUser;
import woorifisa.goodfriends.backend.auth.dto.request.TokenRenewalRequest;
import woorifisa.goodfriends.backend.auth.dto.response.AccessTokenResponse;
import woorifisa.goodfriends.backend.auth.event.UserSavedEvent;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

@Transactional
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final OAuthTokenRepository oAuthTokenRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final TokenCreator tokenCreator;

    public AuthService(final UserRepository userRepository, final OAuthTokenRepository oAuthTokenRepository,
                       final ApplicationEventPublisher eventPublisher, final TokenCreator tokenCreator) {
        this.userRepository = userRepository;
        this.oAuthTokenRepository = oAuthTokenRepository;
        this.eventPublisher = eventPublisher;
        this.tokenCreator = tokenCreator;
    }

    public AccessTokenResponse generateAccessAndRefreshToken(final OAuthUser oAuthUser) {
        User foundUser = findUser(oAuthUser);

        OAuthToken oAuthToken = getOAuthToken(oAuthUser, foundUser);
        oAuthToken.change(oAuthUser.getRefreshToken());

        AuthToken authToken = tokenCreator.createAuthToken(foundUser.getId());

        return new AccessTokenResponse(authToken.getId(), authToken.getAccessToken());
    }

    private OAuthToken getOAuthToken(final OAuthUser oAuthUser, final User user) {
        Long userId = user.getId();
        if(oAuthTokenRepository.existsByUserId(userId)) {
            return oAuthTokenRepository.getByUserId(userId);
        }
        return oAuthTokenRepository.save(new OAuthToken(user, oAuthUser.getRefreshToken()));
    }


    private User findUser(final OAuthUser oAuthUser) {
        String email = oAuthUser.getEmail();
        if (userRepository.existsByEmail(email)) {
            return userRepository.getByEmail(email);
        }
        return saveMember(oAuthUser);
    }

    private User saveMember(final OAuthUser oAuthUser) {
        User saveUser = userRepository.save(oAuthUser.toUser());
        eventPublisher.publishEvent(new UserSavedEvent(saveUser.getId()));
        return saveUser;
    }

    public Long extractMemberId(final String accessToken) {
        Long userId = tokenCreator.extractPayLoad(accessToken);
        userRepository.validateExistById(userId);
        return userId;
    }

    public AccessTokenResponse generateAccessToken(TokenRenewalRequest tokenRenewalRequest) {
        String refreshToken = tokenRenewalRequest.getRefreshToken();
        AuthToken authToken = tokenCreator.renewAuthToken(refreshToken);
        return new AccessTokenResponse(authToken.getId(), authToken.getAccessToken());
    }

    public void deleteToken(Long id) {
        oAuthTokenRepository.deleteAllByUserId(id);
    }
}
