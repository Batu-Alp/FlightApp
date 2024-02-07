package com.flightapp.flightapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import java.time.LocalDateTime;
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

    @GetMapping("/{flightId}")
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

    @GetMapping("/searchByAirports")
    public ResponseEntity<List<Flight>> searchByAirports(
            @RequestParam String departurer_airport,
            @RequestParam String arrival_airport) {
        List<Flight> result = flightService.searchByAirports(departurer_airport, arrival_airport);
        return new ResponseEntity<>(result,
                HttpStatus.OK);
    }

    @GetMapping("/searchByDepartureAndArrivalTimes")
    public List<Flight> searchByDepartureAndArrivalTimes(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalDateTime) {
        return flightService.searchByDepartureAndArrivalTimes(departureDateTime, arrivalDateTime);
    }

    @GetMapping("/searchByDepartureTime")
    public ResponseEntity<?> searchFlightsByDepartureTime(
            @RequestParam String departure,
            @RequestParam String arrival,
            @RequestParam("departureDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime, // 2024-02-06T14:28:17.653000
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDateTime) // 2024-02-06T16:28:17.653000
    {

        List<Flight> departureFlights = flightService.searchOneWayFlights(
                departure, arrival, departureDateTime);

        return new ResponseEntity<>(departureFlights, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFlights(
            @RequestParam String departure,
            @RequestParam String arrival,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDateTime) {

        List<Flight> departures = flightService.searchOneWayFlights(
                departure, arrival, departureDateTime);

        if (returnDateTime == null) {
            return new ResponseEntity<>(departures, HttpStatus.OK);
        } else {
            List<Flight> returns = flightService.searchRoundTripFlights(
                    departure, arrival, departureDateTime, returnDateTime);
            return new ResponseEntity<>(new Object[] { departures, returns },
                    HttpStatus.OK);

        }

    }
}
