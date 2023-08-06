package com.hangangFlow.hangangFlow.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.hangangFlow.hangangFlow.dto.request.FindUserIdRequest;
import com.hangangFlow.hangangFlow.dto.request.JoinRequest;
import com.hangangFlow.hangangFlow.dto.request.LoginRequest;
import com.hangangFlow.hangangFlow.dto.request.PasswordResetRequest;
import com.hangangFlow.hangangFlow.domain.user.User;
import com.hangangFlow.hangangFlow.service.UserService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Lazy
@RestController
//@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody JoinRequest joinRequest, BindingResult bindingResult) {
        try {

            // id 중복체크
            if (userService.checkUserIdDuplicate(joinRequest.getUserId())) {
                bindingResult.addError(new FieldError("joinRequest", "userId", "중복된 아이디 입니다"));
            }

//        // 이메일 중복체크
//        if (userService.checkEmailDuplicate(joinRequest.getEmail())) {
//            bindingResult.addError(new FieldError("joinRequest", "email", "중복된 이메일입니다"));
//        }

            // password와 passwordCheck가 같은지 확인
            if (!joinRequest.getPassword().equals(joinRequest.getPasswordCheck())) {
                bindingResult.addError(new FieldError("joinRequest", "passwordCheck", "비밀번호가 일치하지 않습니다"));
            }

            // 유효성 검사 실패시 에러발생
            if (bindingResult.hasErrors()) {

                return ResponseEntity.badRequest().build();
            }

            User newUser = userService.join(joinRequest);

            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            logger.debug("=======================Error" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/register/id/check")
    public ResponseEntity<Boolean> checkUserIdDuplicate(@RequestBody String userId) {
        boolean isDuplicate = userService.checkUserIdDuplicate(userId);
        // new throw Excpetion ...
        return ResponseEntity.ok(isDuplicate);
    }

    @PostMapping("/register/email/check")
    public ResponseEntity<Boolean> checkEmailDuplicate(@RequestBody String email) {
        boolean isDuplicate = userService.checkEmailDuplicate(email);
        return ResponseEntity.ok(isDuplicate);
    }

    // 아이디 찾기
    @PostMapping("/findId")
    public ResponseEntity<String> findUserId(@RequestBody FindUserIdRequest findUserIdRequest) {
        String userId = userService.findUserId(findUserIdRequest.getEmail());
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 비밀번호 재설정
    @PutMapping("/resetPw")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        boolean isReset = userService.resetPassword(passwordResetRequest.getUserId(), passwordResetRequest.getNewPassword());
        if (isReset) {
            return ResponseEntity.ok("비밀번호가 재설정되었습니다.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        String userId = loginRequest.getUserId();
        String password = loginRequest.getPassword();

        if (userId == null || password == null || userId.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body("아이디와 비밀번호를 입력하세요.");
        }
        
        User user = userService.login(loginRequest);

        if (user != null) {
            return ResponseEntity.ok("로그인에 성공하였습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패하였습니다. 아이디 또는 비밀번호를 확인하세요.");
        }
    }

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }
}
