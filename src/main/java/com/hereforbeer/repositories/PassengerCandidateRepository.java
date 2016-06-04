package com.hereforbeer.repositories;

import com.hereforbeer.domain.PassengerCandidate;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerCandidateRepository extends MongoRepository<PassengerCandidate, String>{

    List<PassengerCandidate> findByLocationWithin(Circle c);
}
