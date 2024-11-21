package com.yours.emong.domain.auth.dto.request;

import jakarta.validation.constraints.*;
import org.aspectj.bridge.Message;
import org.aspectj.weaver.ast.Not;

import java.time.Year;

public record SignUpRequest (

        @NotBlank
        String name,
        @NotNull
        @Min(2020)
        @Max(2100)
        int graduationYear,
        @NotBlank
        String schoolName,
        @NotBlank
        String phoneNumber
//        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}", message = "정해진 핸드폰 양식으로 입력해주세요. (010-0000-0000)")

){
}
