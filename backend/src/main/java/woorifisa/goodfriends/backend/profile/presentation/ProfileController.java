package woorifisa.goodfriends.backend.profile.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.auth.presentation.AuthenticationPrincipal;
import woorifisa.goodfriends.backend.profile.dto.response.ProfilePurchasesResponse;
import woorifisa.goodfriends.backend.profile.dto.response.ProfileSellsResponse;
import woorifisa.goodfriends.backend.profile.application.ProfileService;
import woorifisa.goodfriends.backend.profile.dto.request.ProfileUpdateRequest;
import woorifisa.goodfriends.backend.profile.dto.response.ProfileBannerResponse;
import woorifisa.goodfriends.backend.profile.dto.response.ProfileDetailResponse;
import woorifisa.goodfriends.backend.user.application.UserService;

import javax.validation.Valid;
import java.io.IOException;

@RequestMapping("/api/profile")
@RestController
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;

    public ProfileController(final ProfileService profileService, final UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<ProfileDetailResponse> findMyProfile(@AuthenticationPrincipal final LoginUser loginUser) {
        ProfileDetailResponse profileDetailResponse = profileService.findMyProfile(loginUser.getId());
        return ResponseEntity.ok().body(profileDetailResponse);
    }

    @PatchMapping("/me/info") // 닉네임, 핸드폰, 주소, 계좌종류, 계좌번호
    public ResponseEntity<Void> update(@AuthenticationPrincipal final LoginUser loginUser,
                                       @Valid @RequestBody final ProfileUpdateRequest profileUpdateRequest) {
        profileService.update(loginUser.getId(), profileUpdateRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/me/profile-image") // 프로필 이미지 수정
    public ResponseEntity<Void> updateImage(@AuthenticationPrincipal final LoginUser loginUser,
                                            @RequestPart MultipartFile multipartFile) throws IOException {
        userService.updateProfileImageToS3(loginUser.getId(), multipartFile);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/me/sell-list")
    public ResponseEntity<ProfileSellsResponse> findMySellProducts(@AuthenticationPrincipal final LoginUser loginUser,
                                                                   @RequestParam final String productStatus) {
        ProfileSellsResponse responses = profileService.findProfileSells(loginUser.getId(), productStatus);
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/me/purchase-list")
    public ResponseEntity<ProfilePurchasesResponse> findMyPurchaseProducts(@AuthenticationPrincipal final LoginUser loginUser,
                                                                           @RequestParam final String confirmStatus) {
        ProfilePurchasesResponse responses = profileService.findProfilePurchases(loginUser.getId(), confirmStatus);
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/me/banner")
    public ResponseEntity<ProfileBannerResponse> findMyProfileBanner(@AuthenticationPrincipal final LoginUser loginUser) {

        ProfileBannerResponse response = profileService.findMyProfileBanner(loginUser.getId());
        return ResponseEntity.ok().body(response);
    }
}
