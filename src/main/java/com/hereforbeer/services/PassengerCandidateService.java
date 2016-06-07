package com.hereforbeer.services;

import com.hereforbeer.domain.PassengerCandidate;
import com.hereforbeer.domain.User;
import com.hereforbeer.repositories.PassengerCandidateRepository;
import com.hereforbeer.repositories.UserRepository;
import com.hereforbeer.web.dto.DTOMapper;
import com.hereforbeer.web.dto.PassengerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerCandidateService {

    private final PassengerCandidateRepository candidateRepository;
    private final UserRepository userRepository;

    @Autowired
    public PassengerCandidateService(PassengerCandidateRepository candidateRepository, UserRepository userRepository) {
        this.candidateRepository = candidateRepository;
        this.userRepository = userRepository;
    }

    public void saveCandidate(PassengerDTO passengerDTO, String nick) {
        Optional<User> userOpt = userRepository.findOneByNick(nick);

        userOpt.ifPresent(user -> {

            PassengerCandidate candidate = DTOMapper.parsePassengerCandidateFromDTO(passengerDTO, user.getFirstName(), user.getLastName());
            candidateRepository.save(candidate);
        });
    }
}
