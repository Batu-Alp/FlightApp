package com.flightapp.flightapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.flightapp.Entity.Airport;
import com.flightapp.flightapp.Repository.IAirportRepository;

@Service
public class AirportService {

    @Autowired
    private IAirportRepository airportRepository;

    public AirportService(IAirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public void createAirport(Airport airport) {
        airportRepository.save(airport);
    }

    public List<Airport> findAirports() {
        return airportRepository.findAll();
    }

    public List<Airport> findByCity(String city) {
        return airportRepository.findByCity(city);

    }

    public Airport getAirportById(Long airportIdd) {
        return airportRepository.findById(airportIdd).orElse(null);
    }

    public void deleteAirport(long airportId) {
        Airport airport = airportRepository.findById(airportId).orElse(null);

        if (airport != null) {
            airportRepository.delete(airport);
        } else {
            throw new RuntimeException("Airport not found with id: " + airportId);
        }
    }

    public void updateAirport(long airportId, Airport updatedAirport) {
        Airport airport = airportRepository.findById(airportId).orElse(null);
        if (airport != null) {
            airport.setCity(updatedAirport.getCity());
            airportRepository.save(airport);
        } else {
            throw new RuntimeException("Airport not found with id: " + airportId);
        }
    }

}
