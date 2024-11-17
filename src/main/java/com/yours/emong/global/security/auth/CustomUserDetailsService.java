package com.yours.emong.global.security.auth;

import com.yours.emong.domain.user.domain.repository.jpa.UserJpaRepository;
import com.yours.emong.domain.user.dto.User;
import com.yours.emong.domain.user.exception.UserExistException;
import com.yours.emong.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;
    private final User user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 사용자 정보를 데이터베이스에서 조회
        User user = findUserByUsername(username); // findUserByUsername()는 DB 조회 메서드로 작성 필요
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new CustomUserDetails(user); // CustomUserDetails 반환
    }

    private User findUserByUsername(String serialNumber) {
        return user.toUser(userJpaRepository.findBySerialNumber(serialNumber).orElseThrow(UserNotFoundException::new));
    }
}
