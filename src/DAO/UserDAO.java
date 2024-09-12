package DAO;

import Entities.Role;
import Entities.User;

public interface UserDAO {
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    User getUserByEmailAndPassword(String email, String password);

}
