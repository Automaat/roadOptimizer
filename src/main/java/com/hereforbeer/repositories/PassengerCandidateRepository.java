package com.hereforbeer.repositories;

import com.hereforbeer.domain.PassengerCandidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerCandidateRepository extends MongoRepository<PassengerCandidate, String>{

}
