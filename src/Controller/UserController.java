package Controller;

import Entities.Client;
import Entities.Employee;
import Service.UserService;

import java.util.Scanner;

public class UserController {
    private UserService userService;
    private Scanner scanner;

    public UserController(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void signUpClient() {
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPassword(password);
        client.setLoyaltyPoints(0);  // Initialiser les points de fidélité à zéro

        //userService.insertUser(client);
        System.out.println("Client signed up successfully with 0 loyalty points!");
    }

    public void signUpEmployee() {
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        System.out.println("Enter your department:");
        String department = scanner.nextLine();
        System.out.println("Enter your badge number:");
        String badgeNumber = scanner.nextLine();

        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setDepartment(department);
        employee.setBadgeNumber(badgeNumber);

        userService.signUp();
        System.out.println("Employee signed up successfully!");
    }

//    public void signUp(){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter your user name : ");
//        String name1 = sc.nextLine();
//        System.out.println("Enter your email : ");
//        String email1 = sc.nextLine();
//        System.out.println("Enter your password : ");
//        String password1 = sc.nextLine();
//        System.out.println("Enter your CIN : ");
//        String cin1 = sc.nextLine();
//        userService.signUp(name1,email1,password1);
//        System.out.println("User signed up successfully!");
//    }

//    public boolean signIn(){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter your email : ");
//        String email1 = sc.nextLine();
//        System.out.println("Enter your password : ");
//        String password1 = sc.nextLine();
//        User user = userService.signIn(email1,password1);
//        if(user != null){
//            System.out.println("User signed in successfully!");
//            return true;
//        }else{
//            System.out.println("Invalid email or password!");
//            return false;
//        }
//    }
}
