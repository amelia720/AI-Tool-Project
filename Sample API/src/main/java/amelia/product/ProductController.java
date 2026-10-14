package amelia.product;

import amelia.MyConnection;

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
 * Handles REST API requests for products.
 */
@RestController
public class ProductController
{
    /**
     * Gets all products from the database.
     */
    @GetMapping("/products")
    public List<Product> getProducts() throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        return ProductDAO.getProductList(conn);
    }


    /**
     * Gets one product using its product ID.
     */
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(
            @PathVariable("productId") int productId) throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        Product product = ProductDAO.getProductById(conn, productId);

        if (product == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }


    /**
     * Adds a new product to the database.
     */
    @PostMapping("/products")
    public ResponseEntity<Void> addProduct(
            @RequestBody Product product) throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        ProductDAO.insertProduct(conn, product);

        return ResponseEntity.status(201).build();
    }


    /**
     * Updates an existing product.
     */
    @PutMapping("/products/{productId}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable("productId") int productId,
            @RequestBody Product product) throws SQLException
    {
        product.setProductId(productId);

        Connection conn = MyConnection.getConnection();

        int rowsUpdated = ProductDAO.updateProduct(conn, product);

        if (rowsUpdated == 0)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }


    /**
     * Deletes a product using its product ID.
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable("productId") int productId) throws SQLException
    {
        Connection conn = MyConnection.getConnection();

        int rowsDeleted = ProductDAO.deleteProduct(conn, productId);

        if (rowsDeleted == 0)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}