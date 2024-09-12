package Entities;

import Exceptions.InvalidUserException;
import Utils.ValidationUtils;

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
        try {
            ValidationUtils.validateDepartment(department);
            this.department = department;
        } catch (IllegalArgumentException e) {
            throw new InvalidUserException("Failed to set department: " + e.getMessage(), e);
        }
    }

    public String getBadgeNumber() {return badgeNumber;}
    public void setBadgeNumber(String badgeNumber) {
        try {
            ValidationUtils.validateBadgeNumber(badgeNumber);
            this.badgeNumber = badgeNumber;
        } catch (IllegalArgumentException e) {
            throw new InvalidUserException("Failed to set badge number: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "department='" + getDepartment() + '\'' +
                ", badgeNumber='" + getBadgeNumber() + '\'' +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
