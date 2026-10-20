package amelia.invoice;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Represents the details required to add an item to an invoice.
 */
public class InvoiceItemRequest
{
    @NotNull
    @Positive
    private Integer productId;

    @NotNull
    @Positive
    private Integer quantity;


    // Getters

    public Integer getProductId()
    {
        return productId;
    }

    public Integer getQuantity()
    {
        return quantity;
    }


    // Setters

    public void setProductId(Integer productId)
    {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }
}