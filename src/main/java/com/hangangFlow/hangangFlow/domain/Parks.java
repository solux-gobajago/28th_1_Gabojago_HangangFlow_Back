package com.hangangFlow.hangangFlow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//import java.util.UUID;

@Entity
@Table(name = "PARK")
@NoArgsConstructor
@Getter
public class Parks {

//    @Id //@GeneratedValue(strategy = GenerationType.IDENTITY)
//    //@GeneratedValue(generator = "uuid2")
//    //@GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @Column(name = "park_uuid", columnDefinition = "BINARY(16)")
    @Id
    //@GeneratedValue
    //@Column(name = "park_uuid", columnDefinition = "BINARY(16)")
    @Column(name = "park_uuid")
    //private UUID parkUuid;
    private int parkUuid;

    @NotNull
    private int parkNo;

    @NotNull
    private String parkName;

    @NotNull
    private String parkAddress;

    private String parkPhoneNum;

    private String parkInfo;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    @Builder
    //public Parks(UUID parkUuid, int parkNo, String parkName, String parkAddress, String parkPhoneNum, String parkInfo, double latitude, double longitude) {
    public Parks(int parkUuid, int parkNo, String parkName, String parkAddress, String parkPhoneNum, String parkInfo, double latitude, double longitude) {
        this.parkUuid = parkUuid;
        this.parkNo = parkNo;
        this.parkName = parkName;
        this.parkAddress = parkAddress;
        this.parkPhoneNum = parkPhoneNum;
        this.parkInfo = parkInfo;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
