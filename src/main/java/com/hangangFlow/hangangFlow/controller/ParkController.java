package com.hangangFlow.hangangFlow.controller;

import com.hangangFlow.hangangFlow.domain.Parks;
import com.hangangFlow.hangangFlow.dto.ParkDTO;
import com.hangangFlow.hangangFlow.service.ParkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parkInfo")
@RequiredArgsConstructor
public class ParkController {

    private final ParkService parkService;
    @GetMapping("/parklist")
    public List<ParkDTO> listPark() {
        List<Parks> parkList = parkService.viewParkList();
        List<ParkDTO> parkListDTO = parkList.stream().map(ParkDTO::new).collect(Collectors.toList());

        return parkListDTO;
    }



    // flask 통해서 공원 리스트
    // keywords에 flask의 JSON 데이터가 들어감
    @GetMapping("/searchpark")
    public List<ParkDTO> searchPark(@RequestBody List<String> keywords) {

        // Flask에서 받아온 데이터를 기준으로 공원 이름들을 생성
        List<String> transformedKeywords = keywords.stream()
                .map(keyword -> keyword + "한강공원")
                .collect(Collectors.toList());

        // MariaDB에서 데이터 찾기
        List<Parks> result = parkService.searchParkList(transformedKeywords);

        // ParkDTO로 변환하여 반환
        return result.stream().map(ParkDTO::new).collect(Collectors.toList());
    }



    // 공원 상세보기
    @GetMapping("/{parkUuid}")
    public ParkDTO findPark(@PathVariable UUID parkUuid) {

        return parkService.findPark(parkUuid);
    }

}
