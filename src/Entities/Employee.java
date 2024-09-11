package Entities;

public class Employee extends User {
    private String department;
    private String badgeNumber;

    public Employee() {}

    public Employee(int id, String name, String email, String password, Role role, String department, String badgeNumber) {
        super(id, name, email, password, role);
        this.department = department;
        this.badgeNumber = badgeNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "department='" + department + '\'' +
                ", badgeNumber='" + badgeNumber + '\'' +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
