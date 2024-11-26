//package com.yours.emong.global.googleSheets.controller;
//
//import com.yours.emong.global.googleSheets.service.GoogleSheetsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.Map;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/google-sheets")
//public class GoogleSheetsController {
//
//    private final GoogleSheetsService googleSheetsService;
//
//    @PostMapping("/webhook")
//    public ResponseEntity<?> handleGoogleSheetsWebhook(@RequestBody Map<String, Object> payload) {
//        try {
//            googleSheetsService.processSignUp(); // 새로운 값이 있을 때 처리
//            return ResponseEntity.ok("회원가입 완료");
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error processing data: " + e.getMessage());
//        }
//    }
//}
