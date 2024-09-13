package UI;

import Service.UserService;

import java.util.Scanner;

public class MainMenu {
    private UserService userService;
    private AuthenticationMenu authenticationMenu;
    private InformationsMenu informationMenu;
    private ReservationMenu reservationMenu;
    private Scanner scanner;

    public MainMenu(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
        this.authenticationMenu = new AuthenticationMenu(userService);
        //this.informationMenu = new InformationsMenu(userService);
        //this.reservationMenu = new ReservationMenu(userService);
    }

    public void displayMainMenu() {
        boolean loggedIn = false;
        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Authenticate");
            System.out.println("2. View Information");
            System.out.println("3. Manage Reservations");
            System.out.println("4. Exit");
            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left-over

            switch (choice) {
                case 1:
                    loggedIn = authenticationMenu.displayAuthenticationMenu();
                    break;
                case 2:
                    if (loggedIn) {
                        //informationMenu.toString();
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;
                case 3:
                    if (loggedIn) {
                        //reservationMenu.toString();
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }
    }
}
