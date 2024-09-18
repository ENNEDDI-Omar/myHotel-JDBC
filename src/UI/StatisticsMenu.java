package UI;

import Service.StatisticsService;
import java.util.Scanner;

public class StatisticsMenu {
    private StatisticsService statisticsService;
    private Scanner scanner;

    public StatisticsMenu(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
        this.scanner = new Scanner(System.in);
    }

    public void displayStatisticsMenu() {
        while (true) {
            System.out.println("\nStatistics Menu:");
            System.out.println("1. Show Occupancy Rates");
            System.out.println("2. Show Popular Room Types");
            System.out.println("3. Exit Statistics Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    displayOccupancyRates();
                    break;
                case 2:
                    displayPopularRoomTypes();
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private void displayOccupancyRates() {
        System.out.println("Occupancy Rates:");
        statisticsService.calculateOccupancyRates().forEach((roomId, rate) ->
                System.out.println("Room ID: " + roomId + ", Occupancy Rate: " + rate + "%"));
    }

    private void displayPopularRoomTypes() {
        System.out.println("Popular Room Types:");
        statisticsService.getPopularRoomTypes().forEach((type, count) ->
                System.out.println("Room Type: " + type + ", Number of Reservations: " + count));
    }
}
