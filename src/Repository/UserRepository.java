package Repository;

import DAO.UserDAO;
import Database.DbConnection;
import Entities.Client;
import Entities.Employee;
import Entities.User;
import Entities.Role;
import Enums.UserStatus;

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
    public User getUserByEmailAndPassword(String email, String password)
    {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        User user = null;

        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setStatus(UserStatus.valueOf(rs.getString("status")));
                Role role = getRoleById(rs.getInt("role_id"));
                user.setRole(role);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving user: " + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        // Placeholder for compilation
    }

    @Override
    public void deleteUser(int userId) {
        // Placeholder for compilation
    }
        ;
//    public void insertUser(User user)
//    {
//        String sql = "INSERT INTO users (name, email, password, status, role_id) VALUES(?,?,?,?)";
//
//      try (Connection connection = DbConnection.getInstance().getConx();
//           PreparedStatement stmt = connection.prepareStatement(sql))
//      {
//          stmt.setString(1, user.getName());
//          stmt.setString(2, user.getEmail());
//          stmt.setString(3, user.getPassword());
//          stmt.setString(4, user.getStatus().name());
//          stmt.setInt(5, user.getRole().getId());
//
//          stmt.executeUpdate();
//
//      } catch (SQLException e)
//      {
//          System.out.println("User Isertion Error: " + e.getMessage());
//          e.printStackTrace();
//      }
//    }

//

        private Role getRoleById ( int roleId){
            // Cette méthode devrait implémenter la logique pour récupérer un objet Role basé sur roleId
            // Simulons un rôle pour l'exemple
            return new Role(roleId, "RoleName");  // Assurez-vous que le constructeur de Role et ses paramètres correspondent.
        }
    }
