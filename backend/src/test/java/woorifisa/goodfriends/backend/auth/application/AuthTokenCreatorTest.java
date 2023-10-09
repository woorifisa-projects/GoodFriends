//package woorifisa.goodfriends.backend.auth.application;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import woorifisa.goodfriends.backend.auth.domain.AuthToken;
//import woorifisa.goodfriends.backend.common.annotation.ServiceTest;
//
//
//public class AuthTokenCreatorTest extends ServiceTest {
//
//    @Autowired
//    private TokenCreator tokenCreator;
//
//    @DisplayName("액세스 토큰을 발급한다.")
//    @Test
//    void 액세스_토큰을_발급한다() {
//        // given
//        Long userId = 1L;
//
//        // when
//        AuthToken authToken = tokenCreator.createAuthToken(userId);
//
//        // then
//        Assertions.assertThat(authToken.getAccessToken()).isNotEmpty();
//    }
//
//    @DisplayName("토큰에서 페이로드를 추출한다.")
//    @Test
//    void 토큰에서_페이로드를_추출한다() {
//        // given
//        Long userId = 1L;
//        AuthToken authToken = tokenCreator.createAuthToken(userId);
//
//        // when
//        Long action = tokenCreator.extractPayLoad(authToken.getAccessToken());
//
//        // then
//        Assertions.assertThat(action).isEqualTo(userId);
//
//    }
//}
