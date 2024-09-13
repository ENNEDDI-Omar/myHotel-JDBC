import Repository.UserRepository;
import Service.UserService;
import UI.MainMenu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
// Assurez-vous que les dépendances nécessaires sont initialisées
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);

        // Créez l'instance de MainMenu et lancez l'interface utilisateur
        MainMenu mainMenu = new MainMenu(userService);
        mainMenu.displayMainMenu();


    }
}