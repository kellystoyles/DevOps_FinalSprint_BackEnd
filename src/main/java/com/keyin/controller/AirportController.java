package com.keyin.controller;

import com.keyin.entity.Airport;
import com.keyin.entity.Flight;
import com.keyin.service.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{iataCode}")
    public Airport getAirportByIataCode(@PathVariable String iataCode) {
        return airportService.getAirportByIataCode(iataCode);
    }

    @GetMapping("/{iataCode}/departures")
    public List<Flight> getDeparturesByIataCode(@PathVariable String iataCode) {
        return airportService.getDeparturesByIataCode(iataCode);
    }

    @GetMapping("/{iataCode}/arrivals")
    public List<Flight> getArrivalsByIataCode(@PathVariable String iataCode) {
        return airportService.getArrivalsByIataCode(iataCode);
    }

    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        // Save the airport using the service
        Airport savedAirport = airportService.save(airport);
        // Return the saved airport with a CREATED status
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAirport);
    }


    @PutMapping("/{iataCode}")
    public Airport updateAirport(@PathVariable String iataCode, @RequestBody Airport updatedAirport) {
        return airportService.updateAirport(iataCode, updatedAirport);
    }

    @DeleteMapping("/{iataCode}")
    public void deleteAirport(@PathVariable String iataCode) {
        airportService.deleteAirport(iataCode);
    }

}

