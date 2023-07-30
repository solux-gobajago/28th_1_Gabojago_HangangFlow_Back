package com.hangangFlow.hangangFlow.controller;

import com.hangangFlow.hangangFlow.dto.ParkDTO;
import com.hangangFlow.hangangFlow.service.ParkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//import java.util.UUID;

@RestController
@RequestMapping("/api/parkInfo")
@RequiredArgsConstructor
public class ParkController {

    private final ParkService parkService;

//    @GetMapping("/{parkNo}")
//    public ParkDTO findPark(@PathVariable int parkNo) {
//        return parkService.findPark(parkNo);
//    }

    @GetMapping("/{parkUuid}")
    public ParkDTO findPark(@PathVariable int parkUuid) {
        return parkService.findPark(parkUuid);
    }
}
