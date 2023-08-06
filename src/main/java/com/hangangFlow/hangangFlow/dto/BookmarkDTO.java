package com.hangangFlow.hangangFlow.dto;

import com.hangangFlow.hangangFlow.domain.Bookmarks;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Setter
@Getter
public class BookmarkDTO {
    private UUID bookmarkUuid;
    private UUID parkUuid;
    private UUID userUuid;
    private LocalDateTime createdAt;



    public BookmarkDTO(Bookmarks bookmarks){
        this.bookmarkUuid = bookmarks.getBookmarkUuid();
        this.parkUuid = bookmarks.getParks().getParkUuid();
        this.userUuid = bookmarks.getUser().getUserUuid();
        this.createdAt = bookmarks.getCreatedAt();
    }

}
