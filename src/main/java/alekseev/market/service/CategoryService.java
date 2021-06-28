package alekseev.market.service;

import alekseev.market.dto.CategoryDTO;
import alekseev.market.dto.CategoryWithProductDTO;

import java.util.List;

public interface CategoryService {
    int saveCategory(CategoryDTO category);
    CategoryWithProductDTO getCategory(int id);
    List<CategoryWithProductDTO> getAllCategories();
    int updateCategory(int id, CategoryDTO category);
    int deleteCategory(int id);
}
