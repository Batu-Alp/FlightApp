package com.flightapp.flightapp.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.flightapp.Entity.Flight;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, Long> {

        List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTime(String departureAirport,
                        String arrivalAirport,
                        LocalDateTime departureDateTime);

        List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAndReturnDateTime(String departure,
                        String arrival, LocalDateTime departureDateTime, LocalDateTime returnDateTime);

        List<Flight> findByDepartureAirportAndArrivalAirport(String departure_airport, String arrival_airport);

        List<Flight> findByDepartureDateTimeAndReturnDateTime(LocalDateTime departureDateTime,
                        LocalDateTime returnDateTime);
        /*
         * List<Flight> findByDeparturesAndArrivals(String departureAirport, String
         * arrivalAirport,
         * LocalDateTime departureDateTime);
         * 
         * List<Flight> findByRoundTrips(String departureAirport,
         * String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime
         * returnDateTime);
         */
        /*
         * 
         * List<Flight> findByRoundTrips(String departure,
         * String arrival, LocalDateTime departureDateTime, LocalDateTime
         * returnDateTime);
         */

}
