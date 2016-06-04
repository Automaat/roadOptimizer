package com.hereforbeer.web.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {
    private String firstName;
    private String lastName;
    private LocationDTO address;
    private String rideTime;
}
