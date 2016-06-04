package com.hereforbeer.services;

import com.hereforbeer.repositories.RideOfferRepository;
import com.hereforbeer.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideService {

    private RideRepository rideRepository;
    private RideOfferRepository offerRepository;

    @Autowired
    public RideService(RideRepository rideRepository, RideOfferRepository offerRepository) {
        this.rideRepository = rideRepository;
        this.offerRepository = offerRepository;
    }

}
