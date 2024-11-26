package com.yours.emong.global.security.jwt;

import com.yours.emong.domain.user.domain.repository.jpa.UserJpaRepository;
import com.yours.emong.domain.user.dto.User;
import com.yours.emong.domain.user.exception.UserNotFoundException;
import com.yours.emong.global.security.auth.CustomUserDetails;
import com.yours.emong.global.security.jwt.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class JwtExtract {

    private final UserJpaRepository userJpaRepository;
    private final User userDTO;
    private final JwtProperties jwtProperties;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = new SecretKeySpec(
                this.jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );
    }

    public Authentication getAuthentication(final String token) {
        User user = userJpaRepository.
                findBySerialNumber(getSerialNumber(token))
                .map(userDTO::toUser)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        final CustomUserDetails details = new CustomUserDetails(user);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        return extractToken(request.getHeader(HttpHeaders.AUTHORIZATION)); //AUTHORIZATION 헤더에 있는 값을 가져옴. Baerer {토큰} 형식으로 jwt 토큰이 저장되어있음.
    }

    public String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }
    public String getSerialNumber(String token) {
        Claims claims = Jwts.parser()  // parserBuilder() 사용
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("serialNumber", String.class);
    }

}
