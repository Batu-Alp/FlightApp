package com.flightapp.flightapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.flightapp.Entity.Airport;

@Repository
public interface IAirportRepository extends JpaRepository<Airport, Long> {
    List<Airport> findByCity(String city);
}
