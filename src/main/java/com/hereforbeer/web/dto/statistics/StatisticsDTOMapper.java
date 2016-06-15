package com.hereforbeer.web.dto.statistics;

import com.hereforbeer.services.statistics.TopUserCount;

public class StatisticsDTOMapper {

    public static TopUserDTO parseTopUserToDTO(TopUserCount topUserCount) {
        return TopUserDTO.builder()
                .ownerId(topUserCount.getOwnerId())
                .total(topUserCount.getTotal())
                .build();
    }
}
