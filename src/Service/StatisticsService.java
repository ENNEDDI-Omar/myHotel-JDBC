package Service;

import DAO.ReservationDAO;
import Entities.Reservation;
import Entities.Room;
import java.util.HashMap;
import java.util.List;

public class StatisticsService {
    private ReservationDAO reservationDAO;

    public StatisticsService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    // Calculate the occupancy rate based on reservations
    public HashMap<Integer, Double> calculateOccupancyRates() {
        List<Reservation> reservations = reservationDAO.getAllReservations();
        HashMap<Integer, Integer> roomOccupancyCount = new HashMap<>();
        HashMap<Integer, Double> occupancyRates = new HashMap<>();

        // Assume each room is supposed to be available 365 days a year
        reservations.forEach(reservation -> {
            roomOccupancyCount.merge(reservation.getRoom().getId(), 1, Integer::sum);
        });

        // Calculating occupancy rate per room
        for (Reservation reservation : reservations) {
            int roomId = reservation.getRoom().getId();
            double rate = (double) roomOccupancyCount.get(roomId) / 365.0 * 100.0; // Percentage rate
            occupancyRates.put(roomId, rate);
        }

        return occupancyRates;
    }

    // Get the most popular room types based on the number of reservations
    public HashMap<String, Integer> getPopularRoomTypes() {
        List<Reservation> reservations = reservationDAO.getAllReservations();
        HashMap<String, Integer> roomTypeCounts = new HashMap<>();

        for (Reservation reservation : reservations) {
            String roomType = reservation.getRoom().getType().toString();
            roomTypeCounts.merge(roomType, 1, Integer::sum);
        }

        return roomTypeCounts;
    }

    // Method to print or return statistics for external use
    public void printStatistics() {
        HashMap<Integer, Double> occupancyRates = calculateOccupancyRates();
        HashMap<String, Integer> popularRoomTypes = getPopularRoomTypes();

        // Printing occupancy rates
        occupancyRates.forEach((roomId, rate) ->
                System.out.println("Room ID: " + roomId + " has an occupancy rate of: " + rate + "%"));

        // Printing popular room types
        popularRoomTypes.forEach((type, count) ->
                System.out.println("Room Type: " + type + " has " + count + " reservations"));
    }
}
