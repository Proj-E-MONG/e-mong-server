//package com.yours.emong.global.googleSheets.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class GoogleSheetsScheduledService {
//
//    private final GoogleSheetsService googleSheetsService;
//
//    @Scheduled(fixedRate = 60000)
//    public void syncSheetsData() {
//        try {
//            googleSheetsService.processSignUp();
//
//        }catch (IOException e) {
//            System.err.println("Failed to sync data: " + e.getMessage());
//        }
//    }
//}