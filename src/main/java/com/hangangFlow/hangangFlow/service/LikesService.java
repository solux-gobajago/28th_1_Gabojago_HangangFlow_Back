package com.hangangFlow.hangangFlow.service;

import com.hangangFlow.hangangFlow.domain.user.User;
import org.springframework.context.annotation.Lazy;

import java.util.UUID;

@Lazy
public interface LikesService {
    void addLikes(UUID communityUuid, UUID userUuid);

    void deleteLikes(UUID communityUuid, UUID likesUuid);
}

