package com.hereforbeer.services;

import com.hereforbeer.domain.Ride;
import com.hereforbeer.domain.RideOffer;
import com.hereforbeer.repositories.RideOfferRepository;
import com.hereforbeer.repositories.RideRepository;
import com.hereforbeer.repositories.UserRepository;
import com.hereforbeer.services.statistics.TopUserCount;
import com.hereforbeer.web.dto.statistics.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class StatisticsService {

    private RideRepository rideRepository;
    private RideOfferRepository rideOfferRepository;
    private UserRepository userRepository;
    private MongoTemplate mongoTemplate;

    @Autowired
    public StatisticsService(RideRepository rideRepository, RideOfferRepository rideOfferRepository, UserRepository userRepository, MongoTemplate mongoTemplate) {
        this.rideRepository = rideRepository;
        this.rideOfferRepository = rideOfferRepository;
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public SuccessComparisionDTO getSuccessfulOffersStats() {
        int successfulRides = rideOfferRepository.findAllByActualIsFalse().size();

//        int partiallysuccessfulRides;
//
//        int failedRides = rideRepository
//                .findByCapacityGreaterThanAndRideTimeBefore(0, LocalDateTime.now())
//                .size();

        int allRides = rideRepository.findAll().size();

        return new SuccessComparisionDTO(successfulRides, allRides, allRides - successfulRides);
    }

    public List<TopUserDTO> getTopUser() {

        Aggregation aggregation = newAggregation(
                match(Criteria.where("rideTime").lt(LocalDateTime.now())),
                group("ownerId").count().as("total"),
                project("total").and("ownerId").previousOperation(),
                sort(Sort.Direction.DESC, "total")
        );

        AggregationResults<TopUserCount> groupResults = mongoTemplate.aggregate(aggregation,
                Ride.class,
                TopUserCount.class);

        return groupResults.getMappedResults()
                .stream()
                .limit(10)
                .map(StatisticsDTOMapper::parseTopUserToDTO)
                .collect(toList());
    }

    public TotalDistanceInfo getTotalDistance() {

        long total = rideOfferRepository.findAll()
                .stream()
                .mapToLong((RideOffer::getDistance))
                .sum();

        long fuel = (total / 100) * 7;

        double price = fuel * 4.5;

        double saved = price * 3;

        long savedFuel = fuel * 3;

        return new TotalDistanceInfo(total, fuel, price, saved, savedFuel);
    }

    public FreebieStatisticsDTO getFreebieStatistics() {

        long userCount = userRepository.count();

        Aggregation aggregation = newAggregation(
                match(Criteria.where("rideTime").lt(LocalDateTime.now())),
                group("ownerId").count().as("total"),
                project("total").and("ownerId").previousOperation(),
                sort(Sort.Direction.DESC, "total")
        );

        AggregationResults<TopUserCount> groupResults = mongoTemplate.aggregate(aggregation,
                Ride.class,
                TopUserCount.class);

        long payingUsers = groupResults.getMappedResults().size();

        double payingUsersPercentage = payingUsers / (double) userCount;
        double freebieUsersPercentage = (userCount - payingUsers) / (double) userCount;


        return new FreebieStatisticsDTO(round(freebieUsersPercentage, 2), round(payingUsersPercentage, 2));
    }


    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
