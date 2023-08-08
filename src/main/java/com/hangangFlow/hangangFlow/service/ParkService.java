package com.hangangFlow.hangangFlow.service;

import com.hangangFlow.hangangFlow.domain.park.Parks;
import com.hangangFlow.hangangFlow.vo.ParkVO;

import java.util.List;
import java.util.UUID;

public interface ParkService {

    public List<Parks> viewParkList();

    public List<Parks> searchParkList(List<UUID> parkUuids);

    public ParkVO findPark(UUID uuid);
}
