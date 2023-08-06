package com.hangangFlow.hangangFlow.dto;

import com.hangangFlow.hangangFlow.domain.community.Community;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class CommunityResponseDto {
    private UUID communityUuid;
    private String article;
    private LocalDateTime createAt;
    private String userNickname;

    public CommunityResponseDto(Community entity) {
        this.communityUuid = entity.getCommunityUuid();
        this.article= entity.getArticle();
        this.createAt = entity.getCreateAt();
        this.userNickname = entity.getUser().getNickname();

    }
}
