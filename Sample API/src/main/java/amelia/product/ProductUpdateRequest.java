package amelia.product;

public class ProductUpdateRequest
{
    private String name;
    private String category;
    private Double price;
    private Integer stock;

    public String getName() { return name; }
    public String getCategory() { return category; }
    public Double getPrice() { return price; }
    public Integer getStock() { return stock; }

    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(Double price) { this.price = price; }
    public void setStock(Integer stock) { this.stock = stock; }
}