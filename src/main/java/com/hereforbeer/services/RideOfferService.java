package com.hereforbeer.services;

import com.hereforbeer.domain.RideOffer;
import com.hereforbeer.repositories.RideOfferRepository;
import com.hereforbeer.web.BadRequestException;
import com.hereforbeer.web.dto.RideOfferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.hereforbeer.web.ErrorInfo.BAD_LOCATION;
import static com.hereforbeer.web.dto.DTOMapper.parseRideOfferFromDTO;

@Service
public class RideOfferService {
    private final RideOfferRepository rideOfferRepository;

    @Autowired
    public RideOfferService(RideOfferRepository rideOfferRepository) {
        this.rideOfferRepository = rideOfferRepository;
    }

    public void addRideOffer(RideOfferDTO rideOfferDTO, String headerNick) {

        if (rideOfferDTO.getStart() == null || rideOfferDTO.getEnd() == null) {
            throw new BadRequestException(BAD_LOCATION);
        }
        RideOffer rideOffer = parseRideOfferFromDTO(rideOfferDTO, headerNick);
        rideOfferRepository.save(rideOffer);
    }
}
