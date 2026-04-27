package service;

import model.Customer;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomerService {

    private static CustomerService serviceInstance;

    private final Map<String, Customer> customerRegistry;

    private CustomerService() {
        customerRegistry = new LinkedHashMap<>();
    }

    public static CustomerService getInstance() {

        if (serviceInstance == null) {
            serviceInstance = new CustomerService();
        }

        return serviceInstance;
    }

    public void registerCustomer(String firstName,
                                 String lastName,
                                 String email) {

        if (customerRegistry.containsKey(email.toLowerCase())) {
            throw new IllegalArgumentException(
                    "Customer already exists with this email."
            );
        }

        Customer customer =
                new Customer(firstName, lastName, email);

        customerRegistry.put(email.toLowerCase(), customer);

        System.out.println("Customer added successfully.");
    }

    public Customer fetchCustomer(String email) {
        return customerRegistry.get(email.toLowerCase());
    }

    public Collection<Customer> fetchAllCustomers() {
        return customerRegistry.values();
    }

    public boolean customerExists(String email) {
        return customerRegistry.containsKey(email.toLowerCase());
    }

    public int totalCustomers() {
        return customerRegistry.size();
    }
}