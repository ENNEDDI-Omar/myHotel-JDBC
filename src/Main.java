import Repository.ReservationRepository;
import Repository.RoomRepository;
import Repository.UserRepository;
import Service.ReservationService;
import Service.RoomService;
import Service.StatisticsService;
import Service.UserService;
import UI.MainMenu;
import Utils.ReservationValidation;

public class Main {
    public static void main(String[] args) {
        // Instantiate repositories
        UserRepository userRepository = new UserRepository();
        RoomRepository roomRepository = new RoomRepository();
        ReservationRepository reservationRepository = new ReservationRepository();

        // Services
        UserService userService = new UserService(userRepository);
        RoomService roomService = new RoomService(roomRepository);
        StatisticsService statisticsService = new StatisticsService(reservationRepository);

        // Validation class instance
        ReservationValidation reservationValidation = new ReservationValidation(roomService);

        // Instantiate the ReservationService with the validation class
        ReservationService reservationService = new ReservationService(reservationRepository, reservationValidation);

        // Create the MainMenu instance with all services
        MainMenu mainMenu = new MainMenu(userService, roomService, reservationService, statisticsService);
        mainMenu.displayMainMenu();
    }
}
