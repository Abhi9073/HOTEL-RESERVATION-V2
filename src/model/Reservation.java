package model;

import java.util.Date;

public class Reservation {

    private final Customer customer;
    private final IRoom room;
    private final Date checkIn;
    private final Date checkOut;

    public Reservation(Customer customer,
                       IRoom room,
                       Date checkIn,
                       Date checkOut) {

        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkIn;
    }

    public Date getCheckOutDate() {
        return checkOut;
    }

    public long getReservationDuration() {
        long diff = checkOut.getTime() - checkIn.getTime();
        return diff / (1000 * 60 * 60 * 24);
    }

    @Override
    public String toString() {
        return """
                Reservation Summary:
                Guest      : %s
                Room       : %s
                Check-In   : %s
                Check-Out  : %s
                Duration   : %d days
                """.formatted(
                customer.getFullName(),
                room.getRoomNumber(),
                checkIn,
                checkOut,
                getReservationDuration()
        );
    }
}