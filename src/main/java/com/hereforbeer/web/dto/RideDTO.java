package com.hereforbeer.web.dto;

import com.hereforbeer.domain.Passenger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RideDTO {
    private String id;
    private List<LocationDTO> checkpoints;
    private int departureTime;
    private List<PassengerDTO> passengers;

}
