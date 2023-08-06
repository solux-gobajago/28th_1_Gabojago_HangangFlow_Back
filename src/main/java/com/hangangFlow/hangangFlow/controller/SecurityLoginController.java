package com.hangangFlow.hangangFlow.controller;

import com.hangangFlow.hangangFlow.dto.JoinRequest;
import com.hangangFlow.hangangFlow.dto.LoginRequest;
import jakarta.validation.Valid;
//import org.aspectj.apache.bcel.classfile.Field;
import org.aspectj.apache.bcel.classfile.Field;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.ui.Model;
import com.hangangFlow.hangangFlow.dto.User;
import com.hangangFlow.hangangFlow.service.UserService;
import lombok.RequiredArgsConstructor;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/api/security-login")
public class SecurityLoginController {
    private final UserService userService;

    // 로그인 관련
    @GetMapping(value = {"","/"})
    public String home(Model model, Authentication auth) {
        model.addAttribute("loginType", "securtiy-login");
        model.addAttribute("pageName", "Security 로그인");

        if (auth != null) {
            User loginUser = userService.getLoginUserByUserId(auth.name());
            if (loginUser != null) {
                model.addAttribute("nickname", loginUser.getNickname());
            }
        }
        return "home";
    }

    @GetMapping("/join")
    public String joinPage(Model model) {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");
        model.addAttribute("JoinRequest", new JoinRequest());

        return "join";
    }

//    @PostMapping("/join")
//    public String join(@Valid @ModelAttribute("JoinRequest") JoinRequest joinRequest, BindingResult bindingResult, Model model) {
//        model.addAttribute("loginType", "security-login");
//        model.addAttribute("pageName", "Security 로그인");
//
//        // id 중복체크
//        if (userService.checkUserIdDuplicate(joinRequest.getUserId())) {
//            bindingResult.addError(new FieldError("joinRequest", "userId", "중복된 아이디입니다"));
//        }
//
//        // 닉네임 중복체크
//        if (userService.checkNicknameDuplicate(joinRequest.getNickname())) {
//            bindingResult.addError(new FieldError("joinRequest", "nickname", "중복된 닉네임입니다"));
//        }
//
//        // password와 passwordCheck가 같은지 확인
//        if (!joinRequest.getPassword().equals(joinRequest.getPasswordCheck())) {
//            bindingResult.addError(new FieldError("joinRequest", "passwordCheck", "비밀번호가 일치하지 않습니다"));
//        }
//
//        if (bindingResult.hasErrors()) {
//            return "join";
//        }
//
//        userService.join(joinRequest);
//
//        return "redirect:/api/security-login";
//    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginType","security-login");
        model.addAttribute("pageName", "Security 로그인");

        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping("/info")
    public String userInfo(Model model, Authentication auth) {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        User loginUser = userService.getLoginUserByUserId(auth.name());

        if (loginUser == null) {
            return "redirect:/api/security-login/login";
        }

        model.addAttribute("user", loginUser);

        return "info";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        return "admin";
    }
}
