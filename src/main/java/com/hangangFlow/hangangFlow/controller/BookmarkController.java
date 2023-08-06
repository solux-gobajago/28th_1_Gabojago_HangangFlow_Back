package com.hangangFlow.hangangFlow.controller;

import com.hangangFlow.hangangFlow.domain.Bookmarks;
import com.hangangFlow.hangangFlow.dto.BookmarkDTO;
import com.hangangFlow.hangangFlow.dto.BookmarkPostDTO;
import com.hangangFlow.hangangFlow.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookmark")

@RequiredArgsConstructor
public class BookmarkController {

    //@Autowired BookmarkService bookmarkService;
    private final BookmarkService bookmarkService;

    // 북마크 추가
//    @PostMapping("")
//    public Bookmarks addBookmark(@RequestBody BookmarkPostDTO bookmarkPostDTO){
//
//        return bookmarkService.addBookmark(bookmarkPostDTO);
//    }
    @PostMapping("")
    public BookmarkDTO addBookmark(@RequestBody BookmarkPostDTO bookmarkPostDTO){

        return bookmarkService.addBookmark(bookmarkPostDTO);

    }



    // 북마크 조회
    @GetMapping("/list/{user_id}")
    public List<BookmarkDTO> getBookmark(@PathVariable("user_id") String userId) {
        List<Bookmarks> bookmarkList = bookmarkService.getBookmarkList(userId);
        return bookmarkList.stream().map(BookmarkDTO::new).collect(Collectors.toList());
    }
    /*@GetMapping("/list/{userId}")
    public List<BookmarkDTO> getBookmark(@PathVariable String userId){

        List<Bookmarks> bookmarkList = bookmarkService.getBookmarkList(userId);
        List<BookmarkDTO> bookmarkListDTO = bookmarkList.stream().map(BookmarkDTO::new).collect(Collectors.toList());

        return bookmarkListDTO;
    }*/



    // 북마크 삭제
    @DeleteMapping("/{bookmarkUuid}")
    public void delBookmark(@PathVariable UUID bookmarkUuid){

        bookmarkService.deleteBookmark(bookmarkUuid);
    }
//    public BookmarkPostDTO delBookmark(@RequestBody BookmarkPostDTO bookmarkPostDTO, Users users, Parks parks){
//        Bookmarks bookmarks = bookmarkPostDTO.toEntity(users, parks);
//        Bookmarks bookmarksAdded = bookmarkService.deleteBookmark(bookmarks);
//        bookmarkPostDTO = BookmarkPostDTO.toDTO(bookmarksAdded);
//        return bookmarkPostDTO;
//    }

}
