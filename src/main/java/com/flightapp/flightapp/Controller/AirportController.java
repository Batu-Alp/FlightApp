package com.flightapp.flightapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.flightapp.Entity.Airport;
import com.flightapp.flightapp.Service.AirportService;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public List<Airport> getAirports() {
        return airportService.findAirports();
    }

    @GetMapping("/{id}")
    public Airport getAirportById(@PathVariable Long id) {
        return airportService.getAirportById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAirport(@RequestBody Airport airport) {
        airportService.createAirport(airport);
        return new ResponseEntity<>("Airport created successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        airportService.updateAirport(id, airport);
        return new ResponseEntity<>("Flight updated successfully", HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
    }

    @GetMapping("/city")
    public List<Airport> getAirportByCity(@RequestParam String city) {
        return airportService.findByCity(city);
    }

}
