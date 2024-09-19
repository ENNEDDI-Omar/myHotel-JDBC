import Entities.Reservation;
import Repository.PricingRepository;
import Repository.ReservationRepository;
import Repository.RoomRepository;
import Repository.UserRepository;
import Service.ReservationService;
import Service.RoomService;
import Service.StatisticsService;
import Service.UserService;
import UI.MainMenu;
import Utils.ReservationValidation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Instantiate repositories
//        UserRepository userRepository = new UserRepository();
//        RoomRepository roomRepository = new RoomRepository();
//        PricingRepository pricingRepository = new PricingRepository();
//        ReservationRepository reservationRepository = new ReservationRepository();
//
//        // Services
//        UserService userService = new UserService(userRepository);
//        RoomService roomService = new RoomService(roomRepository);
//        StatisticsService statisticsService = new StatisticsService(reservationRepository);
//
//        // Validation class instance
//        ReservationValidation reservationValidation = new ReservationValidation(roomService);
//
//        // Instantiate the ReservationService with the validation class
//        ReservationService reservationService = new ReservationService(reservationRepository, reservationValidation, pricingRepository, roomRepository);
//
//        // Create the MainMenu instance with all services
//        MainMenu mainMenu = new MainMenu(userService, roomService, reservationService, statisticsService);
//        mainMenu.displayMainMenu();

        ReservationService reservationService = new ReservationService();

        LocalDate startDate = LocalDate.parse("2024-09-15");
        LocalDate endDate = LocalDate.parse("2024-09-17");

//        Optional<Reservation> reservation = reservationService.bookAvailableRoom("Double", startDate, endDate);
//
//        reservation.ifPresentOrElse(
//                r -> System.out.println("Reservation successful for room id: " + r.getRoom().getId()),
//                () -> System.out.println("No available rooms found for the specified type and dates.")
//        );
    }
}
