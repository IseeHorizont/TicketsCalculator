package ru.ideaplatform.testcase.filter;

import ru.ideaplatform.testcase.data.Ticket;

import java.time.Duration;
import java.util.List;

public class FilterResolver {

    public static Duration takeFilterByContext(List<Ticket> tickets, FlightFilter filter) {
        return filter.doFilter(tickets);
    }
}
