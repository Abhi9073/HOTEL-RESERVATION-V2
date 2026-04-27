import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final HotelResource hotelResource =
            new HotelResource();

    private static final AdminResource adminResource =
            new AdminResource();

    public static void launch() {

        boolean applicationRunning = true;

        while (applicationRunning) {

            displayMenu();

            int choice = getMenuChoice();

            switch (choice) {

                case 1 -> reserveRoomFlow();

                case 2 -> displayReservations();

                case 3 -> registerCustomer();

                case 4 -> AdminMenu.openAdminPanel();

                case 5 -> {
                    System.out.println(
                            "Thank you for using the system."
                    );

                    applicationRunning = false;
                }

                default -> System.out.println(
                        "Invalid menu option."
                );
            }
        }
    }

    private static void displayMenu() {

        System.out.println("""
                
                ========== MAIN MENU ==========
                1. Find & Reserve Room
                2. View My Reservations
                3. Create Account
                4. Admin Panel
                5. Exit
                =================================
                """);
    }

    private static int getMenuChoice() {

        while (true) {

            try {

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (Exception ex) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    private static void registerCustomer() {

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        String email = readEmail();

        Customer existingCustomer =
                hotelResource.fetchCustomer(email);

        if (existingCustomer != null) {

            System.out.println(
                    "Customer already exists."
            );

            return;
        }

        hotelResource.registerCustomer(
                email,
                firstName,
                lastName
        );

        System.out.println(
                "Account created successfully."
        );
    }

    private static void reserveRoomFlow() {

        String email = readEmail();

        Customer customer =
                hotelResource.fetchCustomer(email);

        if (customer == null) {

            System.out.println(
                    "No customer found with this email."
            );

            return;
        }

        Date checkIn = getValidDate(
                "Enter Check-In Date (yyyy-MM-dd): "
        );

        Date checkOut = getValidDate(
                "Enter Check-Out Date (yyyy-MM-dd): "
        );

        while (!checkOut.after(checkIn)) {

            System.out.println(
                    "Check-out date must be after check-in date."
            );

            checkOut = getValidDate(
                    "Enter Check-Out Date Again (yyyy-MM-dd): "
            );
        }

        Collection<IRoom> availableRooms =
                hotelResource.findAvailableRooms(
                        checkIn,
                        checkOut
                );

        boolean usingRecommendedDates = false;

        if (availableRooms.isEmpty()) {

            System.out.println("""
                
                We've only found rooms on alternative dates:.
                """);

            Collection<IRoom> recommendedRooms =
                    hotelResource.getRecommendedRooms(
                            checkIn,
                            checkOut
                    );

            if (recommendedRooms.isEmpty()) {

                System.out.println(
                        "No alternative rooms available."
                );

                return;
            }

            Date suggestedCheckIn =
                    addDays(checkIn, 7);

            Date suggestedCheckOut =
                    addDays(checkOut, 7);

            System.out.println("""
                
                Suggested Alternative Dates:
                """);

            System.out.println(
                    "Check-In  : " + suggestedCheckIn
            );

            System.out.println(
                    "Check-Out : " + suggestedCheckOut
            );

            System.out.println("""
                
                Recommended Rooms:
                """);

            for (IRoom room : recommendedRooms) {

                System.out.println(room);
            }

            System.out.print("""
                
                Would you like to book using these dates? (y/n): 
                """);

            String choice =
                    scanner.nextLine().trim();

            if (!choice.equalsIgnoreCase("y")) {

                System.out.println(
                        "Reservation cancelled."
                );

                return;
            }

            usingRecommendedDates = true;

            availableRooms = recommendedRooms;
        }

        if (usingRecommendedDates) {

            checkIn = addDays(checkIn, 7);

            checkOut = addDays(checkOut, 7);
        }

        System.out.println("""
            
            ===== AVAILABLE ROOMS =====
            """);

        for (IRoom room : availableRooms) {

            System.out.println(room);
        }

        System.out.print("""
            
            Enter Room Number To Reserve: 
            """);

        String roomNumber =
                scanner.nextLine().trim();

        IRoom selectedRoom =
                hotelResource.fetchRoom(roomNumber);

        if (selectedRoom == null
                || availableRooms.stream()
                .noneMatch(room ->
                        room.getRoomNumber()
                                .equals(roomNumber))) {

            System.out.println(
                    "Invalid room selection."
            );

            return;
        }

        Reservation reservation =
                hotelResource.reserveRoom(
                        email,
                        selectedRoom,
                        checkIn,
                        checkOut
                );

        System.out.println("""
            
            Reservation Successful!
            """);

        System.out.println(reservation);
    }

    private static void displayReservations() {

        String email = readEmail();

        Collection<Reservation> reservations =
                hotelResource.fetchCustomerReservations(email);

        if (reservations.isEmpty()) {

            System.out.println(
                    "No reservations found."
            );

            return;
        }

        System.out.println("""
                
                ===== YOUR RESERVATIONS =====
                """);

        for (Reservation reservation : reservations) {

            System.out.println(reservation);
        }
    }

    private static String readEmail() {

        while (true) {

            System.out.print(
                    "Enter Email Address: "
            );

            String email =
                    scanner.nextLine().trim().toLowerCase();

            if (email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
            )) {

                return email;
            }

            System.out.println(
                    "Invalid email format."
            );
        }
    }

    private static Date getValidDate(String message) {

        while (true) {

            System.out.print(message);

            String dateInput =
                    scanner.nextLine();

            Date parsedDate =
                    HotelResource.convertStringToDate(
                            dateInput
                    );

            if (parsedDate != null
                    && parsedDate.after(new Date())) {

                return parsedDate;
            }

            System.out.println(
                    "Please enter a valid future date."
            );
        }
    }

    private static Date addDays(Date date, int days) {

        Calendar calendar =
                Calendar.getInstance();

        calendar.setTime(date);

        calendar.add(Calendar.DAY_OF_MONTH, days);

        return calendar.getTime();
    }
}
