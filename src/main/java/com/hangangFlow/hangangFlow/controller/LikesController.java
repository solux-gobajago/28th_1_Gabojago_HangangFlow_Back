package com.hangangFlow.hangangFlow.controller;

import com.hangangFlow.hangangFlow.domain.community.CommunityRepository;
import com.hangangFlow.hangangFlow.dto.request.LikesSaveDto;
import com.hangangFlow.hangangFlow.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

@Lazy
@RestController
@RequestMapping("/api")
public class LikesController {
    @Resource
    private final LikesService likesService;
    private final CommunityRepository communityRepository;

    @Autowired
    public LikesController(LikesService likesService, CommunityRepository communityRepository) {
        this.likesService = likesService;
        this.communityRepository = communityRepository;
    }

    //좋아요 한번 누르면 +1, 다시 누르면 -1
    @PostMapping("/likes/{communityUuid}")
    public ResponseEntity AddLikes(@PathVariable UUID communityUuid,
                                   @RequestBody LikesSaveDto likesSaveDto) {
        likesService.addLikes(communityUuid, likesSaveDto.getUserUuid());
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
