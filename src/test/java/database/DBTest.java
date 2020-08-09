package database;

import dao.CustomerDAO;
import factory.Factory;
import model.Customer;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class DBTest {

    DB db = new DB();
    Customer customer = new Customer();
    Customer customer1 = new Customer();
    private Factory instance = Factory.getInstance();


    @Test
    public void testCreateDB() {
        try {
            db.createDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCRUDoptions(){
        CustomerDAO customerDAO = instance.getCustomerDAO();

        customer.setTelegramId("PuPetya");
        customer.setFirstName("Petya");
        customer.setLastName("Pupkin");
        customer.setUsername("PuPetya");
        customer.setPhone("+38066 222 22 22");
        customerDAO.insertCustomer(customer);

        System.out.println(customerDAO.findCustomerByPhone("+38066 222 22 22"));

        customer1.setTelegramId("PePutya");
        customer1.setFirstName("Pupka");
        customer1.setLastName("Petechkin");
        customer1.setUsername("PePutya");
        customer1.setPhone("+38066 333 33 33");
        customerDAO.updateCustomer(customer1);
        customerDAO.deleteAll();


    }
}
