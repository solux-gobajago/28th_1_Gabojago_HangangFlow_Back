package com.hangangFlow.hangangFlow.service;

import com.hangangFlow.hangangFlow.domain.user.User;
import com.hangangFlow.hangangFlow.dto.request.JoinRequest;
import com.hangangFlow.hangangFlow.dto.request.LoginRequest;
import org.springframework.context.annotation.Lazy;

import java.util.UUID;

@Lazy
public interface UserService {

    /*
     * userId 중복체크
     * 중복 시 return true
     * */
    boolean checkUserIdDuplicate(String userId);

    /*
    * 이메일 중복체크
    * 중복 시 return true
    * */
    boolean checkEmailDuplicate(String email);

    User join(JoinRequest req);

    User login(LoginRequest req);

    User getLoginUserByUserUuid(UUID userUuid);

    User getLoginUserByUserId(String userId);

    boolean resetPassword(String userId, String newPassword);

    String findUserId(String email);
}
