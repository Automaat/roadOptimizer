package com.hereforbeer.services;

import com.hereforbeer.domain.RideOffer;
import com.hereforbeer.domain.User;
import com.hereforbeer.repositories.RideOfferRepository;
import com.hereforbeer.repositories.UserRepository;
import com.hereforbeer.web.BadRequestException;
import com.hereforbeer.web.dto.RideOfferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.hereforbeer.web.ErrorInfo.BAD_LOCATION;
import static com.hereforbeer.web.dto.DTOMapper.parseRideOfferFromDTO;

@Service
public class RideOfferService {
    private final RideOfferRepository rideOfferRepository;
    private final UserRepository userRepository;

    @Autowired
    public RideOfferService(RideOfferRepository rideOfferRepository, UserRepository userRepository) {
        this.rideOfferRepository = rideOfferRepository;
        this.userRepository = userRepository;
    }


    public void addRideOffer(RideOfferDTO rideOfferDTO, String nick) {
        if (rideOfferDTO.getStart() == null || rideOfferDTO.getEnd() == null) {
            throw new BadRequestException(BAD_LOCATION);
        }

        User user = userRepository.findOneByNick(nick).orElseThrow(() -> new IllegalStateException("User not found"));


        RideOffer rideOffer = parseRideOfferFromDTO(rideOfferDTO, user.getId());
        rideOfferRepository.save(rideOffer);
    }
}
