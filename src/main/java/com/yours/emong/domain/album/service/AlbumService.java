package com.yours.emong.domain.album.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlbumService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public List<String> uploadFile(List<MultipartFile> multipartFile) {
        List<String> fileNameList = new ArrayList<>();

        multipartFile.forEach(file -> {
            String fileName = createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try (InputStream inputStream = file.getInputStream()) {
                PutObjectRequest request = new PutObjectRequest(bucket, fileName, inputStream, objectMetadata);
                amazonS3.putObject(request);

            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
            }
            fileNameList.add(fileName);
        });

        return fileNameList;
    }


    public void deleteFile(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        try{
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }

    public String getFileUrl(String fileName){
        return amazonS3.getUrl(bucket, fileName).toString();
    }

//    public List<String> getAllFileUrls(List<String> fileNames) {
//        List<String> fileUrls = new ArrayList<>();
//        fileNames.forEach(fileName -> {
//            String fileUrl = getFileUrl(fileName);
//            fileUrls.add(fileUrl);
//        });
//        return fileUrls;
//    }

    public List<String> getAllFileUrls() {
        try {
            List<String> fileUrls = new ArrayList<>();
            amazonS3.listObjects(bucket).getObjectSummaries()
                    .forEach(s3Object -> fileUrls.add(amazonS3.getUrl(bucket, s3Object.getKey()).toString()));
            return fileUrls;
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "S3에서 파일 목록을 가져오는 데 실패했습니다.", e);
        }
    }
}
