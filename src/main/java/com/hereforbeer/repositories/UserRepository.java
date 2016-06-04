package com.hereforbeer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.hereforbeer.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findOneById(String id);
    Optional<User> findOneByNick(String id);
    Optional<User> findOneByLastname(String id);
}
