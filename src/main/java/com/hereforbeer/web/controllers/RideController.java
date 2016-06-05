package com.hereforbeer.web.controllers;

import com.hereforbeer.services.RideService;
import com.hereforbeer.web.dto.RideDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/rides/owned", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RideDTO>> getOwnerRides(@RequestHeader("X-AUTH-TOKEN") String nick) {
        List<RideDTO> ridesDTOs = rideService.getOwnerRides(nick);
        return new ResponseEntity<>(ridesDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/rides/participates", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RideDTO>> getPassengerRides(@RequestHeader("X-AUTH-TOKEN") String nick) {
        List<RideDTO> ridesDTOs = rideService.getPassengerRides(nick);
        return new ResponseEntity<>(ridesDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/rides", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RideDTO>> getAllRides() {
        List<RideDTO> ridesDTOs = rideService.getAllActualRides();
        return new ResponseEntity<>(ridesDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/rides/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RideDTO> getRide(@PathVariable("id") String id) {
        RideDTO rideDTO = rideService.getRide(id);
        return new ResponseEntity<>(rideDTO, HttpStatus.OK);
    }
}
