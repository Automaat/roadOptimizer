package com.hereforbeer.domain;

import lombok.*;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder(builderMethodName = "hiddenEventBuilder")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@Document(collection = "passengers")
public class Passenger {
    private String id;

    private String firstname;

    private String lastname;

    private Point point;

    public static PassengerBuilder builder(){
        return hiddenEventBuilder().id(UUID.randomUUID().toString());
    }
}
