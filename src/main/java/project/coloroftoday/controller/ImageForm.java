package project.coloroftoday.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageForm {
    private Long id;

    private MultipartFile file1;
    private MultipartFile file2;
    private MultipartFile file3;
}


