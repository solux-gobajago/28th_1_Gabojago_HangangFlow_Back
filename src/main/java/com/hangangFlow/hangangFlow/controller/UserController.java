package com.hangangFlow.hangangFlow.controller;

import com.hangangFlow.hangangFlow.dto.request.FindUserIdRequest;
import com.hangangFlow.hangangFlow.dto.request.JoinRequest;
import com.hangangFlow.hangangFlow.dto.request.PasswordResetRequest;
import com.hangangFlow.hangangFlow.domain.user.User;
import com.hangangFlow.hangangFlow.service.UserService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody JoinRequest joinRequest, BindingResult bindingResult) {

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

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }
}
