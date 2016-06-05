package com.hereforbeer.repositories;

import com.hereforbeer.domain.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RideRepository extends MongoRepository<Ride, String> {
    List<Ride> findAllByOwnerIdAndRideTimeAfter(String id, LocalDateTime rideTime);
    List<Ride> findAllByOwnerId(String id);
    List<Ride> findAllByRideTimeAfter(LocalDateTime rideTime);
    Optional<Ride> findOneById(String id);
}
