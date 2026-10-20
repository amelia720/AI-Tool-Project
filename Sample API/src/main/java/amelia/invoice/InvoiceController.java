package amelia.invoice;

import amelia.MyConnection;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles REST API requests for invoices.
 */
@RestController
public class InvoiceController
{

    /**
     * Gets all invoices from the database.
     */
    @GetMapping("/invoices")
    public List<Invoice> getInvoices() throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        InvoiceDAO invoiceDAO = new InvoiceDAO(conn);

        return invoiceDAO.getAllInvoices();
    }


    /**
     * Gets one invoice using its invoice ID.
     */
    @GetMapping("/invoices/{invoiceId}")
    public ResponseEntity<Invoice> getInvoiceById(
            @PathVariable("invoiceId") int invoiceId) throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        InvoiceDAO invoiceDAO = new InvoiceDAO(conn);

        Invoice invoice = invoiceDAO.getInvoiceById(invoiceId);

        if (invoice == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(invoice);
    }


    /**
     * Creates a new invoice for a customer.
     */
    @PostMapping("/invoices/customer/{customerId}")
    @ApiResponse(responseCode = "201", description = "Invoice created")
    public ResponseEntity<Integer> createInvoice(
            @PathVariable("customerId") int customerId) throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        InvoiceDAO invoiceDAO = new InvoiceDAO(conn);

        int invoiceId = invoiceDAO.createInvoice(customerId);

        if (invoiceId == -1)
        {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.status(201).body(invoiceId);
    }


    /**
     * Adds a product to an invoice.
     */
    @PostMapping("/invoices/{invoiceId}/items")
    @ApiResponse(responseCode = "201", description = "Invoice item created")
    public ResponseEntity<Void> addInvoiceItem(
            @PathVariable("invoiceId") int invoiceId,
            @Valid @RequestBody InvoiceItemRequest request) throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        InvoiceDAO invoiceDAO = new InvoiceDAO(conn);

        invoiceDAO.addInvoiceItem(
            invoiceId,
            request.getProductId(),
            request.getQuantity()
        );

        return ResponseEntity.status(201).build();
    }


    /**
     * Updates the quantity of a product on an invoice.
     */
    @PutMapping("/invoices/{invoiceId}/items/{productId}")
    public ResponseEntity<Void> updateInvoiceItem(
            @PathVariable("invoiceId") int invoiceId,
            @PathVariable("productId") int productId,
            @Valid @RequestBody InvoiceQuantityRequest request) throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        InvoiceDAO invoiceDAO = new InvoiceDAO(conn);

        boolean updated = invoiceDAO.updateInvoiceItemQuantity(
            invoiceId,
            productId,
            request.getQuantity()
        );

        if (!updated)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }


    /**
     * Updates the date of an invoice.
     */
    @PutMapping("/invoices/{invoiceId}/date")
    public ResponseEntity<Void> updateInvoiceDate(
            @PathVariable("invoiceId") int invoiceId,
            @Valid @RequestBody InvoiceDateRequest request) throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        int rowsUpdated = InvoiceDAO.updateInvoiceDate(
            conn,
            invoiceId,
            request.getDate()
        );

        if (rowsUpdated == 0)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }


    /**
     * Deletes a product from an invoice.
     */
    @DeleteMapping("/invoices/{invoiceId}/items/{productId}")
    public ResponseEntity<Void> deleteInvoiceItem(
            @PathVariable("invoiceId") int invoiceId,
            @PathVariable("productId") int productId) throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        InvoiceDAO invoiceDAO = new InvoiceDAO(conn);

        boolean deleted = invoiceDAO.deleteInvoiceItem(
            invoiceId,
            productId
        );

        if (!deleted)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }


    /**
     * Deletes an invoice and its items.
     */
    @DeleteMapping("/invoices/{invoiceId}")
    public ResponseEntity<Void> deleteInvoice(
            @PathVariable("invoiceId") int invoiceId) throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        InvoiceDAO invoiceDAO = new InvoiceDAO(conn);

        boolean deleted = invoiceDAO.deleteInvoiceWithItems(invoiceId);

        if (!deleted)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}