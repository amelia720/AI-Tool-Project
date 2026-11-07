
package amelia.customer;

public class CustomerUpdateRequest
{
    private String fname;
    private String sname;
    private String address;
    private String email;
    private String phone;

    public String getFname() { return fname; }
    public String getSname() { return sname; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setFname(String fname) { this.fname = fname; }
    public void setSname(String sname) { this.sname = sname; }
    public void setAddress(String address) { this.address = address; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
}
