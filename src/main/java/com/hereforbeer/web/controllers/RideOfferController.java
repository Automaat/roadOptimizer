package com.hereforbeer.web.controllers;

import com.hereforbeer.services.RideOfferService;
import com.hereforbeer.web.dto.RideOfferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
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
        return new ResponseEntity<>(CREATED);
    }

    @RequestMapping(value = "/offers/active", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RideOfferDTO>> getActiveRideOffers() {

        return new ResponseEntity<>(rideOfferService.getActiveOffers(), OK);
    }

    @RequestMapping(value = "/offers/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RideOfferDTO> getOfferById(@PathVariable(value = "id") String id) {

        return new ResponseEntity<RideOfferDTO>(rideOfferService.getOfferById(id), OK);
    }
}
