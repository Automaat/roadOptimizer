package com.hereforbeer.web.controllers;

import com.hereforbeer.services.RideOfferService;
import com.hereforbeer.web.dto.RideOfferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping("/api")
public class RideOfferController {
    private final RideOfferService rideOfferService;

    @Autowired
    public RideOfferController(RideOfferService rideOfferService) {
        this.rideOfferService = rideOfferService;
    }

    @RequestMapping(value = "/offers", method = POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<RideOfferDTO> createEvent(@RequestBody RideOfferDTO rideOfferDTO,
                                                    @RequestHeader("X-AUTH-TOKEN") String headerNick) {
        rideOfferService.addRideOffer(rideOfferDTO, headerNick);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
