package alekseev.market.dto;

import java.util.List;

public class ProductDTO extends ProductWithoutCategoryDTO {

    private List<CategoryDTO> categories;

    public ProductDTO() {
    }

    public ProductDTO(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}
