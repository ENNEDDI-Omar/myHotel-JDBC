package Exceptions;

public class ReservationExeption extends RuntimeException {
    public ReservationExeption(String message) {
        super(message);
    }
    public ReservationExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
