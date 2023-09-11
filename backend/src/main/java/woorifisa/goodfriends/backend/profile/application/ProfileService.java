package woorifisa.goodfriends.backend.profile.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.profile.domain.ProfileRepository;
import woorifisa.goodfriends.backend.profile.dto.request.ProfileUpdateRequest;
import woorifisa.goodfriends.backend.profile.dto.response.ProfileViewResponse;
import woorifisa.goodfriends.backend.profile.exception.AlreadyExitPhoneProfile;
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

    public ProfileViewResponse viewProfile(Long userId) {
        User user = userRepository.getById(userId);
        Profile profile = profileRepository.findByUserId(userId).orElse(null);

        ProfileViewResponse profileViewResponse;

        // profile 정보가 없을 경우 주소와 핸드폰 번호는 null 로 반환
        if (profile == null) {
            profileViewResponse = new ProfileViewResponse(user.getId(), user.getProfileImageUrl(), user.getNickname(), user.getEmail(), null, null, null, null);
        }else{
            profileViewResponse = new ProfileViewResponse(user.getId(), user.getProfileImageUrl(), user.getNickname(), user.getEmail(), profile.getAddress(), profile.getMobileNumber(), profile.getAccountType(), profile.getAccountNumber());
        }

        return profileViewResponse;
    }

    public void update(Long userId, ProfileUpdateRequest request) {
        User user = userRepository.getById(userId);

        user.updateNickname(request.getNickName());
        userRepository.save(user);

        Profile profile = profileRepository.findByUserId(userId)
                .orElse(null);

        Boolean checkPhone = profileRepository.existsByMobileNumber(request.getMobileNumber());
        if(checkPhone){
            throw new AlreadyExitPhoneProfile();
        }

        if (profile == null) { // 프로필을 등록하지 않은 경우 새로 생성해서 값을 넣어준다.
            profileRepository.save(profile.builder()
                    .user(user)
                    .mobileNumber(request.getMobileNumber())
                    .address(request.getAddress())
                    .accountType(request.getAccountType())
                    .accountNumber(request.getAccountNumber())
                    .build());
        } else { // 기존에 프로필이 있는 경우, 프로필 정보(핸드폰, 주소, 계좌종류, 계좌번호)를 수정해서 저장한다.
            profile.updateMobileNumber(request.getMobileNumber());
            profile.updateAddress(request.getAddress());
            profile.updateAccountType(request.getAccountType());
            profile.updateAccountNumber(request.getAccountNumber());
            profileRepository.save(profile);
        }
    }
}
