package model;

public class Tester {

    public static void main(String[] args) {

        try {

            Customer customer1 =
                    new Customer("Abhishek", "Verma", "abhishek@gmail.com");

            System.out.println(customer1);

            Room room1 =
                    new Room("101", 2500, RoomType.SINGLE);

            System.out.println(room1);

            FreeRoom freeRoom =
                    new FreeRoom("F1", RoomType.DOUBLE);

            System.out.println(freeRoom);

        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}