package api;

import model.Customer;
import model.IRoom;

import java.util.Collection;
import java.util.List;

public class AdminResource extends HotelResource {

    public void addRooms(List<IRoom> roomList) {

        for (IRoom room : roomList) {

            reservationService.addRoom(room);
        }

        System.out.println("""
                -------------------------
                Rooms Added Successfully
                -------------------------
                """);
    }

    public Collection<IRoom> fetchAllRooms() {

        return reservationService.fetchAllRooms();
    }

    public Collection<Customer> fetchAllCustomers() {

        return customerService.fetchAllCustomers();
    }

    public void showAllCustomers() {

        Collection<Customer> customers = fetchAllCustomers();

        if (customers.isEmpty()) {

            System.out.println("No registered customers found.");
            return;
        }

        System.out.println("""
                ========= CUSTOMERS =========
                """);

        for (Customer customer : customers) {

            System.out.println(customer);
        }
    }

    public void showAllRooms() {

        Collection<IRoom> rooms = fetchAllRooms();

        if (rooms.isEmpty()) {

            System.out.println("No rooms available.");
            return;
        }

        System.out.println("""
                ========= ROOM LIST =========
                """);

        for (IRoom room : rooms) {

            System.out.println(room);
        }
    }

    public void showAllReservations() {

        System.out.println("""
                ===== RESERVATION DETAILS =====
                """);

        reservationService.displayAllReservations();
    }
}