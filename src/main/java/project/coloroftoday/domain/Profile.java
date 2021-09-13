package project.coloroftoday.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Profile {
    @Id @GeneratedValue
    @Column(name = "profile_id")
    private Long id;

    private String content;

    @Embedded
    private Color color;

    @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
    private Member member;
}
