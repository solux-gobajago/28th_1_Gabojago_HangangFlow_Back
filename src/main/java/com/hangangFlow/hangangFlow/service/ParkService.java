package com.hangangFlow.hangangFlow.service;

import com.hangangFlow.hangangFlow.domain.ParkRepository;
import com.hangangFlow.hangangFlow.domain.Parks;
import com.hangangFlow.hangangFlow.dto.ParkDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ParkService {

    private final ParkRepository parkRepository;

    @Transactional(readOnly=true)
    public List<Parks> viewParkList(){
        List<Parks> parkList = parkRepository.findAll();

        return parkList;
    }

    public List<Parks> searchParkList (List<String> filtering) {

        // filtering된 데이터들을 기준으로 공원 찾기
        return parkRepository.findAllByParkNameIn(filtering);

    }



    // 공원 상세 페이지
    @Transactional(readOnly = true)
    public ParkDTO findPark(UUID parkUuid) {
        Parks parks = parkRepository.findById(parkUuid).orElseThrow(() ->
                new IllegalArgumentException("ParkService 오류. parkUuid = " + parkUuid));

        return new ParkDTO(parks);
    }

}
