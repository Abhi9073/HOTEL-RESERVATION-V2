import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;
import model.FreeRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private static final Scanner scanner = new Scanner(System.in);

    private static final AdminResource adminResource =
            new AdminResource();

    public static void openAdminPanel() {

        boolean running = true;

        while (running) {

            printAdminMenu();

            int option = getIntegerInput();

            switch (option) {

                case 1 -> adminResource.showAllCustomers();

                case 2 -> adminResource.showAllRooms();

                case 3 -> adminResource.showAllReservations();

                case 4 -> addRoomsFlow();

                case 5 -> running = false;

                default -> System.out.println(
                        "Invalid option selected."
                );
            }
        }
    }

    private static void printAdminMenu() {

        System.out.println("""
                
                ========= ADMIN PANEL =========
                1. View Customers
                2. View Rooms
                3. View Reservations
                4. Add New Rooms
                5. Return to Main Menu
                =================================
                """);
    }

    private static int getIntegerInput() {

        while (true) {

            try {

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException ex) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    private static void addRoomsFlow() {

        List<IRoom> roomList = new ArrayList<>();

        System.out.print("How many rooms do you want to add? ");

        int totalRooms = getIntegerInput();

        for (int i = 1; i <= totalRooms; i++) {

            System.out.println("\nAdding Room " + i);

            System.out.print("Room Number: ");
            String roomNumber = scanner.nextLine();

            double roomPrice = getRoomPrice();

            RoomType roomType = getRoomType();

            // Create FreeRoom when price is 0
            if (roomPrice == 0) {

                roomList.add(
                        new FreeRoom(roomNumber, roomType)
                );

            } else {

                roomList.add(
                        new Room(roomNumber, roomPrice, roomType)
                );
            }
        }

        adminResource.addRooms(roomList);
    }

    private static double getRoomPrice() {

        while (true) {

            try {

                System.out.print("Room Price: ");

                double price =
                        Double.parseDouble(scanner.nextLine());

                if (price >= 0) {
                    return price;
                }

                System.out.println(
                        "Price cannot be negative."
                );

            } catch (NumberFormatException ex) {

                System.out.println(
                        "Invalid price entered."
                );
            }
        }
    }

    private static RoomType getRoomType() {

        while (true) {

            System.out.print(
                    "Room Type (single/double): "
            );

            String type =
                    scanner.nextLine().trim().toLowerCase();

            switch (type) {

                case "single":
                    return RoomType.SINGLE;

                case "double":
                    return RoomType.DOUBLE;

                default:
                    System.out.println(
                            "Please enter single or double."
                    );
            }
        }
    }
}