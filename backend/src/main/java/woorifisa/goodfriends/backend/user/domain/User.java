package woorifisa.goodfriends.backend.user.domain;


import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    private Nickname nickname;

    private int ban;

    protected User() {
    }

    public User(final String email, final Nickname nickname, int ban) {

        this.email = email;
        this.nickname = nickname;
        this.ban = ban;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public int getBan() {
        return ban;
    }

}
