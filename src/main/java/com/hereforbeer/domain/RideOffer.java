package com.hereforbeer.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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

    private String ownerId;
    private LocalDateTime rideTime; //Format: "yyyy/MM/dd HH:mm:ss"
    private Point start;
    private Point end;
    private int seats;
    private boolean actual;

    public static RideOfferBuilder builder() {
        return hiddenBuilder().id(UUID.randomUUID().toString()).actual(true);
    }

    public Circle getCircle() {
        Point center = new Point(avg(start.getX(), end.getX()), avg(start.getY(), end.getY()));
        return new Circle(center, rad(start, end));
    }

    private double avg(double x, double y) {
        return (x + y) / 2;
    }

    private double rad(Point point1, Point point2) {
        double dx = point1.getX() - point2.getX();
        double dy = point1.getY() - point2.getY();

        return Math.sqrt(dx*dx + dy * dy);
    }
}
