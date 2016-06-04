package com.hereforbeer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PassengerDTO {
    private String firstname;
    private String lastname;
    private LocationDTO point;
}
