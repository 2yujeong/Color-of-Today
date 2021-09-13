package project.coloroftoday.domain;

import lombok.Getter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member implements Persistable<String> {
    @Id
    // @GeneratedValue까지 설정해주면 엔티티 생성 시점에는 Id 값이 null이고 DB에 커밋될 때 Id 값이 생성된다.
    // @GeneratedValue를 설정하지 않으면 엔티티 생성 시점에 이미 Id 값이 설정되어 있는 상태
    // 스프링 데이터 JPA에서는 save() 시 새로운 엔티티면 persist, 아니면 merge를 실행
    // -> @GeneratedValue를 설정하지 않으면 Id 값이 null이 아니라서 새로운 데이터임에도 merge가 실행되어 버림
    // 해결 : Persistable 인터페이스의 isNew()를 직접 구현해서 새로운 엔티티인지 판단하는 기본 로직을 변경한다.
    @Column(name = "member_id")
    private String id;

    private String nickname;
    private String email;
    private String pw;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Share> shares = new ArrayList<>();

    public Member(String id) {
        this.id = id;
    }

    protected Member () { }

    @Override
    public boolean isNew() { // 데이터 save() 시 새로운 엔티티인지 판단하는 기본 전략
        return profile == null;
    }
}
