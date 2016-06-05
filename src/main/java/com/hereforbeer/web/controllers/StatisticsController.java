package com.hereforbeer.web.controllers;

import com.hereforbeer.services.StatisticsService;
import com.hereforbeer.web.dto.statistics.SuccessComparisionDTO;
import com.hereforbeer.web.dto.statistics.TopUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("api/statistics")
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @RequestMapping(value = "/success_compare", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessComparisionDTO> getSuccessfulOffersStats() {

        return new ResponseEntity<>(statisticsService.getSuccessfulOffersStats(), OK);
    }

    @RequestMapping(value = "/top_users", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TopUserDTO>> getTopUsers() {

        return new ResponseEntity<>(statisticsService.getTopUser(), OK);
    }
}
