package alekseev.market.dto;

import java.util.List;

public class ProductWithoutCategoryDTO {

    private int productId;
    private String title;
    private double price;

    public ProductWithoutCategoryDTO() {
    }

    public ProductWithoutCategoryDTO(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public ProductWithoutCategoryDTO(int id, String title, double price) {
        this.productId = id;
        this.title = title;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
