package com.hereforbeer.web.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreebieStatisticsDTO {

    private double freebieUser;
    private double payingUsers;
}
