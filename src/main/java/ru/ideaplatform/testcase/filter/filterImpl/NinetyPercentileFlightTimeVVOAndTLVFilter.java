package ru.ideaplatform.testcase.filter.filterImpl;

import ru.ideaplatform.testcase.data.Ticket;
import ru.ideaplatform.testcase.filter.FlightFilter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 90-й процентиль времени полета между городами  Владивосток и Тель-Авив
 */
public class NinetyPercentileFlightTimeVVOAndTLVFilter implements FlightFilter {

    @Override
    public Duration doFilter(List<Ticket> flights) {
        Predicate<Ticket> isDestinationVVO = ticket -> "VVO".equals(ticket.getDestination());
        Predicate<Ticket> isDestinationTLV = ticket -> "TLV".equals(ticket.getDestination());

        List<Long> timeAllFlights = flights.stream()
                .filter(isDestinationVVO.or(isDestinationTLV))
                .map(flight -> Duration.between(flight.getDepartureDateTime(),
                        flight.getArrivalDateTime()).toMinutes())
                .sorted()
                .collect(Collectors.toList());

        double percentile = 0.90 * timeAllFlights.size();
        int index = (int) Math.ceil(percentile) - 1;

        return Duration.of(timeAllFlights.get(index), ChronoUnit.MINUTES);
    }
}
