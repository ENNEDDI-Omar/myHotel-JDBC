package UI;

import Service.ReservationService;
import Service.RoomService;
import Service.UserService;

import java.util.Scanner;

public class MainMenu {
    private UserService userService;
    private RoomService roomService;
    private ReservationService reservationService;
    private AuthenticationMenu authenticationMenu;
    private InformationsMenu informationMenu;
    private ReservationMenu reservationMenu;
    private RoomMenu roomMenu;
    private Scanner scanner;

    public MainMenu(UserService userService, RoomService roomService, ReservationService reservationService) {
        this.userService = userService;
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.scanner = new Scanner(System.in);
        this.authenticationMenu = new AuthenticationMenu(userService);
       // this.informationMenu = new InformationsMenu(userService, reservationService);
        this.reservationMenu = new ReservationMenu(reservationService);
        this.roomMenu = new RoomMenu(roomService);
    }

    public void displayMainMenu() {
        boolean loggedIn = false;
        try {
            while (true) {
                System.out.println("Main Menu:");
                System.out.println("1. Authenticate");
                System.out.println("2. View Information");
                System.out.println("3. Manage Reservations");
                System.out.println("4. Manage Rooms");
                System.out.println("5. Exit");
                System.out.print("Please choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        loggedIn = authenticationMenu.displayAuthenticationMenu();
                        break;
                    case 2:
                        if (loggedIn) {
                           // informationMenu.display();
                        } else {
                            System.out.println("Please log in first.");
                        }
                        break;
                    case 3:
                        if (loggedIn) {
                            reservationMenu.displayReservationMenu();
                        } else {
                            System.out.println("Please log in first.");
                        }
                        break;
                    case 4:
                        if (loggedIn) {
                            roomMenu.displayRoomMenu();
                        } else {
                            System.out.println("Please log in first.");
                        }
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, 3, 4, or 5.");
                }
            }
        } finally {
            scanner.close();
        }
    }
}
