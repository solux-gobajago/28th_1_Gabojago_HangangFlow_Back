package com.hangangFlow.hangangFlow.domain.likes;

import com.hangangFlow.hangangFlow.domain.community.Community;
import com.hangangFlow.hangangFlow.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikesRepository extends JpaRepository<Likes, UUID> {
    //있는지 없는지 검토
    boolean existsByUserAndCommunity(User user, Community community);
    //삭제
    void deleteByUserAndCommunity(User user, Community community);

}
