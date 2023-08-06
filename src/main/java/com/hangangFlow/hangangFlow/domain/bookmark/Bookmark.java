package com.hangangFlow.hangangFlow.domain.bookmark;
import com.hangangFlow.hangangFlow.domain.user.User;
import com.hangangFlow.hangangFlow.domain.park.Parks;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
//import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.*;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookmark")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID bookmarkUuid;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    @OneToOne
    @JoinColumn(name = "park_uuid")
    private Parks parks;

//    @CreationTimestamp
//    private LocalDateTime createAt = LocalDateTime.now();

    @Builder
    public Bookmark(User user, Parks parks) {
        this.user = user;
        this.parks = parks;
    }
}
