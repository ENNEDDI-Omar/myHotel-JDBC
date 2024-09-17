package UI;

import Entities.Reservation;
import Service.ReservationService;
import java.time.LocalDate;
import java.util.Scanner;

public class ReservationMenu {
    private ReservationService reservationService;
    private Scanner scanner;

    public ReservationMenu(ReservationService reservationService) {
        this.reservationService = reservationService;
        this.scanner = new Scanner(System.in);
    }

    public void displayReservationMenu() {
        int option;
        do {
            System.out.println("\nReservation Management Menu:");
            System.out.println("1. Add Reservation");
            System.out.println("2. Update Reservation");
            System.out.println("3. Delete Reservation");
            System.out.println("4. List All Reservations");
            System.out.println("5. Exit Reservation Menu");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (option) {
                case 1:
                    addReservation();
                    break;
                case 2:
                    updateReservation();
                    break;
                case 3:
                    deleteReservation();
                    break;
                case 4:
                    listAllReservations();
                    break;
                case 5:
                    System.out.println("Exiting Reservation Menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 5.");
            }
        } while (option != 5);
    }

    private void addReservation() {
        try {
            System.out.print("Enter Room ID: ");
            int roomId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Client ID: ");
            int clientId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Start Date (YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter End Date (YYYY-MM-DD): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            Reservation reservation = new Reservation();  // Assuming you have a constructor to set these values
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            // Set other values, e.g., Room and Client based on IDs

            reservationService.addReservation(reservation);
        } catch (Exception e) {
            System.out.println("Error adding reservation: " + e.getMessage());
        }
    }

    private void updateReservation() {
        // Implement similar to addReservation with additional logic for fetching and updating existing reservation
    }

    private void deleteReservation() {
        System.out.print("Enter Reservation ID to delete: ");
        int reservationId = scanner.nextInt();
        try {
            reservationService.deleteReservation(reservationId);
            System.out.println("Reservation deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting reservation: " + e.getMessage());
        }
    }

    private void listAllReservations() {
        try {
            reservationService.getAllReservations().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error retrieving reservations: " + e.getMessage());
        }
    }
}
