package com.hangangFlow.hangangFlow.domain.user;

import com.hangangFlow.hangangFlow.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);
    Optional<User> findByUserId(String userId);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);



    Optional<User> findByUserUuid(UUID userUuid);
}