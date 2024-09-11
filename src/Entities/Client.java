package Entities;

public class Client extends User {
    private int loyaltyPoints;

    public Client() {}

    public Client(int id, String name, String email, String password, Role role, int loyaltyPoints) {
        super(id, name, email, password, role);
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public String toString() {
        return "Client{" +
                "loyaltyPoints=" + loyaltyPoints +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
