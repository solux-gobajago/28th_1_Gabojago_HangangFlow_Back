package com.hangangFlow.hangangFlow.service;

import com.hangangFlow.hangangFlow.domain.bookmark.Bookmark;
import com.hangangFlow.hangangFlow.dto.BookmarkResponseDto;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.UUID;

@Lazy

public interface BookmarkService {
    Bookmark saveBookmark(UUID userUuid, UUID parkUuid);

    List<BookmarkResponseDto> findAllBookmark(UUID userUuid);
    Bookmark deleteBookmark(UUID bookmarkUuid);

}
