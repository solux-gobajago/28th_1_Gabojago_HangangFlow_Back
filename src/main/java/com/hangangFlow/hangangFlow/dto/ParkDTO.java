package com.hangangFlow.hangangFlow.dto;

import com.hangangFlow.hangangFlow.domain.Parks;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
@Setter
@Getter
public class ParkDTO {
    private String parkName;
    private String parkAddress;
    private String parkPhoneNum;
    private String parkInfo;
    private double latitude;
    private double longitude;

    public ParkDTO(Parks Parks){
        this.parkName = Parks.getParkName();
        this.parkAddress = Parks.getParkAddress();
        this.parkPhoneNum = Parks.getParkPhoneNum();
        this.parkInfo = Parks.getParkInfo();
        this.latitude = Parks.getLatitude();
        this.longitude = Parks.getLongitude();
    }

}
