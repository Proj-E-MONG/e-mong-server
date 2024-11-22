package com.yours.emong.domain.album.controller;


import com.yours.emong.domain.album.service.AlbumService;
import com.yours.emong.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/s3")
public class AlbumController {

    private final AlbumService albumService;

    // 사진 등록
    @PostMapping("/file")
    public BaseResponse<List<String>> uploadFile(@RequestPart List<MultipartFile> multipartFile) {
        List<String> uploadedFiles = albumService.uploadFile(multipartFile);
        return new BaseResponse<>(true, "파일 업로드 성공", uploadedFiles);
    }

    // 사진 삭제
    @DeleteMapping("/file")
    public BaseResponse<Void> deleteFile(@RequestParam String fileName) {
        albumService.deleteFile(fileName);
        return new BaseResponse<>(true, "파일 삭제 성공", null);
    }
}
