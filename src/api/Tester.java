package api;

public class Tester {

    public static void main(String[] args) {

        System.out.println("""
                ==================================
                   HOTEL RESERVATION SYSTEM
                ==================================
                System Initialized Successfully
                """);

        HotelResource hotelAPI = new HotelResource();
        AdminResource adminAPI = new AdminResource();

        System.out.println("Application is ready to use.");
    }
}