package org.ticketsystem;

public class Ticket {
    private int id;
    private double price;
    private int soldBy;
    private boolean isReserved;

    // Constructor to initialize a ticket
    public Ticket(int id, double price, int soldBy) {
        this.id = id;
        this.price = price;
        this.soldBy = soldBy;
        this.isReserved = false;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSoldBy() {
        return soldBy;
    }

    public void setSoldBy(int soldBy) {
        this.soldBy = soldBy;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public String toString() {
        return "Ticket{id=" + id + ", price=" + price + ", soldBy=" + soldBy + ", isReserved=" + isReserved + "}";
    }
}
