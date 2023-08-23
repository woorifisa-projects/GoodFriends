package woorifisa.goodfriends.backend.global.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import java.util.List;

@RequestMapping("/setup")
@RestController
public class SetUpController {
    private final UserRepository userRepository;

    public SetUpController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public List<User> setup() {
        User user1 = new User("goodfriends.admin@gmail.com", "관리자", "admin_image", 0);
        User user2 = new User("fancy@gmail.com", "팬시", "fancy_image", 0);

        userRepository.save(user1);
        userRepository.save(user2);

        return userRepository.findAll(); //  저장된 모든 사용자 목록 반환s
    }
}
