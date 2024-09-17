package UI;

import Service.RoomService;
import Entities.Room;
import Enums.RoomType;

import java.util.List;
import java.util.Scanner;

public class RoomMenu {
    private RoomService roomService;
    private Scanner scanner;

    public RoomMenu(RoomService roomService) {
        this.roomService = roomService;
        this.scanner = new Scanner(System.in);
    }

    public void displayRoomMenu() {
        while (true) {
            System.out.println("\nRoom Management Menu:");
            System.out.println("1. Add Room");
            System.out.println("2. Update Room");
            System.out.println("3. Delete Room");
            System.out.println("4. List All Rooms");
            System.out.println("5. Exit Room Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    updateRoom();
                    break;
                case 3:
                    deleteRoom();
                    break;
                case 4:
                    listRooms();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice, please enter a number between 1 and 5.");
            }
        }
    }

    private void addRoom() {
        System.out.println("Enter room number:");
        String roomNumber = scanner.nextLine();
        System.out.println("Enter room type (Single, Double, Suite, Deluxe):");
        RoomType type = RoomType.valueOf(scanner.nextLine());
        System.out.println("Is the room available? (true/false):");
        boolean availability = scanner.nextBoolean();
        scanner.nextLine();  // Clear buffer

        roomService.addRoom(roomNumber, type, availability);
    }

    private void updateRoom() {
        System.out.println("Enter room ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        System.out.println("Enter new room number:");
        String roomNumber = scanner.nextLine();
        System.out.println("Enter new room type (Single, Double, Suite, Deluxe):");
        RoomType type = RoomType.valueOf(scanner.nextLine());
        System.out.println("Is the room available? (true/false):");
        boolean availability = scanner.nextBoolean();
        scanner.nextLine();  // Clear buffer

        roomService.updateRoom(id, roomNumber, type, availability);
    }

    private void deleteRoom() {
        System.out.println("Enter room ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();  // Clear buffer
        roomService.deleteRoom(id);
    }

    private void listRooms() {
        List<Room> rooms = roomService.listAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            rooms.forEach(System.out::println);
        }
    }
}
