package com.hereforbeer.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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

    private List<Point> checkpoints;
    private LocalDateTime departureTime; //Format: "yyyy/MM/dd HH:mm:ss"
    private List<Passenger> passengers;


    public static RideBuilder builder() {
        return hiddenBuilder().id(UUID.randomUUID().toString());
    }

}
