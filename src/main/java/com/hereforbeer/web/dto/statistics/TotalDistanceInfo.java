package com.hereforbeer.web.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalDistanceInfo {

    private long kilometers;
    private long fuel;
    private double price;
    private double saved;
    private long savedFuel;
}
