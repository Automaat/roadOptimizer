package com.hereforbeer.web.dto;

import lombok.*;
import org.springframework.data.geo.Point;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    private Double longitude;
    private Double latitude;

    public static LocationDTO valueOf(double[] location) {
        return new LocationDTO(location[0], location[1]);
    }

    public double[] asArray() {
        return new double[]{longitude, latitude};
    }

    public Point asPoint() {
        return new Point(longitude, latitude);
    }
}
