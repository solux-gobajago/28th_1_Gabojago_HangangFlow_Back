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

    @GetMapping("")
    public ResponseEntity<List<Parks>> searchPark(@RequestParam List<String> keyword) {
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

            List<UUID> parkUuidList = uuidList.stream()
                    .map(UUID::fromString)
                    .collect(Collectors.toList());

            List<Parks> parkList = parkService.searchParkList(parkUuidList);

            return ResponseEntity.ok(parkList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


//    private List<UUID> convertUuidStringsToUuids(List<String> uuidStrings) {
//        List<UUID> uuids = new ArrayList<>();
//        for (String uuidString : uuidStrings) {
//            uuids.add(UUID.fromString(uuidString));
//        }
//        return uuids;
//    }

}