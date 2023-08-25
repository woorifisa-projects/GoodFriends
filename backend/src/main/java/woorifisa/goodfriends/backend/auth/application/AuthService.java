package woorifisa.goodfriends.backend.auth.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorifisa.goodfriends.backend.auth.domain.AuthToken;
import woorifisa.goodfriends.backend.auth.dto.OAuthUser;
import woorifisa.goodfriends.backend.auth.dto.response.AccessTokenResponse;
import woorifisa.goodfriends.backend.auth.event.UserSavedEvent;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

@Transactional
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final TokenCreator tokenCreator;

    public AuthService(UserRepository userRepository, ApplicationEventPublisher eventPublisher, TokenCreator tokenCreator) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        this.tokenCreator = tokenCreator;
    }

    public AccessTokenResponse generateAccessToken(OAuthUser oAuthUser) {
        User foundUser = findUser(oAuthUser);

        AuthToken authToken = tokenCreator.createAuthToken(foundUser.getId());

        return new AccessTokenResponse(authToken.getId(), authToken.getAccessToken());
    }

    private User findUser(OAuthUser oAuthUser) {
        String email = oAuthUser.getEmail();
        if (userRepository.existsByEmail(email)) {
            return userRepository.getByEmail(email);
        }
        return saveMember(oAuthUser);
    }

    private User saveMember(OAuthUser oAuthUser) {
        User saveUser = userRepository.save(oAuthUser.toUser());
        eventPublisher.publishEvent(new UserSavedEvent(saveUser.getId()));
        return saveUser;
    }

    public Long extractMemberId(String accessToken) {
        Long userId = tokenCreator.extractPayLoad(accessToken);
        userRepository.validateExistById(userId);
        return userId;
    }
}
