package com.hereforbeer.services;

import com.hereforbeer.repositories.RideRepository;
import com.hereforbeer.repositories.UserRepository;
import com.hereforbeer.web.BadRequestException;
import com.hereforbeer.web.dto.DTOMapper;
import com.hereforbeer.web.dto.RideDTO;
import com.hereforbeer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hereforbeer.web.ErrorInfo.USER_NOT_FOUND;

@Service
public class RideService {
    private final RideRepository rideRepository;
    private final UserRepository userRepository;

    @Autowired
    public RideService(RideRepository rideRepository, UserRepository userRepository) {
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
    }

    public List<RideDTO> getOwnerRides(String ownerId) {
        Optional<User> user = userRepository.findOneById(ownerId);
        user.orElseThrow(() -> new BadRequestException(USER_NOT_FOUND));

        return rideRepository
                .findAllByOwnerIdAndRideTimeAfter(ownerId, LocalDateTime.now())
                .stream()
                .map(DTOMapper::rideToDto)
                .collect(Collectors.toList());
    }
}
