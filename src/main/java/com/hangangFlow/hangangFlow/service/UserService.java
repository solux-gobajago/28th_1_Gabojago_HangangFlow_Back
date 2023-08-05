package com.hangangFlow.hangangFlow.service;

import com.hangangFlow.hangangFlow.domain.user.UserRepository;
import com.hangangFlow.hangangFlow.dto.JoinRequest;
import com.hangangFlow.hangangFlow.dto.LoginRequest;
import com.hangangFlow.hangangFlow.dto.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    /*
    * userId 중복체크
    * 회원가입 기능
    * 중복 시 return true
    * */
    public boolean checkUserIdDuplicate(String userId) {
        return userRepository.existsByUserId(userId);
    }

    /*
    * nickname 중복체크
    * 중복 시 return true
    * */
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    /*
    * 회원가입
    * */
    public void join(JoinRequest req) {
        userRepository.save(req.toEntity(encoder.encode(req.getPassword())));
    }

    /*
    * 로그인
    * userId와 password가 일치하면 return User
    * userId 존재하지 않거나 password 일치하지 않으면 return null*/
    public User login(LoginRequest req) {
        Optional<User> optionalUser = userRepository.findByUserId(req.getUserId());

        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        if(!user.getPassword().equals(req.getPassword())) {
            return null;
        }

        return user;
    }

    /*
    * 인증, 인가
    * userUuid 받아서 return User 없는경우는 return null */
    public User getLoginUserByUserUuid(Long userUuid) {
        if (userUuid == null) return null;

        Optional<User> optionalUser = userRepository.findById(userUuid);
        if (optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    /*
    * 인증, 인가
    * userId 받아서 return User 없는 경우는 return null */
    public User getLoginUserByUserId(String userId) {
        if (userId == null) return null;

        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }
}
