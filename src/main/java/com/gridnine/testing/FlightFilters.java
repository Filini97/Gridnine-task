package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilters {
    // Filtering departures up to the current point in time
    public static List<Flight> filterFlightsByDepartureBeforeNow(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (Flight flight : flights) {
            Segment firstSegment = flight.getSegments().get(0);
            if (firstSegment.getDepartureDate().isAfter(now)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    // Filtering flights with segments whose arrival date is earlier than the departure date
    public static List<Flight> filterFlightsByArrivalBeforeDeparture(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            boolean isValid = true;
            for (Segment segment : flight.getSegments()) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    // Filtering flights by total time spent on the ground
    public static List<Flight> filterFlightsByTotalGroundTime(List<Flight> flights, Duration maxGroundTime) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            boolean isValid = true;
            List<Segment> segments = flight.getSegments();
            Duration totalGroundTime = Duration.ZERO;
            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime arrival = segments.get(i).getArrivalDate();
                LocalDateTime departure = segments.get(i + 1).getDepartureDate();
                Duration groundTime = Duration.between(arrival, departure);
                totalGroundTime = totalGroundTime.plus(groundTime);
            }
            if (totalGroundTime.compareTo(maxGroundTime) > 0) {
                isValid = false;
            }
            if (isValid) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }
}
