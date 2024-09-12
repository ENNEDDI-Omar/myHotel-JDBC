package UI;

import Controller.UserController;

import java.util.Scanner;

public class AuthenticationMenu {
    private UserController userController;
    private Scanner scanner;

    public AuthenticationMenu(UserController userController) {
        this.userController = userController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline left-over

            switch (choice) {
                case 1:
                    userController.signUp();
                    break;
                case 2:
                    if (userController.signIn()) {
                        System.out.println("Welcome to your dashboard!");

                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }
}
