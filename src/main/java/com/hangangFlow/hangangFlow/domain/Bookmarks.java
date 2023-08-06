package com.hangangFlow.hangangFlow.domain;

import com.hangangFlow.hangangFlow.dto.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
//@Table(name = "BOOKMARK")
@Table(name = "testbookmarks")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Bookmarks {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookmark_uuid")
    private UUID bookmarkUuid;

    //@NotNull
    @ManyToOne(targetEntity = Parks.class)
    //@JoinColumn(name = "park_uuid")
    @JoinColumn(name = "park_uuid", referencedColumnName = "park_uuid")
    private Parks parks;

    //@NotNull
    @ManyToOne(targetEntity = User.class)
    //@JoinColumn(name = "user_uuid")
    @JoinColumn(name = "user_uuid", referencedColumnName = "user_uuid")
    private User user;

    @NotNull
    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();



    @Builder
    public Bookmarks(Parks parks, User user){
        this.parks = parks;
        this.user = user;
    }

}

