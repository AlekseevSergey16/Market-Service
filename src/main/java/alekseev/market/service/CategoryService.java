package alekseev.market.service;

import alekseev.market.dto.CategoryDTO;
import alekseev.market.dto.CategoryWithProductDTO;

import java.util.List;

public interface CategoryService {
    void saveCategory(CategoryDTO category);
    CategoryWithProductDTO getCategory(int id);
    List<CategoryWithProductDTO> getAllCategories();
    void updateCategory(int id, CategoryDTO category);
    void deleteCategory(int id);
}
