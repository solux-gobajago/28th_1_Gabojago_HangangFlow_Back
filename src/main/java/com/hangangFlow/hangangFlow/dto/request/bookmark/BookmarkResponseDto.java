package com.hangangFlow.hangangFlow.dto.request.bookmark;


import com.hangangFlow.hangangFlow.domain.Bookmark;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class BookmarkResponseDto {
    private String parkName;
    public BookmarkResponseDto(Bookmark entity) {

        this.parkName = entity.getParks().getParkName();
    }
}
