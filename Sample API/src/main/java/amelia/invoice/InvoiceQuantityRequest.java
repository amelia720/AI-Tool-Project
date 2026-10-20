package amelia.invoice;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Represents the new quantity for an invoice item.
 */
public class InvoiceQuantityRequest
{
    @NotNull
    @Positive
    private Integer quantity;


    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }
}