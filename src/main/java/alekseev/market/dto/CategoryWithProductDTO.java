package alekseev.market.dto;

import java.util.List;

public class CategoryWithProductDTO extends CategoryDTO {

    private List<ProductWithoutCategoryDTO> products;

    public CategoryWithProductDTO() {
        super();
    }

    public CategoryWithProductDTO(int categoryId, String nameCategory, List<ProductWithoutCategoryDTO> products) {
        super(categoryId, nameCategory);
        this.products = products;
    }

    public List<ProductWithoutCategoryDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWithoutCategoryDTO> products) {
        this.products = products;
    }
}
