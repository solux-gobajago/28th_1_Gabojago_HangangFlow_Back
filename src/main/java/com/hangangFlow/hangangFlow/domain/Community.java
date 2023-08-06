package com.hangangFlow.hangangFlow.domain;


//import jakarta.persistence.*;
import com.hangangFlow.hangangFlow.domain.user.User;
import com.hangangFlow.hangangFlow.domain.park.Parks;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID communityUuid;

    @Column(columnDefinition = "TEXT")
    private String article;

    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime updateAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    @OneToOne
    @JoinColumn(name = "park_uuid")
    private Parks parks;

    @Builder
    public Community(String article, User user, Parks parks) {
        this.article = article;
        this.user = user;
        this.parks = parks;
    }

    public void update(String article) {
        this.article = article;
    }

    public String getUserNickname() {
        return this.user.getNickname();
    }

}
