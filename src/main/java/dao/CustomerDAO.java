package dao;

import model.Customer;

import java.util.List;

public interface CustomerDAO {
    void insertCustomer(Customer customer);
    List<Customer>getAllCustomers();
    Customer findCustomerByPhone(String phone);
    void updateCustomer(Customer customer);
    void deleteAll();
}
