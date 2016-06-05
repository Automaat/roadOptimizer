package com.hereforbeer.repositories;

import com.hereforbeer.domain.RideOffer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RideOfferRepository extends MongoRepository<RideOffer, String> {

    List<RideOffer> findAllByActualIsTrue();

    List<RideOffer> findByRideTimeAfter(LocalDateTime date);

    Optional<RideOffer> findOneById(String id);

    List<RideOffer> findAllByActualIsFalse();
}
