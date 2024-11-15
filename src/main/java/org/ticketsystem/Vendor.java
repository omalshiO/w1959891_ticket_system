package org.ticketsystem;

public class Vendor implements Runnable {

    private int id;
    private String name;
    private String email;

    public Vendor(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }


    @Override
    public void run() {
        System.out.println("Vendor " + name + " (ID: " + id + ") started.");

        try {
            while (true) {
                addTicketsToEvent(id);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Vendor " + name + " interrupted.");
        }
    }


    private void addTicketsToEvent(int vendorId) {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
