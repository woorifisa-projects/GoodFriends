package woorifisa.goodfriends.backend.user.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.global.application.S3Service;
import woorifisa.goodfriends.backend.global.config.utils.FileUtils;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;
import woorifisa.goodfriends.backend.user.dto.response.UserResponse;
import woorifisa.goodfriends.backend.user.exception.NotFoundUserException;

import java.io.IOException;

@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;

    private final S3Service s3Service;

    public UserService(final UserRepository userRepository, final S3Service s3Service) {
        this.userRepository = userRepository;
        this.s3Service = s3Service;
    }

    public UserResponse findById(final Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(NotFoundUserException::new);
        return new UserResponse(user);
    }

    @Transactional
    public void updateProfileImageToS3(final Long userId, final MultipartFile multipartFile) throws IOException {
        User foundUser = userRepository.getById(userId);
        String updateImageUrl =  updateProfileImageToS3(multipartFile);

        foundUser.updateProfileImageUrl(updateImageUrl);
        userRepository.save(foundUser);
    }
    private String updateProfileImageToS3(final MultipartFile image) throws IOException {
        String uniqueFileName = FileUtils.generateUniqueFileName(image.getOriginalFilename());
        String updatedImageUrl = s3Service.saveFile(image, uniqueFileName);

        return updatedImageUrl;
    }
}
