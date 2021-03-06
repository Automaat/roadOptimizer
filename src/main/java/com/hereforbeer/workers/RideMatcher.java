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

import static java.util.stream.Collectors.toList;

@Component
public class RideMatcher {

    public static final long DELTA_MINUTES = 10L;
    public static final String CRON_PROPERTY = "${road_optimizer.matcher.cron}";

    private final RideOfferRepository rideOfferRepository;
    private final PassengerCandidateRepository passengerCandidateRepository;
    private final RideRepository rideRepository;

    @Autowired
    public RideMatcher(RideOfferRepository rideOfferRepository,
                       PassengerCandidateRepository passengerCandidateRepository,
                       RideRepository rideRepository) {
        this.rideOfferRepository = rideOfferRepository;
        this.passengerCandidateRepository = passengerCandidateRepository;
        this.rideRepository = rideRepository;
    }


    @Scheduled(cron = CRON_PROPERTY)
    public void MatchRides() {
        rideOfferRepository.findByActualIsTrue()
                .stream().forEach(offer -> {
                    List<PassengerCandidate> matchedPassengers = passengerCandidateRepository
                            .findByLocationWithinAndRideTimeBetween(offer.getCircle(),
                                    offer.getRideTime().minusMinutes(DELTA_MINUTES),
                                    offer.getRideTime().plusMinutes(DELTA_MINUTES))
                            .stream()
                            .limit(offer.getSeats())
                            .collect(toList());

                    if (!matchedPassengers.isEmpty()) {
                        Ride ride = rideRepository.findOneById(offer.getId())
                                .orElseGet(() -> newRideFromOffer(offer));

                        List<PassengerCandidate> addedCandidates = ride.addPassengers(matchedPassengers);

                        if (ride.getCapacity() == 0) {
                            offer.setActual(false);
                            rideOfferRepository.save(offer);
                        }

                        addedCandidates.forEach(candidate -> ride.getCheckpoints().add(candidate.getLocation()));
                        passengerCandidateRepository.delete(addedCandidates);
                        rideRepository.save(ride);
                    }
                });
    }

    private Ride newRideFromOffer(RideOffer offer) {
        return Ride.builder()
                .id(offer.getId())
                .rideTime(offer.getRideTime())
                .capacity(offer.getSeats())
                .ownerId(offer.getOwnerId())
                .start(offer.getStart())
                .end(offer.getEnd())
                .build();
    }
}
