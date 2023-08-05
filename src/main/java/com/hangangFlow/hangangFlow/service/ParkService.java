package com.hangangFlow.hangangFlow.service;

import com.hangangFlow.hangangFlow.domain.ParkRepository;
import com.hangangFlow.hangangFlow.domain.Parks;
import com.hangangFlow.hangangFlow.dto.ParkDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParkService {

    private final ParkRepository parkRepository;

//    @Transactional(readOnly = true)
//    public ParkDTO findPark(int parkNo) {
//        Parks park = parkRepository.findByParkId(parkNo)
//                .orElseThrow(() -> new IllegalArgumentException("해당 parkNo에 해당하는 공원이 없습니다. parkNo = " + parkNo));
//        return new ParkDTO(park);
//    }

    @Transactional(readOnly=true)
    public ParkDTO findPark(int parkUuid) {

        Parks parks = parkRepository.findById(parkUuid).orElseThrow(() ->
                new IllegalArgumentException("ParkService 오류. parkUuid = " + parkUuid));

        return new ParkDTO(parks);
    }
}
