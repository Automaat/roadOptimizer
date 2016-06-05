package com.hereforbeer.services;

import com.hereforbeer.domain.Passenger;
import com.hereforbeer.domain.Ride;
import com.hereforbeer.repositories.RideRepository;
import com.hereforbeer.repositories.UserRepository;
import com.hereforbeer.web.BadRequestException;
import com.hereforbeer.web.ErrorInfo;
import com.hereforbeer.web.dto.DTOMapper;
import com.hereforbeer.web.dto.RideDTO;
import com.hereforbeer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    public List<RideDTO> getPassengerRides(String passengerId) {
        User user = userRepository.findOneById(passengerId).orElseThrow(() -> new BadRequestException(ErrorInfo.USER_NOT_FOUND));

        return rideRepository.findAll().stream()
                .filter(ride -> containsPassenger(ride, user) && ride.getRideTime().isAfter(LocalDateTime.now())).map(DTOMapper::parseRideToDTO)
                .collect(Collectors.toList());
    }

    private boolean containsPassenger(Ride ride, User user) {
        for (Passenger passenger : ride.getPassengers()) {
            if (Objects.equals(passenger.getFirstName(), user.getFirstName()) && Objects.equals(passenger.getLastName(), user.getLastName())) {
                return true;
            }
        }
        return false;
    }

    public List<RideDTO> getAllActualRides() {
        List<Ride> rides = rideRepository.findAllByRideTimeAfter(LocalDateTime.now());
        return rides.stream().map(DTOMapper::parseRideToDTO).collect(Collectors.toList());
    }
}
