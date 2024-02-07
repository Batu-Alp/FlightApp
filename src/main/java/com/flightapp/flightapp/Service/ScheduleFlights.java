package com.flightapp.flightapp.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.flightapp.flightapp.Entity.Flight;
import com.flightapp.flightapp.Repository.IFlightRepository;

@Service
public class ScheduleFlights {

    @Autowired
    private IFlightRepository flightRepository;
    private static final List<String> airports = List.of("New York", "London", "Paris", "Tokyo", "Sydney",
            "Los Angeles");

    @Scheduled(cron = "0 0 7 * * ?")
    public void fetchFlightInfo() {
        List<Flight> mockFlights = generateMockFlightData();
        flightRepository.saveAll(mockFlights);
    }

    private List<Flight> generateMockFlightData() {
        List<Flight> mockFlights = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Flight flight = new Flight();
            String departureAirport = airports.get(i);
            String arrivalAirport = airports.get(i + 1);
            flight.setDepartureAirport(departureAirport);
            flight.setArrivalAirport(arrivalAirport);
            flight.setDepartureDateTime(LocalDateTime.now().plusDays(i));
            flight.setReturnDateTime(LocalDateTime.now().plusDays(i).plusHours(1));
            flight.setPrice(new Random().nextDouble() * 1000 + 50);
            mockFlights.add(flight);
        }
        return mockFlights;
    }

}
