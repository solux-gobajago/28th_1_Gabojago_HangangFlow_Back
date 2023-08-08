package com.hangangFlow.hangangFlow.controller;

import com.hangangFlow.hangangFlow.domain.park.Parks;
import com.hangangFlow.hangangFlow.service.ParkService;
import com.hangangFlow.hangangFlow.vo.ParkVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/parkList")
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

//    private final RestTemplate restTemplate;
//    private final ParkService parkService; // 추가: ParkService 주입

//    @GetMapping("")
//    public ResponseEntity<List<Parks>> searchPark(@RequestParam List<String> keyword) {
//        String flaskApiUrl = "http://localhost:8000/data?keyword=" + String.join(",", keyword);
//        System.out.println("checkLog--- flaskApiUrl" + flaskApiUrl);
//        try {
//            ResponseEntity<Map<String, List<String>>> flaskResponse = restTemplate.exchange(
//                    flaskApiUrl,
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<Map<String, List<String>>>() {}
//            );
//
//            List<String> uuidList = flaskResponse.getBody().get("park_uuid");
//            System.out.println("checkLog--- uuidList" + uuidList);
//
//            List<UUID> parkUuidList = uuidList.stream()
//                    .map(UUID::fromString)
//                    .collect(Collectors.toList());
//            System.out.println("checkLog--- parkUuidList" + uuidList);
//
//            List<Parks> parkList = parkService.searchParkList(parkUuidList); // 수정: ParkService의 메서드 활용
//            System.out.println("checkLog--- parkUuidList" + parkList);
//            return ResponseEntity.ok(parkList);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @GetMapping("")
    public ResponseEntity<List<ParkVO>> searchPark(@RequestParam List<String> keyword) {
        String flaskApiUrl = "http://localhost:8000/data?keyword=" + String.join(",", keyword);
        System.out.println("checkLog--- flaskApiUrl" + flaskApiUrl);
        try {
            ResponseEntity<Map<String, List<String>>> flaskResponse = restTemplate.exchange(
                    flaskApiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, List<String>>>() {}
            );

            List<String> uuidList = flaskResponse.getBody().get("park_uuid");
            System.out.println("checkLog--- uuidList" + uuidList);

            List<UUID> parkUuidList = uuidList.stream()
                    .map(UUID::fromString)
                    .collect(Collectors.toList());
            System.out.println("checkLog--- parkUuidList" + parkUuidList);

            List<Parks> parkList = parkService.searchParkList(parkUuidList); // 수정: 공원 정보 가져오기

            List<ParkVO> parkVOList = parkList.stream().map(ParkVO::new).collect(Collectors.toList());

            return ResponseEntity.ok(parkVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}