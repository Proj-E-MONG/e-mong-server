package com.yours.emong.global.security.jwt.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "application.jwt") //application.yml에서 application.jwt 로 시작하는 속성값을 객체로 주입
public class JwtProperties {

    private String secretKey;
    private long expiration;
    private long refreshExpiration;
}
