package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class HotelResource {

    protected static final CustomerService customerService =
            CustomerService.getInstance();

    protected static final ReservationService reservationService =
            ReservationService.getInstance();

    public Customer fetchCustomer(String email) {

        return customerService.fetchCustomer(email);
    }

    public void registerCustomer(String email,
                                 String firstName,
                                 String lastName) {

        customerService.registerCustomer(
                firstName,
                lastName,
                email
        );
    }

    public IRoom fetchRoom(String roomNumber) {

        return reservationService.fetchRoom(roomNumber);
    }

    public Reservation reserveRoom(String customerEmail,
                                   IRoom room,
                                   Date checkIn,
                                   Date checkOut) {

        Customer customer =
                customerService.fetchCustomer(customerEmail);

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer does not exist."
            );
        }

        return reservationService.bookRoom(
                customer,
                room,
                checkIn,
                checkOut
        );
    }

    public Collection<Reservation> fetchCustomerReservations(String email) {

        Customer customer =
                customerService.fetchCustomer(email);

        return reservationService.getReservationsByCustomer(customer);
    }

    public Collection<IRoom> findAvailableRooms(Date checkIn,
                                                Date checkOut) {

        return reservationService.searchAvailableRooms(
                checkIn,
                checkOut
        );
    }

    public Collection<IRoom> getRecommendedRooms(Date checkIn,
                                                 Date checkOut) {

        return reservationService.suggestAlternativeRooms(
                checkIn,
                checkOut
        );
    }

    public static Date convertStringToDate(String dateValue) {

        SimpleDateFormat formatter =
                new SimpleDateFormat("yyyy-MM-dd");

        formatter.setLenient(false);

        try {

            return formatter.parse(dateValue);

        } catch (ParseException exception) {

            System.out.println("Invalid date format.");
            return null;
        }
    }
}