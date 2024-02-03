package com.flightapp.flightapp.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.flightapp.Entity.Flight;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, Long> {

        List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTime(String departure, String arrival,
                        Date departureDateTime);

        List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAndReturnDateTime(String departure,
                        String arrival, Date departureDateTime, Date returnDateTime);

}
