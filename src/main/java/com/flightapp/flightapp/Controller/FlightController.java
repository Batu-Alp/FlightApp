package com.flightapp.flightapp.Controller;

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

import com.flightapp.flightapp.Entity.Flight;
import com.flightapp.flightapp.Service.FlightService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/flights")

public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/")
    public List<Flight> getFlights() {
        return flightService.findFlights();
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getFlightById(@PathVariable Long flightId) {
        Flight flight = flightService.getFlightById(flightId);
        if (flight != null) {
            return ResponseEntity.ok(flight.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found with id: ");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createFlight(@RequestBody Flight flight) {
        flightService.createFlight(flight);
        return new ResponseEntity<>("Flight created successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return new ResponseEntity<>("Flight deleted successfully", HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Flight flight) {

        flightService.updateFlight(id, flight);
        return new ResponseEntity<>("Flight updated successfully", HttpStatus.OK);

    }

    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam String departure,
            @RequestParam String arrival,
            @RequestParam Date departureDateTime,
            @RequestParam(required = false) Date returnDateTime) {

        if (returnDateTime != null) {
            return flightService.searchRoundTripFlights(departure, arrival, departureDateTime, returnDateTime);
        } else {
            return flightService.searchOneWayFlights(departure, arrival, departureDateTime);
        }
    }
}
