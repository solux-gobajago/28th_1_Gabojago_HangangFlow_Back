package com.hangangFlow.hangangFlow.dto;

import com.hangangFlow.hangangFlow.domain.Community;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommunityListParkDto {
    private UUID communityUuid;
    private String article;
    private String userNickname;
    private String parkName;
    private LocalDateTime createAt;

    public CommunityListParkDto(Community entity) {
        this.communityUuid = entity.getCommunityUuid();
        this.article = entity.getArticle();
        this.userNickname = entity.getUser().getNickname();
        this.parkName = entity.getParks().getParkName();
        this.createAt = entity.getCreateAt();
    }
}
