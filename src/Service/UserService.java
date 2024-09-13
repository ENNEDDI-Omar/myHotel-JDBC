package Service;

import Entities.Role;
import Repository.UserRepository;
import Entities.User;
import Entities.Client;
import Entities.Employee;
import java.util.Scanner;

public class UserService {
    private UserRepository userRepository;
    private Scanner scanner;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.scanner = new Scanner(System.in);
    }

    public void registerUser() {
        System.out.println("Enter user type (client/employee):");
        String userType = scanner.nextLine();
        User user = "client".equalsIgnoreCase(userType) ? new Client() : new Employee();

        System.out.println("Enter name:");
        user.setName(scanner.nextLine());
        System.out.println("Enter email:");
        user.setEmail(scanner.nextLine());
        System.out.println("Enter password:");
        user.setPassword(scanner.nextLine()); // Password is hashed inside the setPassword method

        if (user instanceof Employee) {
            System.out.println("Enter department:");
            ((Employee) user).setDepartment(scanner.nextLine());
            System.out.println("Enter badge number:");
            ((Employee) user).setBadgeNumber(scanner.nextLine());
        }

        Role role = userRepository.getRoleByName(userType.equalsIgnoreCase("client") ? "Client" : "Employee");
        if (role != null) {
            user.setRole(role);
            userRepository.saveUser(user);

            System.out.println("User registered as " + role.getName() + "successfully.");
        } else {
            System.out.println("Role not found, cannot register user.");
        }
    }

    public boolean signIn(String email, String password) {
        User user = userRepository.getUserByEmailAndPassword(email, password);
        if (user != null) {
            System.out.println("User " + user.getName() + " logged in successfully.");
            return true;
        } else {
            System.out.println("Login failed for email: " + email);
            return false;
        }
    }


    public void closeService() {
        scanner.close();
    }
}
