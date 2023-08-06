package com.hangangFlow.hangangFlow.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommunityListVO {
    private UUID communityUuid;
    private String article;
    private String userNickname;
    private String parkName;
    private LocalDateTime createAt;

//    public CommunityListDto(Community entity) {
//        this.communityUuid = entity.getCommunityUuid();
//        this.article = entity.getArticle();
//        this.userNickname = entity.getUser().getNickname();
//        this.parkName = entity.getParks().getParkName();
//        this.createAt = entity.getCreateAt();
//    }
}
