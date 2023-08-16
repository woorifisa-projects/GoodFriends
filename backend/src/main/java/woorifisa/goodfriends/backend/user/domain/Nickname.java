package woorifisa.goodfriends.backend.user.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Nickname {

    private static final Pattern PATTERN = Pattern.compile("^[0-9a-zA-Z가-힣]+(?:\\\\s+[0-9a-zA-Z가-힣]+)*$");
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 20;

    @Column(name = "nickname", unique = true)
    private String value;

    protected Nickname() {
    }

    public Nickname(String value) {
        this.value = value;
    }
}
