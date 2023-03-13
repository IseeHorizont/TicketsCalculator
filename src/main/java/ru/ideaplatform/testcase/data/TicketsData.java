package ru.ideaplatform.testcase.data;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TicketsData {

    @JsonProperty("tickets")
    private List<Ticket> ticketsList;


    public List<Ticket> getTicketsList() {
        ticketsList.stream().forEach(Ticket::setDepartureDateTime);
        ticketsList.stream().forEach(Ticket::setArrivalDateTime);
        return ticketsList;
    }

    public void setTicketsList(List<Ticket> ticketsList) {
        this.ticketsList = ticketsList;
    }
}
