package com.hangangFlow.hangangFlow.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "PARK")
@NoArgsConstructor
@Getter
public class Parks {

    @Id
    @Column(name = "park_uuid", columnDefinition = "BINARY(16)")
    private UUID parkUuid;

    @Column(name = "park_name", nullable = false)
    private String parkName;

    @Column(name = "park_address", nullable = false)
    private String parkAddress;

    @Column(name = "park_phone_num")
    private String parkPhoneNum;

    @Column(name = "park_info", columnDefinition = "LONGTEXT")
    private String parkInfo;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Builder
    public Parks(UUID parkUuid, String parkName, String parkAddress, String parkPhoneNum, String parkInfo, double latitude, double longitude) {
        this.parkUuid = parkUuid;
        this.parkName = parkName;
        this.parkAddress = parkAddress;
        this.parkPhoneNum = parkPhoneNum;
        this.parkInfo = parkInfo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
