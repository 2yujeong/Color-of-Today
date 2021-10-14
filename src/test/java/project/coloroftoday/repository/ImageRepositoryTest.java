package project.coloroftoday.repository;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.coloroftoday.controller.UploadFile;
import project.coloroftoday.domain.Image;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class ImageRepositoryTest {
    @Autowired
    ImageRepository imageRepository;

    @Test
    public void testImage() {
        UploadFile uploadFile1 = new UploadFile("userName1", "storeName1");
        UploadFile uploadFile2 = new UploadFile("userName2", "storeName2");
        UploadFile uploadFile3 = new UploadFile("userName3", "storeName3");

        Image image = new Image(uploadFile1.getUploadFileName(), uploadFile1.getStoreFileName(),
                uploadFile2.getUploadFileName(), uploadFile2.getStoreFileName(),
                uploadFile3.getUploadFileName(), uploadFile3.getStoreFileName());

        Image savedImage = imageRepository.save(image);
        Image findImage = imageRepository.findById(savedImage.getId()).get();

        assertThat(findImage.getId()).isEqualTo(image.getId());
        assertThat(findImage).isEqualTo(image);
    }
}