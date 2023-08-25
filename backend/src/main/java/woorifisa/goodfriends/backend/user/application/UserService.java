package woorifisa.goodfriends.backend.user.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;
import woorifisa.goodfriends.backend.user.dto.response.UserResponse;
import woorifisa.goodfriends.backend.user.exception.NoSuchUserException;

@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserResponse findById(final Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(NoSuchUserException::new);
        return new UserResponse(user);
    }
}
