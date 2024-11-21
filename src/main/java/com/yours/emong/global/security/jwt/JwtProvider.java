package com.yours.emong.global.security.jwt;

import com.yours.emong.global.security.jwt.config.JwtProperties;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;
    private SecretKey secretKey;

   private SecretKey getSecretKey() {
       if(secretKey == null) {
           this.secretKey = new SecretKeySpec(
                   jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8),
                   Jwts.SIG.HS256.key().build().getAlgorithm()
           );
       }
       return secretKey;
   }

    public Jws<Claims> getClaims(final String token){ //Jws: 서명. 페이로드의 데이터가 변조되지 않았음을 확인.
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token); //verifyWith: 키가 유효한지 체크, parseSignedClaims: 서명이 올바른 경우에만 claims(사용자 id, 만료시간, 권한..) 반환
        }catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("만료된 토큰");
        }catch (UnsupportedJwtException e) {
            throw new IllegalArgumentException("지원되지 않는 토큰");
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 토큰");
        }
        return null;
    }

    public String generateAccessToken(final String serialNumber) {
        return Jwts.builder()
                .claim("serialNumber", serialNumber)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String generateRefreshToken(final String serialNumber) {
        return Jwts.builder()
                .claim("serialNumber", serialNumber)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExpiration()))
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }
}
