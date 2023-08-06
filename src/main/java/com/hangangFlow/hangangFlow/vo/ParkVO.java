package com.hangangFlow.hangangFlow.vo;

import com.hangangFlow.hangangFlow.dto.Parks;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
@Setter
@Getter
public class ParkVO {
    private String parkName;
    private String parkAddress;
    private String parkPhoneNum;
    private String parkInfo;
    private double latitude;
    private double longitude;

    public ParkVO(Parks Parks){
        this.parkName = Parks.getParkName();
        this.parkAddress = Parks.getParkAddress();
        this.parkPhoneNum = Parks.getParkPhoneNum();
        this.parkInfo = Parks.getParkInfo();
        this.latitude = Parks.getLatitude();
        this.longitude = Parks.getLongitude();
    }

}
