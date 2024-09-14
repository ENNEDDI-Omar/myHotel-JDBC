package Entities;

import Exceptions.InvalidUserException;
import Utils.ValidationUtils;

import java.util.HashSet;
import java.util.Set;

public class Client extends User {
    private int loyaltyPoints;
    private Set<Reservation> reservations = new HashSet<>();

    public Client() {super();}

    public Client(int id, String name, String email, String password, Role role) {
        super(id, name, email, password, role);
        this.loyaltyPoints = 0;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        try {
            ValidationUtils.validateLoyaltyPoints(loyaltyPoints);
            this.loyaltyPoints = loyaltyPoints;
        } catch (IllegalArgumentException e) {
            throw new InvalidUserException("Failed to set loyalty points: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                "loyaltyPoints=" + getLoyaltyPoints() +
                '}';
    }
}
