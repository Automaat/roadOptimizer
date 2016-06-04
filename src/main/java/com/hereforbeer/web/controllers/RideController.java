package com.hereforbeer.web.controllers;

import com.hereforbeer.services.RideService;
import com.hereforbeer.web.dto.RideDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api")
public class RideController {
    private final RideService rideService;

    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @RequestMapping(value = "/rides", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RideDTO>> getOwnerRides(@RequestParam("ownerId") String ownerId) {
        List<RideDTO> ridesDTOs = rideService.getOwnerRides(ownerId);
        return new ResponseEntity<>(ridesDTOs, HttpStatus.OK);
    }
}
