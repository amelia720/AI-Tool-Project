package amelia.customer;

import amelia.MyConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;

/**
 * Handles REST API requests for customers.
 */
@RestController
public class CustomerController
{
    /**
     * Gets all customers from the database.
     *
     * @return a list of all customers
     * @throws SQLException if there is a problem with the database
     */
    @GetMapping("/customers")
    public List<Customer> getCustomers() throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        return CustomerDAO.getCustomerList(conn);
    }
    /**
     * Gets one customer using their customer ID.
     *
     * @param customerId the ID of the customer
     * @return the customer if found
     * @throws SQLException if there is a problem with the database
     */
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable("customerId") int customerId) throws SQLException
    {
        Connection conn = MyConnection.getConnection();
    
        Customer customer = CustomerDAO.getCustomerById(conn, customerId);
    
        if (customer == null)
        {
            return ResponseEntity.notFound().build();
        }
    
        return ResponseEntity.ok(customer);
    }

    /**
     * Adds a new customer to the database.
     *
     * @param customer the customer details sent in the request
     * @return a 201 Created response
     * @throws SQLException if there is a problem with the database
     */
    @PostMapping("/customers")
    public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        CustomerDAO.insertCustomer(conn, customer);

        return ResponseEntity.status(201).build();
    }
    /**
     * Updates an existing customer.
     *
     * @param customerId the ID of the customer to update
     * @param customer the new customer details
     * @return 200 OK if updated, or 404 Not Found if the customer does not exist
     * @throws SQLException if there is a problem with the database
     */
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Void> updateCustomer(
            @PathVariable("customerId") int customerId,
            @RequestBody Customer customer) throws SQLException
    {
        customer.setCustomerId(customerId);

        Connection conn = MyConnection.getConnection();

        int rowsUpdated = CustomerDAO.updateCustomer(conn, customer);

        if (rowsUpdated == 0)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
    /**
     * Deletes a customer using their customer ID.
     *
     * @param customerId the ID of the customer to delete
     * @return 200 OK if deleted, or 404 Not Found if the customer does not exist
     * @throws SQLException if there is a problem with the database
     */
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable("customerId") int customerId) throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        int rowsDeleted = CustomerDAO.deleteCustomer(conn, customerId);

        if (rowsDeleted == 0)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}