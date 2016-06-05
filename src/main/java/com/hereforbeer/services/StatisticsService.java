package com.hereforbeer.services;

import com.hereforbeer.domain.Ride;
import com.hereforbeer.domain.RideOffer;
import com.hereforbeer.repositories.RideOfferRepository;
import com.hereforbeer.repositories.RideRepository;
import com.hereforbeer.services.statistics.TopUserCount;
import com.hereforbeer.web.dto.statistics.StatisticsDTOMapper;
import com.hereforbeer.web.dto.statistics.SuccessComparisionDTO;
import com.hereforbeer.web.dto.statistics.TopUserDTO;
import com.hereforbeer.web.dto.statistics.TotalDistanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class StatisticsService {

    private RideRepository rideRepository;
    private RideOfferRepository rideOfferRepository;
    private MongoTemplate mongoTemplate;

    @Autowired
    public StatisticsService(RideRepository rideRepository,
                             RideOfferRepository rideOfferRepository,
                             MongoTemplate mongoTemplate) {
        this.rideRepository = rideRepository;
        this.rideOfferRepository = rideOfferRepository;
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

    public TotalDistanceInfo getTotalDistance(){

        long total = rideOfferRepository.findAll()
                .stream()
                .mapToLong((RideOffer::getDistance))
                .sum();

        long fuel = (total/100) * 7;

        double price = fuel * 4.5;

        double saved = price * 3;

        long savedFuel = fuel * 3;

        return new TotalDistanceInfo(total, fuel, price, saved, savedFuel);
    }
}
