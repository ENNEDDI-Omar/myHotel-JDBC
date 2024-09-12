package Service;

import Entities.Client;
import Entities.Employee;
import Entities.Role;
import Repository.UserRepository;
import Utils.PasswordUtils;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Inscription des clients avec initialisation des points de fidélité à zéro
    public void registerClient(String name, String email, String password) {
        String hashedPassword = PasswordUtils.hashPassword(password);
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPassword(hashedPassword);
        client.setLoyaltyPoints(0);
        client.setRole(new Role(1, "Client"));
        userRepository.saveUser(client);
        System.out.println("Client registered with 0 loyalty points.");
    }

    // Inscription des employés avec saisie de département et numéro de badge
    public void registerEmployee(String name, String email, String password, String department, String badgeNumber) {
        String hashedPassword = PasswordUtils.hashPassword(password);
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(hashedPassword);
        employee.setDepartment(department);
        employee.setBadgeNumber(badgeNumber);
        employee.setRole(new Role(2, "Employee"));
        userRepository.saveUser(employee);
        System.out.println("Employee registered with department and badge number.");
    }
}
