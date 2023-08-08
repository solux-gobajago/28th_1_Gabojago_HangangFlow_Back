package com.hangangFlow.hangangFlow.controller;

import com.hangangFlow.hangangFlow.domain.park.Parks;
import com.hangangFlow.hangangFlow.service.ParkService;
import com.hangangFlow.hangangFlow.vo.ParkVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Controller
@RequestMapping("/api/parkInfo")
@RequiredArgsConstructor
public class ParkController {

    @Autowired
    private final ParkService parkService;

    @Autowired
    private final RestTemplate restTemplate;
    @GetMapping("/parklist")
    public List<ParkVO> listPark() {
        List<Parks> parkList = parkService.viewParkList();
        List<ParkVO> parkListVO = parkList.stream().map(ParkVO::new).collect(Collectors.toList());

        return parkListVO;
    }

    // flask 통해서 공원 리스트
    // keywords에 flask의 JSON 데이터가 들어감
//    @GetMapping("/searchpark")
//    public List<ParkVO> searchPark(@RequestBody Map<String, List<UUID>> requestBody) {
//        List<UUID> parkUuidList = requestBody.get("park_uuid");
//
//        // MariaDB에서 데이터 찾기
//        List<Parks> result = parkService.searchParkList(parkUuidList);
//
//        // ParkVO로 변환하여 반환
//        return result.stream().map(ParkVO::new).collect(Collectors.toList());
//    }

//    @GetMapping("/searchPark")
//    public ResponseEntity<List<ParkVO> searchPark(@RequestParam String params) {
//        List<String> keywordList = ArrayList.
//    }

    // 공원 상세보기
    @GetMapping("/{parkUuid}")
    public ResponseEntity<List<ParkVO>> searchPark(@RequestParam String keywords) {

        String flaskApiUrl = "http://flask-data-server-url/flask-endpoint?keywords=" + keywords;
        ResponseEntity<String[]> flaskResponse = restTemplate.getForEntity(flaskApiUrl, String[].class);
        List<UUID> parkUuids = convertUuidStringsToUuids(Arrays.asList(Objects.requireNonNull(flaskResponse.getBody())));

        List<Parks> parkList = parkService.searchParkList(parkUuids);

        List<ParkVO> parkVOList = parkList.stream()
                .map(ParkVO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(parkVOList);
    }

    private List<UUID> convertUuidStringsToUuids(List<String> uuidStrings) {
        List<UUID> uuids = new ArrayList<>();
        for (String uuidString : uuidStrings) {
            uuids.add(UUID.fromString(uuidString));
        }
        return uuids;
    }

}