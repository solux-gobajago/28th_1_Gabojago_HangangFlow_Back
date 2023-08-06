package com.hangangFlow.hangangFlow.service;

import com.hangangFlow.hangangFlow.domain.user.UserRepository;
import com.hangangFlow.hangangFlow.domain.user.UserRole;
import com.hangangFlow.hangangFlow.dto.JoinRequest;
import com.hangangFlow.hangangFlow.dto.LoginRequest;
import com.hangangFlow.hangangFlow.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final @Qualifier("passwordEncoder") BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + userId));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), authorities);
    }

    /*
     * userId 중복체크
     * 중복 시 return true
     * */
    public boolean checkUserIdDuplicate(String userId) {
        return userRepository.existsByUserId(userId);
    }

    /*
     * 이메일 중복체크
     * 중복 시 return true
     * */
    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    /*
     * 회원가입
     * */
    public User join(JoinRequest req) {
        User newUser = User.builder()
                .userId(req.getUserId())
                .email(req.getEmail())
                .nickname(req.getNickname())
                .password(encoder.encode(req.getPassword()))
                .role(UserRole.USER) // 기본 권한은 USER로 설정
                .build();

        return userRepository.save(newUser);
    }

    /*
     * 로그인
     * userId와 password가 일치하면 return User
     * userId 존재하지 않거나 password 일치하지 않으면 return null
     * */
    public User login(LoginRequest req) {
        User user = userRepository.findByUserId(req.getUserId())
                .orElse(null);

        if (user == null || !encoder.matches(req.getPassword(), user.getPassword())) {
            return null;
        }

        return user;
    }

    /*
     * 인증, 인가
     * userUuid 받아서 return User 없는 경우는 return null
     * */
    public User getLoginUserByUserUuid(Long userUuid) {
        return userRepository.findById(userUuid)
                .orElse(null);
    }

    /*
     * 인증, 인가
     * userId 받아서 return User 없는 경우는 return null
     * */
    public User getLoginUserByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElse(null);
    }

    /*
     * 비밀번호 재설정
     * userId와 newPassword를 받아서 비밀번호 재설정
     * */
    public boolean resetPassword(String userId, String newPassword) {
        User user = userRepository.findByUserId(userId)
                .orElse(null);

        if (user == null) {
            return false;
        }

        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }

    /*
     * 이메일을 기반으로 아이디 찾기
     * */
    public String findUserId(String email) {
        User user = userRepository.findByEmail(email)
                .orElse(null);

        return user != null ? user.getUserId() : null;
    }
}
