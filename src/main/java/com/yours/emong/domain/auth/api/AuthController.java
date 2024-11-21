package com.yours.emong.domain.auth.api;

import com.yours.emong.domain.auth.dto.request.RefreshTokenRequest;
import com.yours.emong.domain.auth.dto.request.SignInRequest;
import com.yours.emong.domain.auth.dto.request.SignUpRequest;
import com.yours.emong.domain.auth.dto.response.RefreshTokenResponse;
import com.yours.emong.domain.auth.service.AuthService;
import com.yours.emong.global.common.response.BaseResponse;
import com.yours.emong.global.common.response.BaseResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse signUp(@Validated @RequestBody SignUpRequest request) {
        authService.signUp(request);
        return BaseResponseData.created("회원가입 성공");
    }

    @PostMapping("/sign-in")
    public BaseResponse signIn(@Validated @RequestBody SignInRequest request) {
        return BaseResponseData.ok(
                "로그인 성공",
                authService.signIn(request)
        );
    }

    @PostMapping("/refresh")
    public BaseResponseData<RefreshTokenResponse> refresh(RefreshTokenRequest request) {
        return BaseResponseData.ok(
                "재발급 성공",
                authService.refresh(request.refreshToken()));
    }



}
