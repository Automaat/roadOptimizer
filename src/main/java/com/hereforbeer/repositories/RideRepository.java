package com.hereforbeer.repositories;

import com.hereforbeer.domain.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends MongoRepository<Ride, String>{

}
