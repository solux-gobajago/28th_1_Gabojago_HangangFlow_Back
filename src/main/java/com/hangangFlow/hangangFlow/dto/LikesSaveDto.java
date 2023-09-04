package com.hangangFlow.hangangFlow.dto;

import com.hangangFlow.hangangFlow.domain.community.Community;
import com.hangangFlow.hangangFlow.domain.likes.Likes;
import com.hangangFlow.hangangFlow.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class LikesSaveDto {
    private UUID userUuid;
    private UUID communityUuid;

    @Builder
    public LikesSaveDto(UUID userUuid, UUID communityUuid) {
        this.userUuid = userUuid;
        this.communityUuid = communityUuid;
    }

    //dto -> entity
    public Likes toEntity(User user, Community community) {
        return Likes.builder()
                .user(user)
                .community(community)
                .build();
    }

}
