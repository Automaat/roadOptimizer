package com.hereforbeer.services.statistics;

import lombok.Data;

@Data
public class TopUserCount {

    private String ownerId;
    private long total;
}
