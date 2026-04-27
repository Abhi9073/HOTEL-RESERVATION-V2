# Hotel Reservation Application

A console-based Hotel Reservation Application developed using Java and Object-Oriented Programming principles.

This application allows customers to:
- Create accounts
- Search for available rooms
- Reserve rooms
- View reservations

It also provides an Admin Panel to:
- Add rooms
- View all rooms
- View all customers
- View all reservations

---

# Features

## Customer Features
- Create customer account
- Email validation using Regular Expressions
- Search available rooms
- Reserve rooms
- View reservations
- Automatic room recommendation (+7 days) if rooms are unavailable

## Admin Features
- Add paid rooms
- Add free rooms
- View all rooms
- View all customers
- View all reservations

## Reservation Features
- Prevent overlapping reservations
- Prevent double booking
- Validate future booking dates
- Recommend alternative dates when rooms are unavailable

---

# Technologies Used

- Java
- Object-Oriented Programming
- Collections Framework
- Exception Handling
- Regular Expressions
- Date & Calendar API

---

# Object-Oriented Concepts Used

- Interfaces
- Inheritance
- Polymorphism
- Encapsulation
- Singleton Pattern
- Method Overriding

---

# Project Structure

```text
src/
│
├── api/
│   ├── HotelResource.java
│   └── AdminResource.java
│
├── model/
│   ├── Customer.java
│   ├── IRoom.java
│   ├── Room.java
│   ├── FreeRoom.java
│   ├── Reservation.java
│   └── RoomType.java
│
├── service/
│   ├── CustomerService.java
│   └── ReservationService.java
│
├── MainMenu.java
├── AdminMenu.java
└── HotelApplication.java
```

---

# How to Run the Application

## Using IntelliJ IDEA

1. Open the project in IntelliJ IDEA
2. Configure JDK 17 or above
3. Navigate to:

```text
HotelApplication.java
```

4. Run the `main()` method

---

## Using Terminal

### Compile

```bash
javac */*.java *.java
```

### Run

```bash
java HotelApplication
```

---

# Example Workflow

## Create Customer

```text
Main Menu -> Create Account
```

## Add Rooms

```text
Main Menu -> Admin Panel -> Add Rooms
```

## Reserve Room

```text
Main Menu -> Find & Reserve Room
```

---

# Validations Implemented

- Email validation using Regex
- Room number validation
- Room price validation
- Future date validation
- Check-out date must be after check-in date
- Prevention of overlapping reservations
- Prevention of duplicate room bookings

---

# Free Room Support

The application supports:
- Paid rooms
- Free rooms

Free rooms are displayed separately and can also be reserved.

---

# Error Handling

The application uses:
- try/catch blocks
- Input validation
- Exception handling

to prevent crashes and improve user experience.

---

# Author

Abhishek Verma

---

# License

This project is created for educational purposes.
