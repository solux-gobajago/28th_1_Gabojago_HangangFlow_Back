package com.hangangFlow.hangangFlow.domain;

import com.hangangFlow.hangangFlow.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmarks, UUID> {

    public List<Bookmarks> findAllByUser(User userId);

}
