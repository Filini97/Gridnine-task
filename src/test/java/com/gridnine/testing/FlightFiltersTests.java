package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class FlightFiltersTests {
    @Test
    void testFilterFlightsByDepartureBeforeNow() {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> filteredFlights = FlightFilters.filterFlightsByDepartureBeforeNow(flights);
        assertEquals(5, filteredFlights.size());
    }

    @Test
    void testFilterFlightsByArrivalBeforeDeparture() {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> filteredFlights = FlightFilters.filterFlightsByArrivalBeforeDeparture(flights);
        assertEquals(5, filteredFlights.size());
    }

    @Test
    void testFilterFlightsByTotalGroundTime() {
        List<Flight> flights = FlightBuilder.createFlights();
        Duration maxGroundTime = Duration.ofHours(2);
        List<Flight> filteredFlights = FlightFilters.filterFlightsByTotalGroundTime(flights, maxGroundTime);
        assertEquals(4, filteredFlights.size());
    }
}
