package com.hangangFlow.hangangFlow.dto;

import com.hangangFlow.hangangFlow.domain.bookmark.Bookmark;
import lombok.Getter;

@Getter
public class BookmarkResponseDto {
    private String parkName;
    public BookmarkResponseDto(Bookmark entity) {

        this.parkName = entity.getParks().getParkName();
    }
}
