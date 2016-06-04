package com.hereforbeer.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@Builder(builderMethodName = "hiddenBuilder")
@Document(collection = "rideOffers")
public class RideOffer {

    @Id
    private String id = UUID.randomUUID().toString();

    private String authorNick;
    private LocalDate rideDate;
    private Point start;
    private Point end;
    private int seats;

    public static RideOfferBuilder builder() {
        return hiddenBuilder().id(UUID.randomUUID().toString());
    }

}
