package com.hereforbeer.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@Builder(builderMethodName = "hiddenBuilder")
@Document(collection = "rides")
public class Ride {

    @Id
    private String id = UUID.randomUUID().toString();

    private List<Point> checkpoints = new ArrayList<>();
    private LocalDateTime rideTime; //Format: "yyyy/MM/dd HH:mm:ss"
    private List<Passenger> passengers = new ArrayList<>();
    private String ownerId;
    private int capacity;
    private Point start;
    private Point end;


    public static RideBuilder builder() {
        return hiddenBuilder().id(UUID.randomUUID().toString()).passengers(new ArrayList<>()).checkpoints(new ArrayList<>());
    }

    public List<PassengerCandidate> addPassengers(List<PassengerCandidate> matchedCandidates) {
        List<PassengerCandidate> addedPassengers = new ArrayList<>();
        matchedCandidates.stream().filter(candidate -> capacity > 0).forEach(candidate -> {
            passengers.add(candidate.asPassenger());
            addedPassengers.add(candidate);
            capacity--;
        });


        return addedPassengers;
    }
}
