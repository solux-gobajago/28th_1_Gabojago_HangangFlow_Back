package com.hangangFlow.hangangFlow.dto;

//import jakarta.persistence.*;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "PARK")
@NoArgsConstructor
public class Parks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
