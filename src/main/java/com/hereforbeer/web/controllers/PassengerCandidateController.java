package com.hereforbeer.web.controllers;

import com.hereforbeer.services.PassengerCandidateService;
import com.hereforbeer.web.dto.PassengerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class PassengerCandidateController {

    private final PassengerCandidateService passengerCandidateService;

    @Autowired
    public PassengerCandidateController(PassengerCandidateService passengerCandidateService) {
        this.passengerCandidateService = passengerCandidateService;
    }

    @RequestMapping(value = "/candidates", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCandidate(@RequestBody PassengerDTO passengerDTO,
                                           @RequestHeader("X-AUTH-TOKEN") String headerNick) {

        passengerCandidateService.saveCandidate(passengerDTO, headerNick);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
