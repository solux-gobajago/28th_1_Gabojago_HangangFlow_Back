package com.hangangFlow.hangangFlow.domain.likes;

import com.hangangFlow.hangangFlow.domain.community.Community;
import com.hangangFlow.hangangFlow.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "likes") //이거 이름 안바꿔 주면 충돌남 SQL 예약어라서..
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "likes_uuid",columnDefinition = "BINARY(16)")
    private UUID likesUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "community_uuid")
    private Community community;

//    public Likes(User user, Community community) {
//        this.user = user;
//        this.community = community;
//
//    }

    @Builder
    public Likes(User user, Community community) {
        this.user = user;
        this.community = community;

    }

}
