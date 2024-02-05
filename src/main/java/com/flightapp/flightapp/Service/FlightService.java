package com.flightapp.flightapp.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.flightapp.Entity.Flight;
import com.flightapp.flightapp.Repository.IFlightRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FlightService {

    @Autowired
    private IFlightRepository flightRepository;

    public FlightService(IFlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void createFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public List<Flight> findFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }

    public void updateFlight(long flightId, Flight updatedFlight) {
        Flight flight = flightRepository.findById(flightId).orElse(null);

        if (flight != null) {
            flight.setDepartureAirport(updatedFlight.getDepartureAirport());
            flight.setArrivalAirport(updatedFlight.getArrivalAirport());
            flight.setDepartureDateTime(updatedFlight.getDepartureDateTime());
            flight.setReturnDateTime(updatedFlight.getReturnDateTime());
            flight.setPrice(updatedFlight.getPrice());
            flightRepository.save(flight);
        } else {
            throw new RuntimeException("Flight not found with id: " + flightId);
        }
    }

    public void deleteFlight(long flightId) {
        Flight flight = flightRepository.findById(flightId).orElse(null);

        if (flight != null) {
            flightRepository.delete(flight);
        } else {
            throw new RuntimeException("Flight not found with id: " + flightId);
        }
    }

    public List<Flight> searchOneWayFlights(String departure, String arrival, LocalDateTime departureDateTime) {

        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(departure, arrival,
                departureDateTime);
    }

    public List<Flight> searchRoundTripFlights(String departure, String arrival, LocalDateTime departureDateTime,
            LocalDateTime returnDateTime) {

        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAndReturnDateTime(departure,
                arrival, departureDateTime, returnDateTime);
    }

}
