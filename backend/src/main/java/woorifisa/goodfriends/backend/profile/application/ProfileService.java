package woorifisa.goodfriends.backend.profile.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.profile.domain.ProfileRepository;
import woorifisa.goodfriends.backend.profile.dto.request.ProfileUpdateRequest;
import woorifisa.goodfriends.backend.profile.dto.response.ProfileUpdateResponse;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

@Transactional
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public void update(Long userId, ProfileUpdateRequest request) {
        User user = userRepository.getById(userId);

        user.updateNickname(request.getNickName());
        userRepository.save(user);

        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("프로필을 찾을 수 없습니다."));

        profile.updateMobilePhone(request.getMobileNumber());
        profile.updateAddress(request.getAddress());
        profileRepository.save(profile);
    }
}
