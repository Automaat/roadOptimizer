package com.hereforbeer.web.dto;

import com.hereforbeer.domain.PassengerCandidate;
import com.hereforbeer.domain.RideOffer;
import com.hereforbeer.domain.User;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DTOMapper {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static User parseUserFromDTO(UserDTO userDTO) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .nick(userDTO.getNick())
                .password(userDTO.getPassword())
                .build();
    }

    public static RideOffer parseRideOfferFromDTO(RideOfferDTO rideOfferDTO, String nick) {
        double[] startLocation = rideOfferDTO.getStart().asArray();
        Point startPoint = new Point(startLocation[0], startLocation[1]);

        double[] endLocation = rideOfferDTO.getStart().asArray();
        Point endPoint = new Point(endLocation[0], endLocation[1]);


        LocalDateTime rideDate = LocalDateTime.parse(rideOfferDTO.getRideDate(), formatter);

        return RideOffer.builder()
                .start(startPoint)
                .end(endPoint)
                .rideDate(rideDate)
                .seats(rideOfferDTO.getSeats())
                .authorNick(nick)
                .build();
    }

    public static PassengerCandidate parsePassengerCandidateFromDTO(PassengerDTO passengerDTO, String firstName, String lastName) {
        return PassengerCandidate.builder()
                .firstName(firstName)
                .lastName(lastName)
                .location(passengerDTO.getAddress().asPoint())
                .rideTime(LocalDateTime.parse(passengerDTO.getRideTime(), formatter))
                .build();

    }
}
