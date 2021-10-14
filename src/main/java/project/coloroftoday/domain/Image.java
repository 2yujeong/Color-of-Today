package project.coloroftoday.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Image {
    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    private String uploadFileName1;
    private String storeFileName1;

    private String uploadFileName2;
    private String storeFileName2;

    private String uploadFileName3;
    private String storeFileName3;

    @OneToOne(mappedBy = "image", fetch = FetchType.LAZY)
    private Share share;

    protected Image() { }

    public Image(String uploadFileName1, String storeFileName1, String uploadFileName2, String storeFileName2, String uploadFileName3, String storeFileName3) {
        this.uploadFileName1 = uploadFileName1;
        this.storeFileName1 = storeFileName1;

        this.uploadFileName2 = uploadFileName2;
        this.storeFileName2 = storeFileName2;

        this.uploadFileName3 = uploadFileName3;
        this.storeFileName3 = storeFileName3;
    }
}
