package com.hangangFlow.hangangFlow.controller;

import com.hangangFlow.hangangFlow.dto.Parks;
import com.hangangFlow.hangangFlow.service.ParkService;
import com.hangangFlow.hangangFlow.vo.ParkVO;
import com.hangangFlow.hangangFlow.service.ParkServicetemp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Controller
@RequestMapping("/api/parkInfo")
@RequiredArgsConstructor
public class ParkController {

    private final ParkService parkService;
    @GetMapping("/parklist")
    public List<ParkVO> listPark() {
        List<Parks> parkList = parkService.viewParkList();
        List<ParkVO> parkListDTO = parkList.stream().map(ParkVO::new).collect(Collectors.toList());

        return parkListDTO;
    }



    // flask 통해서 공원 리스트
    // keywords에 flask의 JSON 데이터가 들어감
    @PostMapping("/searchpark")
    public List<ParkVO> searchPark(@RequestBody List<String> keywords) {

        // Flask에서 받아온 데이터를 기준으로 공원 이름들을 생성
        List<String> transformedKeywords = keywords.stream()
                .map(keyword -> keyword + "한강공원")
                .collect(Collectors.toList());

        // MariaDB에서 데이터 찾기
        List<Parks> result = parkService.searchParkList(transformedKeywords);

        // ParkDTO로 변환하여 반환
        return result.stream().map(ParkVO::new).collect(Collectors.toList());
    }



    // 공원 상세보기
    @GetMapping("/{parkUuid}")
    public ParkVO findPark(@PathVariable UUID parkUuid) {

        return parkService.findPark(parkUuid);
    }

}
