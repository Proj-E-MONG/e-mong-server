//package com.yours.emong.global.googleSheets.service;
//
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.sheets.v4.Sheets;
//import com.google.api.services.sheets.v4.SheetsScopes;
//import com.google.api.services.sheets.v4.model.ValueRange;
//import com.google.auth.http.HttpCredentialsAdapter;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.yours.emong.domain.auth.dto.request.SignUpRequest;
//import com.yours.emong.domain.auth.service.AuthService;
//import com.yours.emong.domain.user.domain.repository.jpa.UserJpaRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class GoogleSheetsService {
//
//    private final UserJpaRepository userJpaRepository;
//    private final AuthService authService;
//
//    @Value("${google.sheets.credentials-path}")
//    private String credentialsPath;
//
//    @Value("${google.sheets.spreadsheet-id}")
//    private String spreadsheetId;
//
//    @Value("${google.sheets.application-name}")
//    private String applicationName;
//
//    @Value("${google.sheets.data-range}")
//    private String dataRange;
//
//    @Value("${api.sign-up-url}")
//    private String signUpUrl;
//
//
//    public List<SignUpRequest> getFormData() throws IOException {
//        InputStream credentialsStream = getClass().getClassLoader().getResourceAsStream(credentialsPath);
//        if (credentialsStream == null) {
//            throw new IOException("Unable to find credentials file at " + credentialsPath);
//        }
//
//        var credentials = GoogleCredentials.fromStream(credentialsStream)
//                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS_READONLY));
//
//        var sheets = new Sheets.Builder(
//                new NetHttpTransport(),
//                JacksonFactory.getDefaultInstance(),
//                new HttpCredentialsAdapter(credentials)
//        ).setApplicationName(applicationName).build();
//
//        ValueRange response = sheets.spreadsheets().values()
//                .get(spreadsheetId, dataRange)
//                .execute();
//
//        List<List<Object>> values = response.getValues();
//        if (values == null || values.isEmpty()) {
//            throw new RuntimeException("No data found in the Google Sheet.");
//        }
//
//        List<SignUpRequest> signUpRequests = new ArrayList<>();
//        for (List<Object> row : values) {
//            if (row.size() < 4) continue; // Skip rows with insufficient data
//
//            String name = row.get(0).toString();
//            String school = row.get(1).toString();
//            int graduationYear = Integer.parseInt(row.get(2).toString());
//            String phoneNumber = row.get(3).toString();
//
//            signUpRequests.add(new SignUpRequest(name, graduationYear, school, phoneNumber));
//        }
//
//        return signUpRequests;
//    }
//
//    public void processSignUp() throws IOException {
//        List<SignUpRequest> signUpRequests = getFormData();
//        for (SignUpRequest request : signUpRequests) {
//            authService.signUp(request); //유효성 체크 및 실제 디비 저장로직
//        }
//    }
//}