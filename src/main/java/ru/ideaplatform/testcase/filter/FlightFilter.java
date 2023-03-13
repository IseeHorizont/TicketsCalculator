package ru.ideaplatform.testcase.filter;

import ru.ideaplatform.testcase.data.Ticket;

import java.time.Duration;
import java.util.List;

public interface FlightFilter {

    Duration doFilter(List<Ticket> flights);
}
