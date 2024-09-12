package Repository;

import DAO.UserDAO;
import Database.DbConnection;
import Entities.Client;
import Entities.Employee;
import Entities.User;
import Entities.Role;
import Enums.UserStatus;
import Utils.PasswordUtils;

import java.sql.*;

public class UserRepository implements UserDAO {

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users (name, email, password, status, role_id, loyalty_points, department, badge_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getStatus().name());
            stmt.setInt(5, user.getRole().getId());
            if (user instanceof Client) {
                Client client = (Client) user;
                stmt.setInt(6, client.getLoyaltyPoints());
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.VARCHAR);
            } else if (user instanceof Employee) {
                Employee employee = (Employee) user;
                stmt.setNull(6, Types.INTEGER);
                stmt.setString(7, employee.getDepartment());
                stmt.setString(8, employee.getBadgeNumber());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = null;

        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // After retrieving the user by email, now check the password
                String storedHashPassword = rs.getString("password");
                boolean passwordMatch = PasswordUtils.checkPassword(password, storedHashPassword);

                if (passwordMatch) {
                    int roleId = rs.getInt("role_id");
                    if (roleId == 1) {
                        user = new Client(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                storedHashPassword,
                                new Role(roleId, "Client")
                        );
                        ((Client) user).setLoyaltyPoints(rs.getInt("loyalty_points"));
                    } else if (roleId == 2) {
                        user = new Employee(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                storedHashPassword,
                                new Role(roleId, "Employee"),
                                rs.getString("department"),
                                rs.getString("badge_number")
                        );
                    }
                    if (user != null) {
                        user.setStatus(UserStatus.valueOf(rs.getString("status")));
                    }
                } else {
                    System.out.println("Invalid login credentials.");
                }
            } else {
                System.out.println("No user found with that email.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user: " + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }


    private Role getRoleById(int roleId) {
        String sql = "SELECT name FROM roles WHERE id = ?";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Role(roleId, rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving role: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        // Placeholder for compilation
    }

    @Override
    public void deleteUser(int userId) {
        // Placeholder for compilation
    }

}
