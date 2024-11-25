package com.yours.emong.global.security.auth;

import com.yours.emong.domain.user.dto.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(final User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getSerialNumber();
    }

    @Override
    public boolean isAccountNonExpired() { //만료
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //계정 잠김 여부 (일시적 접근 차단)
        return true; //잠기지 않음.
    }

    @Override
    public boolean isCredentialsNonExpired() { //자격 증명 만료 (비밀번호 설정 유효기간 만료)
        return true;
    }

    @Override
    public boolean isEnabled() { //계정 활성화
        return true;
    }
}
