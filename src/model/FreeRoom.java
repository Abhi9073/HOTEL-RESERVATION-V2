package model;

public class FreeRoom extends Room {

    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0.0, roomType);
    }

    @Override
    public boolean isFreeRoom() {
        return true;
    }

    @Override
    public String toString() {
        return """
                Free Room:
                Room No   : %s
                Room Type : %s
                Price     : Free
                """.formatted(getRoomNumber(), getRoomType());
    }
}