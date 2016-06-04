package com.hereforbeer.repositories;

import com.hereforbeer.domain.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RideRepository extends MongoRepository<Ride, String> {
    List<Ride> findAllByOwnerIdAndRideTimeAfter(String id, LocalDateTime rideTime);
    List<Ride> findAllByOwnerId(String id);
}
