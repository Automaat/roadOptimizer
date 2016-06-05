package com.hereforbeer.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PassengerDTO {
    private String firstName;
    private String lastName;
    private LocationDTO location;
    private String rideTime;
}
