package amelia.invoice;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents the new date for an invoice.
 */
public class InvoiceDateRequest
{
    @NotBlank
    private String date;


    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
}