package com.hangangFlow.hangangFlow.dto;

import com.hangangFlow.hangangFlow.domain.Bookmarks;
import com.hangangFlow.hangangFlow.domain.Parks;
import lombok.*;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor

@Setter
@Getter
public class BookmarkPostDTO {
    private UUID parkUuid;
    private UUID userUuid;

    //Bookmarks의 @Builder 관련
    public static Bookmarks toEntity(Parks park){
        return Bookmarks.builder()
                .parks(park)
                .build();
    }

}
