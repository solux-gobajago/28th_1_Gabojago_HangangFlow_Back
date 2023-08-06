package com.hangangFlow.hangangFlow.domain.park;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParkRepository extends JpaRepository<Parks, UUID> {

    List<Parks> findAllByParkNameIn(List<String> parkNames);

}
