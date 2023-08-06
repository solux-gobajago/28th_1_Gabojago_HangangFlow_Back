package com.hangangFlow.hangangFlow.service;

import com.hangangFlow.hangangFlow.dto.User;
import com.hangangFlow.hangangFlow.domain.user.UserRepository;
import com.hangangFlow.hangangFlow.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
* UserDetailsService
* 유저 정보 가져오는 인터페이스
* */
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /*
    * 패스워드 알아서 체킹해서 신경쓸 필요 없음
    * 리턴 잘되면 세션 자동 생성
    * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다");
                });
        return new PrincipalDetails(user);
    }

}
