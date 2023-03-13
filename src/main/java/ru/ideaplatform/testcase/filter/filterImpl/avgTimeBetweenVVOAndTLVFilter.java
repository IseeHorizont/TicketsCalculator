package ru.ideaplatform.testcase.filter.filterImpl;

import ru.ideaplatform.testcase.data.Ticket;
import ru.ideaplatform.testcase.filter.FlightFilter;

import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

/**
 * Среднее время полета между городами Владивосток и Тель-Авив
 */
public class avgTimeBetweenVVOAndTLVFilter implements FlightFilter {

    @Override
    public Duration doFilter(List<Ticket> flights) {

        Predicate<Ticket> isDestinationVVO = ticket -> "VVO".equals(ticket.getDestination());
        Predicate<Ticket> isDestinationTLV = ticket -> "TLV".equals(ticket.getDestination());

        long goalFlightsCount = flights.stream()
                .filter(isDestinationVVO.or(isDestinationTLV))
                .count();

        Duration timeAllFlights = flights.stream()
                .filter(isDestinationVVO.or(isDestinationTLV))
                .map(flight -> Duration.between(flight.getDepartureDateTime(),
                                                flight.getArrivalDateTime()))
                .reduce(Duration.ZERO, (duration, duration2) -> duration.plus(duration2));

        return timeAllFlights.dividedBy(goalFlightsCount);
    }
}
