package com.hereforbeer.repositories;

import com.hereforbeer.domain.RideOffer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RideOfferRepository extends MongoRepository<RideOffer, String> {
}
