package org.ticketsystem;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        System.out.println("WELCOME TO THE EVENT TICKET SYSTEM");
        System.out.println("PLEASE ENTER THE CONFIGURATION SETTINGS TO START A NEW EVENT");


        int totalTickets = 0;
        int ticketReleaseRate = 0;
        int customerRetrievalRate = 0;
        int maxTicketCapacity = 0;

        totalTickets = getIntInput(scanner, "Enter the Total number of tickets: ");

        ticketReleaseRate = getIntInput(scanner, "Enter the Ticket Releasing rate (tickets per minute): ");

        customerRetrievalRate = getIntInput(scanner, "Enter the Customer Retrieval rate (tickets per minute): ");

        maxTicketCapacity = getIntInput(scanner, "Enter the Max Ticket Capacity (max tickets allowed in the system): ");


        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);


//        System.out.println("\nConfiguration Summary:");
//        System.out.println("Total Tickets: " + config.getTotalTickets());
//        System.out.println("Ticket Release Rate: " + config.getTicketReleaseRate() + " tickets/minute");
//        System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate() + " tickets/minute");
//        System.out.println("Max Ticket Capacity: " + config.getMaxTicketCapacity() + " tickets");


        saveConfigurationToJson(config, "ticketing_config.json");
        Event event = createNewEvent(config);

        Vendor vendor1 = new Vendor(1, "Vendor 1", "vendor1@example.com");
        Vendor vendor2 = new Vendor(2, "Vendor 2", "vendor2@example.com");

        event.addVendor(vendor1);
        event.addVendor(vendor2);

        Thread vendorThread1 = new Thread(vendor1);
        Thread vendorThread2 = new Thread(vendor2);
        vendorThread1.start();
        vendorThread2.start();


        System.out.println("\nEvent Created Successfully!");
        System.out.println("Event ID: " + event.getId());
        System.out.println("Event Started By: " + event.getStartedBy());
        System.out.println("Event Active: " + event.isActive());
        System.out.println("Max Ticket Capacity: " + event.getConfiguration().getMaxTicketCapacity());
        System.out.println("Tickets Available in the Pool: " + event.getTicketPool().getAvailableTickets());


        scanner.close();
    }


    private static int getIntInput(Scanner scanner, String prompt) {
        int input = -1;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine());
                if (input <= 0) {
                    System.out.println("Input must be a positive number. Please try again.");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
        return input;
    }


    private static void saveConfigurationToJson(Configuration config, String fileName) {
        Gson gson = new Gson();

        String json = gson.toJson(config);


        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
            System.out.println("Configuration saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving configuration to file: " + e.getMessage());
        }
    }


    private static Event createNewEvent(Configuration config) {

        int eventId = 1;
        int startedBy = 1;


        Event event = new Event(eventId, config);


        event.setStartedBy(startedBy);
        event.setActive(true);

        return event;
    }
}
