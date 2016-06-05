package com.hereforbeer.web.dto;

import com.hereforbeer.domain.*;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static User parseUserFromDTO(UserDTO userDTO) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .nick(userDTO.getNick())
                .password(userDTO.getPassword())
                .build();
    }

    public static RideOffer parseRideOfferFromDTO(RideOfferDTO rideOfferDTO, String ownerId) {
        double[] startLocation = rideOfferDTO.getStart().asArray();
        Point startPoint = new Point(startLocation[0], startLocation[1]);

        double[] endLocation = rideOfferDTO.getEnd().asArray();
        Point endPoint = new Point(endLocation[0], endLocation[1]);


        LocalDateTime rideTime = LocalDateTime.parse(rideOfferDTO.getRideTime(), formatter);

        return RideOffer.builder()
                .start(startPoint)
                .end(endPoint)
                .rideTime(rideTime)
                .seats(rideOfferDTO.getSeats())
                .ownerId(ownerId)
                .build();
    }

    public static PassengerCandidate parsePassengerCandidateFromDTO(PassengerDTO passengerDTO, String firstName, String lastName) {
        return PassengerCandidate.builder()
                .firstName(firstName)
                .lastName(lastName)
                .location(passengerDTO.getLocation().asPoint())
                .rideTime(LocalDateTime.parse(passengerDTO.getRideTime(), formatter))
                .build();

    }

    public static LocationDTO pointToDTO(Point point) {
        return LocationDTO.builder()
                .latitude(point.getX())
                .longitude(point.getY())
                .build();
    }

    public static PassengerDTO passengertoDTO(Passenger passenger) {
        return PassengerDTO.builder()
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .location(pointToDTO(passenger.getLocation()))
                .build();
    }

    public static RideDTO rideToDto(Ride ride) {
        List<LocationDTO> checkpointsDTOs = ride.getCheckpoints().stream().map(DTOMapper::pointToDTO).collect(Collectors.toList());

        List<PassengerDTO> passengersDTOs = ride.getPassengers().stream().map(DTOMapper::passengertoDTO).collect(Collectors.toList());

        return RideDTO.builder()
                .id(ride.getId())
                .checkpoints(checkpointsDTOs)
                .rideTime(ride.getRideTime().toString())
                .passengers(passengersDTOs)
                .build();
    }

    public static RideOfferDTO parseRideOfferToDTO(RideOffer offer) {
        return RideOfferDTO.builder()
                .id(offer.getId())
                .ownerId(offer.getOwnerId())
                .start(parseFromLocation(offer.getStart()))
                .end(parseFromLocation(offer.getEnd()))
                .rideTime(offer.getRideTime().format(formatter))
                .seats(offer.getSeats())
                .build();
    }

    public static LocationDTO parseFromLocation(Point point) {
        return LocationDTO.fromPoint(point);
    }


    public static UserDTO parseUserToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .nick(user.getNick())
                .build();
    }

    public static RideDTO parseRideToDTO(Ride ride) {
        return RideDTO.builder()
                .id(ride.getId())
                .checkpoints(ride.getCheckpoints().stream().map(DTOMapper::parseFromLocation).collect(Collectors.toList()))
                .rideTime(ride.getRideTime().format(formatter))
                .start(LocationDTO.fromPoint(ride.getStart()))
                .end(LocationDTO.fromPoint(ride.getEnd()))
                .passengers(ride.getPassengers().stream().map(DTOMapper::parsePassengerToDTO).collect(Collectors.toList()))
                .build();
    }

    private static PassengerDTO parsePassengerToDTO(Passenger passenger) {
        return PassengerDTO.builder()
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .location(LocationDTO.fromPoint(passenger.getLocation()))
                .build();
    }
}
