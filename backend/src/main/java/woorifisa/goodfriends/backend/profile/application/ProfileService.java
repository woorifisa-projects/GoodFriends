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
                .orElse(null);

        if(profile == null) { // 프로필을 등록하지 않은 경우 새로 생성해서 값을 넣어준다.
            profileRepository.save(profile.builder()
                    .user(user)
                    .mobilePhone(request.getMobileNumber())
                    .address(request.getAddress())
                    .build());
        }
        else { // 기존에 프로필이 있는 경우, 프로필 정보(핸드폰, 주소)를 수정해서 저장한다.
            profile.updateMobilePhone(request.getMobileNumber());
            profile.updateAddress(request.getAddress());
            profileRepository.save(profile);
        }
    }
}
