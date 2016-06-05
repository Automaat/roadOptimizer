package com.hereforbeer.workers

import com.hereforbeer.RouteOptimizerApplication
import com.hereforbeer.domain.PassengerCandidate
import com.hereforbeer.domain.RideOffer
import com.hereforbeer.domain.User
import com.hereforbeer.repositories.PassengerCandidateRepository
import com.hereforbeer.repositories.RideOfferRepository
import com.hereforbeer.repositories.RideRepository
import com.hereforbeer.repositories.UserRepository
import com.hereforbeer.web.dto.DTOMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.data.geo.Point
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@SpringApplicationConfiguration(classes = RouteOptimizerApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
class RideMatcherTest extends Specification {

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    UserRepository userRepository

    @Autowired
    RideOfferRepository offerRepository

    @Autowired
    PassengerCandidateRepository candidateRepository

    @Autowired
    RideRepository rideRepository

    def userJanusz = User.builder()
            .nick("janusz")
            .firstName("Janusz")
            .lastName("Nowkak")
            .password("janusz")
            .build()

    def userJan = User.builder()
            .nick("jan")
            .firstName("Jan")
            .lastName("Kowalski")
            .password("jan")
            .build()

    def userDriver = User.builder()
            .nick("grazia")
            .firstName("Grzyna")
            .lastName("Kubica")
            .password("grazia")
            .build()

    def offer = RideOffer.builder()
            .rideTime(LocalDateTime.parse("2016/06/05 08:00:00", DTOMapper.formatter))
            .ownerId(userDriver.getId())
            .seats(4)
            .start(new Point(0.0, 0.0))
            .end(new Point(10.0, 0.0))
            .build()

    def candidate = PassengerCandidate.builder()
            .firstName(userJanusz.getFirstName())
            .lastName(userJanusz.getLastName())
            .rideTime(LocalDateTime.parse("2016/06/05 08:00:00", DTOMapper.formatter))
            .location(new Point(5.0, 0.0))
            .build()

    def roadNotMatchingCandidate = PassengerCandidate.builder()
            .firstName(userJan.getFirstName())
            .lastName(userJan.getLastName())
            .rideTime(LocalDateTime.parse("2016/06/05 08:00:00", DTOMapper.formatter))
            .location(new Point(80.0, 80.0))
            .build()

    def timeNotMatchingCandidate = PassengerCandidate.builder()
            .firstName(userJan.getFirstName())
            .lastName(userJan.getLastName())
            .rideTime(LocalDateTime.parse("2016/06/05 08:20:00", DTOMapper.formatter))
            .location(new Point(5.0, 0.0))
            .build()


    def setup() {
        userRepository.save([userJanusz, userDriver])
    }

    def cleanup() {
        userRepository.deleteAll()
        offerRepository.deleteAll()
        candidateRepository.deleteAll()
        rideRepository.deleteAll()
    }

    def "matcher should create ride"() {
        given:
        offerRepository.save(offer)
        candidateRepository.save(candidate)


        TimeUnit.SECONDS.sleep(10)

        expect:
        !rideRepository.findAll().isEmpty()
        candidateRepository.findAll().isEmpty()
    }

    def "matcher should not create ride when freebie is not on road" () {
        given:
        offerRepository.save(offer)
        candidateRepository.save(roadNotMatchingCandidate)


        TimeUnit.SECONDS.sleep(10)

        expect:
        !candidateRepository.findAll().isEmpty()
    }

    def "matcher should not create ride when freebie is not in time range" () {
        given:
        offerRepository.save(offer)
        candidateRepository.save(timeNotMatchingCandidate)


        TimeUnit.SECONDS.sleep(10)

        expect:
        !candidateRepository.findAll().isEmpty()
    }

}
