package com.yours.emong.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignInRequest (
        @NotBlank
        String serialNumber,
        @NotBlank
        String name,
        @NotBlank
        String schoolName
){
}
