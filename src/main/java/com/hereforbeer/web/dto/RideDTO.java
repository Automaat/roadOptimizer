package com.hereforbeer.web.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RideDTO {
    private String id;
    private List<LocationDTO> checkpoints;
    private String rideTime;
    private List<PassengerDTO> passengers;
    private LocationDTO start;
    private LocationDTO end;

}
