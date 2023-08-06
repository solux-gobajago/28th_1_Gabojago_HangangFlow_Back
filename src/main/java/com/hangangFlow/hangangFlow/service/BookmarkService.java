package com.hangangFlow.hangangFlow.service;

import com.hangangFlow.hangangFlow.domain.*;
import com.hangangFlow.hangangFlow.domain.user.UserRepository;
import com.hangangFlow.hangangFlow.dto.BookmarkDTO;
import com.hangangFlow.hangangFlow.dto.BookmarkPostDTO;
import com.hangangFlow.hangangFlow.dto.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final ParkRepository parkRepository;

    // 북마크 추가
    public BookmarkDTO addBookmark(BookmarkPostDTO bookmarkPostDTO) {
        User user = userRepository.findByUserUuid(bookmarkPostDTO.getUserUuid())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + bookmarkPostDTO.getUserUuid()));

        Parks park = parkRepository.findById(bookmarkPostDTO.getParkUuid())
                .orElseThrow(() -> new IllegalArgumentException("Park not found with id: " + bookmarkPostDTO.getParkUuid()));

        Bookmarks bookmark = Bookmarks.builder()
                .user(user)
                .parks(park)
                .build();

        bookmark = bookmarkRepository.save(bookmark);
        return new BookmarkDTO(bookmark);
    }



    // 북마크 조회
    // 사용자의 북마크 조회
    public List<Bookmarks> getBookmarkList(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with user_id: " + userId));
        return bookmarkRepository.findAllByUser(user);
    }



    // 북마크 삭제
    public void deleteBookmark (UUID bookmarkUuid) {
        Bookmarks bookmark = bookmarkRepository.findById(bookmarkUuid).orElseThrow(() ->
                new IllegalArgumentException("BookmarkService 오류. bookmarkUuid = " + bookmarkUuid));

        bookmarkRepository.delete(bookmark);
    }

}


