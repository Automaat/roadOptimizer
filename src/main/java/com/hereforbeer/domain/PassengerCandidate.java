package com.hereforbeer.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@Builder(builderMethodName = "hiddenBuilder")
@Document(collection = "candidates")
public class PassengerCandidate {

    @Id
    private String id = UUID.randomUUID().toString();

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Point location;

    @NotNull
    private LocalDateTime rideTime;

    public Passenger asPassenger() {
        return Passenger.builder()
                .firstName(firstName)
                .lastName(lastName)
                .location(location)
                .build();
    }

    public static PassengerCandidateBuilder builder() {
        return hiddenBuilder().id(UUID.randomUUID().toString());
    }
}
