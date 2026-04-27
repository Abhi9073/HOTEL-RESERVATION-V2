package model;

import java.util.Objects;

public class Room implements IRoom {

    private final String roomNumber;
    private final double price;
    private final RoomType roomType;

    public Room(String roomNumber, double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFreeRoom() {
        return price == 0;
    }

    @Override
    public String toString() {
        return """
                Room Information:
                Room No   : %s
                Price     : %.2f
                Room Type : %s
                """.formatted(roomNumber, price, roomType);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Room other)) return false;

        return Objects.equals(roomNumber, other.roomNumber)
                && Double.compare(price, other.price) == 0
                && roomType == other.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price, roomType);
    }
}
