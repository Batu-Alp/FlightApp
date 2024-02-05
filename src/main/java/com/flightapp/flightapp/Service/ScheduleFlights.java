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

    @Scheduled(cron = "0 0 7 * * ?")
    public void updateFlightInformation() {
        List<Flight> flights = fetchMockFlightData();
        flightRepository.saveAll(flights);
    }

    private List<Flight> fetchMockFlightData() {
        List<Flight> mockFlights = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Flight flight = new Flight();
            flight.setDepartureAirport("Airport " + i);
            flight.setArrivalAirport("Airport " + (i + 1));
            flight.setDepartureDateTime(LocalDateTime.now().plusDays(i));
            flight.setReturnDateTime(LocalDateTime.now().plusDays(i).plusHours(2));
            flight.setPrice(new Random().nextDouble() * 100 + 100);
            mockFlights.add(flight);
        }
        return mockFlights;
    }

}
