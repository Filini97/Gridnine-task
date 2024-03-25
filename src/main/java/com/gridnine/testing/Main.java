package com.gridnine.testing;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Вылет до текущего момента времени:");
        List<Flight> filteredFlightsByDepartureBeforeNow = FlightFilters.filterFlightsByDepartureBeforeNow(flights);
        filteredFlightsByDepartureBeforeNow.forEach(System.out::println);

        System.out.println("\nИмеются сегменты с датой прилёта раньше даты вылета:");
        List<Flight> filteredFlightsByArrivalBeforeDeparture = FlightFilters.filterFlightsByArrivalBeforeDeparture(flights);
        filteredFlightsByArrivalBeforeDeparture.forEach(System.out::println);

        System.out.println("\nОбщее время, проведённое на земле, превышает два часа :");
        List<Flight> filteredFlightsByTotalGroundTime = FlightFilters.filterFlightsByTotalGroundTime(flights, Duration.ofHours(2));
        filteredFlightsByTotalGroundTime.forEach(System.out::println);
    }
}