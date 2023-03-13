package ru.ideaplatform.testcase;

import com.azul.crs.com.fasterxml.jackson.databind.ObjectMapper;
import ru.ideaplatform.testcase.data.TicketsData;
import ru.ideaplatform.testcase.filter.FilterResolver;
import ru.ideaplatform.testcase.filter.filterImpl.NinetyPercentileFlightTimeVVOAndTLVFilter;
import ru.ideaplatform.testcase.filter.filterImpl.avgTimeBetweenVVOAndTLVFilter;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TicketsCalculator {

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        TicketsData ticketsData =
                mapper.readValue(new File("src/main/resources/tickets.json"), TicketsData.class);

        // #1 - среднее время полета между городами Владивосток и Тель-Авив
        Duration avgTimeBetweenVVOAndTLV =
                FilterResolver.takeFilterByContext(
                        ticketsData.getTicketsList(), new avgTimeBetweenVVOAndTLVFilter()
                );
        System.out.println(
                String.format("Cреднее время полета между городами Владивосток и Тель-Авив: %d час. %d мин.",
                              avgTimeBetweenVVOAndTLV.toHours(), avgTimeBetweenVVOAndTLV.toMinutes() % 60)
        );

        System.out.println();

        // #2 - 90-й процентиль времени полета между городами  Владивосток и Тель-Авив
        Duration ninetyPercentileFlightTime =
                FilterResolver.takeFilterByContext(
                        ticketsData.getTicketsList(), new NinetyPercentileFlightTimeVVOAndTLVFilter()
                );
        System.out.println(
                String.format("90-й процентиль времени полета между городами  Владивосток и Тель-Авив: %d час. %d мин.",
                              ninetyPercentileFlightTime.toHours(), ninetyPercentileFlightTime.toMinutes() % 60)
        );
    }
}
