package cona.study.domain.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String nickname;
    @Column(nullable = false, unique = true)
    private String email;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    public static Member of(String name,
                            String password,
                            String nickname,
                            String email,
                            Address address,
                            RoleType role) {
        return new Member(name, password, nickname, email, address, role);
    }

    protected Member() {
    }

    private Member(String name,
                   String password,
                   String nickname,
                   String email,
                   Address address,
                   RoleType role) {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.address = address;
        this.role = role;
    }

}
