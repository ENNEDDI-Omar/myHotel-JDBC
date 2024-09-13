package UI;

import Service.UserService;
import java.util.Scanner;

public class AuthenticationMenu {
    private UserService userService;
    private Scanner scanner;

    public AuthenticationMenu(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
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
                    userService.registerUser();  // Assuming this method handles the full registration process
                    break;
                case 2:
                    login();  // Handle user login
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;  // Exit the menu
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private void login() {
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Login successful! Welcome to your dashboard!");
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

    public void closeMenu() {
        scanner.close();  //
    }
}
