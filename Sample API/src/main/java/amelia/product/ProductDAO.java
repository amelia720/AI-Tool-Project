package amelia.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


/**
 * ProductDAO handles all database operations related to the Product table.
 */
public class ProductDAO 
{

    /**
     * Inserts a new product into the database.
     * 
     * @param conn    Active DB connection
     * @param product Product object with all data to insert
     */
    public static void insertProduct(Connection conn, Product product) throws SQLException 
    {
        // SQL insert statement with placeholders for values
        PreparedStatement pstat = conn.prepareStatement(
            "INSERT INTO Product (name, category, price, stock) VALUES (?, ?, ?, ?)"
        );

        // Fill in values from the Product object
        pstat.setString(1, product.getName());
        pstat.setString(2, product.getCategory());
        pstat.setDouble(3, product.getPrice());
        pstat.setInt(4, product.getStock());

        // Run the insert query
        pstat.executeUpdate();
    }

    /**
     * Updates an existing product in the database.
     * 
     * @param conn    Active DB connection
     * @param product Product object with updated values
     * @return        Number of rows affected
     */
    public static int updateProduct(Connection conn, Product product) throws SQLException 
    {
        // SQL update query using WHERE clause to match productId
        PreparedStatement pstat = conn.prepareStatement(
            "UPDATE Product SET name=?, category=?, price=?, stock=? WHERE productId=?"
        );

        // Fill in the updated values
        pstat.setString(1, product.getName());
        pstat.setString(2, product.getCategory());
        pstat.setDouble(3, product.getPrice());
        pstat.setInt(4, product.getStock());
        pstat.setInt(5, product.getProductId());

        // Run the update query and return how many rows were changed
        return pstat.executeUpdate();
    }

    /**
     * Deletes a product by its ID.
     * 
     * @param conn      Active DB connection
     * @param productId ID of the product to delete
     * @return          Number of rows deleted
     */
    public static int deleteProduct(Connection conn, int productId) throws SQLException 
    {
        // SQL delete query
        PreparedStatement pstat = conn.prepareStatement(
            "DELETE FROM Product WHERE productId=?"
        );

        // Set the ID of the product to delete
        pstat.setInt(1, productId);

        // Run the delete query
        return pstat.executeUpdate();
    }

    /**
     * Fetches all products from the database.
     * 
     * @param conn Active DB connection
     * @return     ResultSet containing all products
     */
    public static ResultSet getAllProducts(Connection conn) throws SQLException 
    {
        // SQL select query
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT productId, name, category, price, stock FROM Product"
        );

        // Return the result set
        return stmt.executeQuery();
    }

    /**
     * Retrieves all products from the Product table
     * and stores them as Product objects.
     *
     * @param conn The database connection to use.
     * @return A list containing all products.
     * @throws SQLException If there's a problem with the SQL operation.
     */
    public static List<Product> getProductList(Connection conn) throws SQLException
    {
        List<Product> products = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement(
            "SELECT productId, name, category, price, stock FROM Product"
        );

        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            Product product = new Product(
                rs.getInt("productId"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getDouble("price"),
                rs.getInt("stock")
            );

            products.add(product);
        }

        return products;
    }

    /**
     * Retrieves one product from the Product table using its ID.
     *
     * @param conn The database connection to use.
     * @param productId The ID of the product to find.
     * @return The product if found, or null if not found.
     * @throws SQLException If there's a problem with the SQL operation.
     */
    public static Product getProductById(Connection conn, int productId) throws SQLException
    {
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT productId, name, category, price, stock " +
            "FROM Product WHERE productId = ?"
        );

        stmt.setInt(1, productId);

        ResultSet rs = stmt.executeQuery();

        if (rs.next())
        {
            return new Product(
                rs.getInt("productId"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getDouble("price"),
                rs.getInt("stock")
            );
        }

        return null;
    }
}
