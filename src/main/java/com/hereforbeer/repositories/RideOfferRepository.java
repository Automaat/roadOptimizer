package com.hereforbeer.repositories;

import com.hereforbeer.domain.RideOffer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RideOfferRepository extends MongoRepository<RideOffer, String> {

    List<RideOffer> findAllByActualIsTrue();

    List<RideOffer> findByRideTimeAfter(LocalDateTime date);
}
