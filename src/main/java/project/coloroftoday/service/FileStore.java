package project.coloroftoday.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import project.coloroftoday.controller.UploadFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty())
            return null;

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName))); // Full path 경로에 파일 저장

        System.out.println(getFullPath(storeFileName));

        return new UploadFile(originalFileName, storeFileName);
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws  IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();

        for (MultipartFile file : multipartFiles) {
            if (!file.isEmpty())
                storeFileResult.add(storeFile(file));
            else
                storeFileResult.add(new UploadFile("null", "null"));
        }

        return storeFileResult;
    }

    private String createStoreFileName(String name) { // 서버 저장용 파일 이름 생성
        String ext = extractExt(name);
        String uuid = UUID.randomUUID().toString();

        return uuid + "." + ext;
    }

    private String extractExt(String name) { // 확장자명 추출 함수
        int pos = name.lastIndexOf(".");

        return name.substring(pos + 1);
    }

    public String getFullPath(String name) {
        return fileDir + name;
    }
}
