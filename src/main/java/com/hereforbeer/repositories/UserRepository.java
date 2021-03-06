package com.hereforbeer.repositories;

import com.hereforbeer.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

    Optional<User> findOneByNick(String nick);
    Optional<User> findOneById(String id);
}
