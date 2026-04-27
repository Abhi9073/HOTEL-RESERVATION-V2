package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {

    private final String firstName;
    private final String lastName;
    private final String email;

    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public Customer(String firstName, String lastName, String email) {

        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }

        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.toLowerCase();
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return """
                Customer Details:
                Name  : %s
                Email : %s
                """.formatted(getFullName(), email);
    }
}