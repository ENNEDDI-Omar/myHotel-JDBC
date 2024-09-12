package Utils;

import Exceptions.InvalidUserException;

public class ValidationUtils {
    public static void validateDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty");
        }
    }

    public static void validateBadgeNumber(String badgeNumber) {
        if (badgeNumber == null || badgeNumber.trim().isEmpty()) {
            throw new InvalidUserException("Badge number cannot be empty");
        }
    }

    public static void validateLoyaltyPoints(int loyaltyPoints) {
        if (loyaltyPoints < 0) {
            throw new IllegalArgumentException("Loyalty points cannot be negative");
        }
    }
}
