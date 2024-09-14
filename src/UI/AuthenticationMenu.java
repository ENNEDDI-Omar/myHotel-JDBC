package UI;

import Entities.User;
import Exceptions.InvalidUserException;
import Service.UserService;
import java.util.Scanner;

public class AuthenticationMenu {
    private UserService userService;
    private Scanner scanner;

    public AuthenticationMenu(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public boolean displayAuthenticationMenu() {
        while (true) {
            System.out.println("\nWelcome to the Authentication Menu");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline left-over

            switch (choice) {
                case 1:
                    userService.registerUser();
                    return false;
                case 2:
                    return userService.signIn();
                case 3:
                    System.out.println("Exiting...");
                    return false;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }


    public void closeMenu() {
        scanner.close();  //
    }
}
