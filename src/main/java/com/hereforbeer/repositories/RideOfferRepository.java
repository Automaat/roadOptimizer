package com.hereforbeer.repositories;

import com.hereforbeer.domain.RideOffer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RideOfferRepository extends MongoRepository<RideOffer, String> {
    List<RideOffer> findAllByActualIsTrue();
}
