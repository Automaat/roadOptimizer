package com.hereforbeer.web.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RideOfferDTO {
    private String id;
    private int seats;
    private LocationDTO start;
    private LocationDTO end;
    private String rideTime;
}
