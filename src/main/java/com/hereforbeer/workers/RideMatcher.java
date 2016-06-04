package com.hereforbeer.workers;

import com.hereforbeer.domain.PassengerCandidate;
import com.hereforbeer.domain.Ride;
import com.hereforbeer.domain.RideOffer;
import com.hereforbeer.repositories.PassengerCandidateRepository;
import com.hereforbeer.repositories.RideOfferRepository;
import com.hereforbeer.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
public class RideMatcher {

    private RideOfferRepository rideOfferRepository;
    private PassengerCandidateRepository passengerCandidateRepository;
    private RideRepository rideRepository;

    @Autowired
    public RideMatcher(RideOfferRepository rideOfferRepository,
                       PassengerCandidateRepository passengerCandidateRepository,
                       RideRepository rideRepository) {
        this.rideOfferRepository = rideOfferRepository;
        this.passengerCandidateRepository = passengerCandidateRepository;
        this.rideRepository = rideRepository;
    }


    @Scheduled(cron = "0 * * * * *")
    public void MatchRides() {
        rideOfferRepository.findAll()
                .stream().forEach(offer -> {
                    List<PassengerCandidate> matchedPassengers = passengerCandidateRepository
                            .findByLocationWithin(offer.getCircle())
                            .stream()
                            .limit(offer.getSeats())
                            .collect(toList());

                    Ride ride = rideRepository.findOneById(offer.getId())
                            .orElseGet(() -> newRideFromOffer(offer));

                    List<PassengerCandidate> addedCandidates = ride.addPassengers(matchedPassengers);

                    passengerCandidateRepository.delete(addedCandidates);
                });
    }

    private Ride newRideFromOffer(RideOffer offer) {
        return Ride.builder()
                .id(offer.getId())
                .rideTime(offer.getRideDate())
                .capacity(offer.getSeats())
                .build();
    }
}
