package com.hangangFlow.hangangFlow.service;

import com.hangangFlow.hangangFlow.domain.community.Community;
import com.hangangFlow.hangangFlow.domain.park.Parks;
import com.hangangFlow.hangangFlow.domain.user.User;
import com.hangangFlow.hangangFlow.dto.*;
import com.hangangFlow.hangangFlow.vo.CommunityListParkVO;
import com.hangangFlow.hangangFlow.vo.CommunityListVO;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.UUID;

@Lazy
public interface CommunityService {

    Community saveCommunity(User user, Parks parks, CommunitySaveDto article);

    Community updateCommunity(UUID communityUuid, CommunityUpdateRequestDto article);

    Community deleteCommunity(UUID communityUuid);

    CommunityResponseDto findCommunity(UUID communityUuid);

    List<CommunityListVO> findAllDesc();

    List<CommunityListParkVO> findAllPark(String parkName);
}
