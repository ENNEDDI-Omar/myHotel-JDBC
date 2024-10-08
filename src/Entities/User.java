package Entities;

import Enums.UserStatus;
import Utils.PasswordUtils;
import Utils.ValidationEmailAndPasswordUtils;

public abstract class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private UserStatus status;
    private Role role;

    // Constructeurs, getters et setters
    public User() {
        this.status = UserStatus.Active;
    }

    public User(int id, String name, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = UserStatus.Active;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (!ValidationEmailAndPasswordUtils.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) {
        if (!ValidationEmailAndPasswordUtils.isValidPassword(password)) {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }
        this.password = PasswordUtils.hashPassword(password);
    }

    public UserStatus getStatus() { return status; }
    public void setStatus(UserStatus status) { this.status = status; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }



    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role=" + getRole().getName() +
                ", status=" + getStatus() +
                '}';
    }
}
