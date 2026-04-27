package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static ReservationService instance;

    // Stores all rooms using unique room number
    private final Map<String, IRoom> roomMap;

    // Stores all reservations
    private final List<Reservation> reservationList;

    private ReservationService() {

        roomMap = new HashMap<>();
        reservationList = new ArrayList<>();
    }

    public static ReservationService getInstance() {

        if (instance == null) {

            instance = new ReservationService();
        }

        return instance;
    }

    // Add room
    public boolean addRoom(IRoom room) {

        if (roomMap.containsKey(room.getRoomNumber())) {

            return false;
        }

        roomMap.put(room.getRoomNumber(), room);

        return true;
    }

    // Fetch single room
    public IRoom fetchRoom(String roomNumber) {

        return roomMap.get(roomNumber);
    }

    // Fetch all rooms
    public Collection<IRoom> fetchAllRooms() {

        return roomMap.values();
    }

    // Search available rooms
    public Collection<IRoom> searchAvailableRooms(Date checkIn,
                                                  Date checkOut) {

        List<IRoom> availableRooms =
                new ArrayList<>(roomMap.values());

        for (Reservation reservation : reservationList) {

            boolean overlap =
                    reservation.getCheckInDate()
                            .before(checkOut)

                            &&

                            reservation.getCheckOutDate()
                                    .after(checkIn);

            if (overlap) {

                availableRooms.remove(
                        reservation.getRoom()
                );
            }
        }

        return availableRooms;
    }

    // Reserve room
    public Reservation bookRoom(Customer customer,
                                IRoom room,
                                Date checkIn,
                                Date checkOut) {

        for (Reservation reservation : reservationList) {

            boolean sameRoom =
                    reservation.getRoom()
                            .equals(room);

            boolean overlap =
                    reservation.getCheckInDate()
                            .before(checkOut)

                            &&

                            reservation.getCheckOutDate()
                                    .after(checkIn);

            if (sameRoom && overlap) {

                throw new IllegalArgumentException(
                        "Room already booked for selected dates."
                );
            }
        }

        Reservation reservation =
                new Reservation(
                        customer,
                        room,
                        checkIn,
                        checkOut
                );

        reservationList.add(reservation);

        return reservation;
    }

    // Get customer reservations
    public Collection<Reservation> getReservationsByCustomer(
            Customer customer
    ) {

        List<Reservation> customerReservations =
                new ArrayList<>();

        for (Reservation reservation : reservationList) {

            if (reservation.getCustomer()
                    .equals(customer)) {

                customerReservations.add(reservation);
            }
        }

        return customerReservations;
    }

    // Recommend rooms with +7 days
    public Collection<IRoom> suggestAlternativeRooms(
            Date checkIn,
            Date checkOut
    ) {

        Date shiftedCheckIn =
                addDays(checkIn, 7);

        Date shiftedCheckOut =
                addDays(checkOut, 7);

        return searchAvailableRooms(
                shiftedCheckIn,
                shiftedCheckOut
        );
    }

    // Add days utility
    private Date addDays(Date date, int days) {

        Calendar calendar =
                Calendar.getInstance();

        calendar.setTime(date);

        calendar.add(Calendar.DATE, days);

        return calendar.getTime();
    }

    // Print all reservations
    public void displayAllReservations() {

        if (reservationList.isEmpty()) {

            System.out.println(
                    "No reservations found."
            );

            return;
        }

        for (Reservation reservation : reservationList) {

            System.out.println("""
                    
                    ==========================
                    """);

            System.out.println(reservation);
        }
    }

    // Total reservations
    public int totalReservations() {

        return reservationList.size();
    }
}