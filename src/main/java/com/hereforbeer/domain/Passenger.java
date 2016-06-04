package com.hereforbeer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    private String firstname;

    private String lastname;

    private Point point;
}
