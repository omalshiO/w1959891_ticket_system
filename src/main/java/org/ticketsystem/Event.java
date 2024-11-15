package org.ticketsystem;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private int id;
    private int startedBy;
    private List<Integer> vendorIds;
    private boolean isActive;
    private Configuration configuration;
    private TicketPool ticketPool;


    public Event(int id, Configuration configuration) {
        this.id = id;
        this.configuration = configuration;
        this.ticketPool = new TicketPool(configuration.getMaxTicketCapacity());
        this.vendorIds = new ArrayList<>();
    }


    public void addVendor(Vendor vendor) {
        if (!vendorIds.contains(vendor.getId())) {
            vendorIds.add(vendor.getId());
            System.out.println("Vendor " + vendor.getName() + " (ID: " + vendor.getId() + ") added to Event " + id);
        }
    }

    public void addTicketsToPool(int vendorId) {
        // Here, we assume that each vendor adds tickets to the pool
        System.out.println("Vendor " + vendorId + " is adding tickets to the pool.");
        ticketPool.addTickets(1, 50.0, vendorId);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartedBy() {
        return startedBy;
    }

    public void setStartedBy(int startedBy) {
        this.startedBy = startedBy;
    }

    public List<Integer> getVendorIds() {
        return vendorIds;
    }

    public void setVendorIds(List<Integer> vendorIds) {
        this.vendorIds = vendorIds;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }
}
