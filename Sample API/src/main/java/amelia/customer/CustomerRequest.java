package amelia.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Represents the customer details required by the REST API.
 */
public class CustomerRequest
{
    @NotBlank
    private String fname;

    @NotBlank
    private String sname;

    @NotBlank
    private String address;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "\\d{10}")
    private String phone;


    // Getters

    public String getFname()
    {
        return fname;
    }

    public String getSname()
    {
        return sname;
    }

    public String getAddress()
    {
        return address;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhone()
    {
        return phone;
    }


    // Setters

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public void setSname(String sname)
    {
        this.sname = sname;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }
}