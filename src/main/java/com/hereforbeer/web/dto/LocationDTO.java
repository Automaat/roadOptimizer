package com.hereforbeer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LocationDTO {
    private Double longitude;
    private Double latitude;

    public static LocationDTO valueOf(double[] location) {
        return new LocationDTO(location[0], location[1]);
    }

    public double[] asArray() {
        return new double[]{longitude, latitude};
    }
}
