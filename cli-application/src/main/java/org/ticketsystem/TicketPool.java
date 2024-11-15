package org.ticketsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private int maxCapacity;
    private List<Ticket> tickets;


    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.tickets = Collections.synchronizedList(new ArrayList<>(maxCapacity));


        for (int i = 0; i < maxCapacity; i++) {
            tickets.add(new Ticket(i + 1, 50.0, -1));
        }
    }


    public void addTickets(int numTickets, double price, int vendorId) {
        synchronized (tickets) {
            int added = 0;
            for (int i = 0; i < numTickets; i++) {
                if (tickets.size() < maxCapacity) {
                    Ticket ticket = new Ticket(tickets.size() + 1, price, vendorId);
                    tickets.add(ticket);
                    added++;
                }
            }
            System.out.println(added + " tickets added to the pool. Total tickets available: " + (maxCapacity - tickets.size()));
        }
    }


    public boolean removeTicket(int customerId) {
        synchronized (tickets) {
            for (Ticket ticket : tickets) {
                if (!ticket.isReserved()) {
                    ticket.setReserved(true);
                    ticket.setSoldBy(customerId);
                    System.out.println("Ticket purchased: " + ticket);
                    return true;
                }
            }
            System.out.println("No tickets available for purchase.");
            return false; // No tickets left to purchase
        }
    }


    public int getAvailableTickets() {
        int available = 0;
        synchronized (tickets) {
            for (Ticket ticket : tickets) {
                if (!ticket.isReserved()) {
                    available++;
                }
            }
        }
        return available;
    }


    public int getMaxCapacity() {
        return maxCapacity;
    }
}
