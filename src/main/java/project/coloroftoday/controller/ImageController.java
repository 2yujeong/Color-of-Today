package project.coloroftoday.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.coloroftoday.domain.Image;
import project.coloroftoday.repository.ImageRepository;
import project.coloroftoday.service.FileStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageRepository imageRepository;
    private final FileStore fileStore;

    @GetMapping("/images/new")
    public String newImage(@ModelAttribute ImageForm form) {
        return "image-form-test";
    }

    @PostMapping("/images/new")
    public String saveImage(@ModelAttribute ImageForm form, Model model) throws IOException {
        // 서버에 실제 이미지 파일 저장 후 저장용 파일 이름 반환
        List<MultipartFile> multiFiles = new ArrayList<>();
        multiFiles.add(form.getFile1());
        multiFiles.add(form.getFile2());
        multiFiles.add(form.getFile3());

        List<UploadFile> files = fileStore.storeFiles(multiFiles);

        // DB 저장
        Image image = new Image(files.get(0).getUploadFileName(), files.get(0).getStoreFileName(),
                files.get(1).getUploadFileName(), files.get(1).getStoreFileName(),
                files.get(2).getUploadFileName(), files.get(2).getStoreFileName());
        imageRepository.save(image);

        model.addAttribute("image", image);

        return "image-view";
    }

}
