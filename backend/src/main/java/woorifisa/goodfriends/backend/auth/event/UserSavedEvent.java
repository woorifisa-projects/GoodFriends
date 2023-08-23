package woorifisa.goodfriends.backend.auth.event;

// 스프링 프레임워크의 이벤트 기능을 활용하여 사용자가 저장되었음을 나타내는 이벤트를 표현하는 클래스
// 이벤트 기능은 애플리케이션 내에서 이벤트 기반 아키텍처를 구현하거나 비동기 작업을 수행할 때 유용
// UserSavedEvent 클래스는 사용자 저장 시에 해당 사용자의 ID 정보를 전달하기 위해 사용되며, 이를 이벤트 핸들러가 수신하여 처리할 수 있도록 돕는 역할을 합니다.
public class UserSavedEvent {
    private final Long userId;

    public UserSavedEvent(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
