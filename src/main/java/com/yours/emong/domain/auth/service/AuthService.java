package com.yours.emong.domain.auth.service;

import com.yours.emong.domain.auth.dto.request.SignInRequest;
import com.yours.emong.domain.auth.dto.request.SignUpRequest;
import com.yours.emong.domain.auth.dto.response.JsonWebTokenResponse;
import com.yours.emong.domain.auth.dto.response.RefreshTokenResponse;
import com.yours.emong.domain.school.domain.SchoolEntity;
import com.yours.emong.domain.school.domain.repository.jpa.SchoolJpaRepository;
import com.yours.emong.domain.user.domain.UserEntity;
import com.yours.emong.domain.user.domain.repository.jpa.UserJpaRepository;
import com.yours.emong.domain.user.exception.UserExistException;
import com.yours.emong.domain.user.exception.UserInfoMismatchedException;
import com.yours.emong.domain.user.exception.UserNotFoundException;
import com.yours.emong.global.security.jwt.JwtExtract;
import com.yours.emong.global.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserJpaRepository userJpaRepository;
    private final SchoolJpaRepository schoolJpaRepository;
    private final JwtExtract jwtExtract;
    private final JwtProvider jwtProvider;

    public void signUp(SignUpRequest request) { //회원가입
        if (checkUserByPhoneNumberAndGraduationYear(request.phoneNumber(), request.graduationYear())) { //졸업 년도를 보내고
            throw UserExistException.EXCEPTION;
        }; //가입 이력 있는지 확인

        SchoolEntity school = schoolJpaRepository.getBySchoolNameAndGraduationYear(request.schoolName(), request.graduationYear());

        if(school == null) {
            LocalDate userGraduationDate = LocalDate.now();
            school = schoolJpaRepository.save(SchoolEntity.builder()
                    .schoolName(request.schoolName())
                    .graduationDate(userGraduationDate)
                    .build());
        }

        String serialNumber = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0,10);

        userJpaRepository.save(UserEntity.builder()
                .phoneNumber(request.phoneNumber())
                .serialNumber(serialNumber) //이거 16자로 설정하기 , 스쿨엔티티 설정
                .name(request.name())
                .school(school)
                .accessStartDate(school.getGraduationDate())
                .accessEndDate(school.getGraduationDate().plusYears(5)) //졸업 후 5년 뒤.
                .build());

        // serialNumber 변수에 담긴 값 문자발송
    }

    public JsonWebTokenResponse signIn(SignInRequest request) { //로그인
        if (!checkUserBySerialNumber(request.serialNumber())) {
            throw UserNotFoundException.EXCEPTION;
        } //해당 시리얼번호의 유저가 없는데 로그인 한 경우.

        if(!checkUserBySerialNumberAndNameAndSchoolName(request.serialNumber(), request.name(), request.schoolName())) {
            throw UserInfoMismatchedException.EXCEPTION;
        }
        System.out.println(request.serialNumber());
        return JsonWebTokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(request.serialNumber())) //회원가입하면 시리얼이 발급되고 이로 어세스토큰 발급
                .refreshToken(jwtProvider.generateRefreshToken(request.serialNumber()))
                .build();
    }

    public RefreshTokenResponse refresh(String token) {
        Jws<Claims> claims = jwtProvider.getClaims(token);
        return RefreshTokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(jwtExtract.getSerialNumber(token))).build();
    }

    public boolean checkUserByPhoneNumberAndGraduationYear(String phoneNumber, int graduationYear) {
        return userJpaRepository.getByPhoneNumberAndAccessStartDate(phoneNumber, graduationYear).isPresent();//값 있으면 true 아님 null
    }
    public boolean checkUserBySerialNumber(String serialNumber) {
        return userJpaRepository.findBySerialNumber(serialNumber).isPresent();
    }

    public boolean checkUserBySerialNumberAndNameAndSchoolName (String serialNumber, String name, String schoolName) {
        return userJpaRepository.findBySerialNumberAndNameAndSchoolName(serialNumber, name, schoolName).isPresent();
    }
}
