package com.yours.emong.domain.album.controller;


import com.yours.emong.domain.album.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<String>> uploadFile(@RequestPart List<MultipartFile> multipartFile) {
        return ResponseEntity.ok(albumService.uploadFile(multipartFile));
    }


    // 사진 삭제
    @DeleteMapping("/file")
    public ResponseEntity<Void> deleteFile(@RequestParam String fileName) {
        albumService.deleteFile(fileName);
        return ResponseEntity.ok().build();
    }
}
