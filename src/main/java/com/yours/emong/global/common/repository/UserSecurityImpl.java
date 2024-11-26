package com.yours.emong.global.common.repository;

import com.yours.emong.domain.user.dto.User;
import com.yours.emong.global.security.auth.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserSecurityImpl implements UserSecurity{
    @Override
    public User getUser() {
        return ((CustomUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();
    }
}
//로그인 성공으로 저장된 유저의 정보를 CustomUserDetails 형식으로 바꾸어 반환.