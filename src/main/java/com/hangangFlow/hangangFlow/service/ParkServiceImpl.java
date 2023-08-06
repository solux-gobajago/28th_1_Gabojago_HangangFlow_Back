package com.hangangFlow.hangangFlow.service;

import com.hangangFlow.hangangFlow.domain.park.ParkRepository;
import com.hangangFlow.hangangFlow.domain.park.Parks;
import com.hangangFlow.hangangFlow.vo.ParkVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Component
@Transactional
@RequiredArgsConstructor
public class ParkServiceImpl implements ParkService {

//    @Autowired
    private ParkRepository parkRepository;
    @Autowired
    public ParkServiceImpl(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    @Override
    public List<Parks> viewParkList(){
        List<Parks> parkList = parkRepository.findAll();

        return parkList;
    }

    @Override
    public List<Parks> searchParkList (List<String> filtering) {

        // filtering된 데이터들을 기준으로 공원 찾기
        return parkRepository.findAllByParkNameIn(filtering);

    }

    @Override
    public ParkVO findPark(UUID parkUuid) {
        Parks parks = parkRepository.findById(parkUuid).orElseThrow(() ->
                new IllegalArgumentException("ParkService 오류. parkUuid = " + parkUuid));

        return new ParkVO(parks);
    }
}
