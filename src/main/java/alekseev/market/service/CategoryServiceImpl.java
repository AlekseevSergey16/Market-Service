package alekseev.market.service;

import alekseev.market.dao.CategoryDAO;
import alekseev.market.dto.CategoryDTO;
import alekseev.market.dto.CategoryWithProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public void saveCategory(CategoryDTO category) {
        categoryDAO.save(category);
    }

    @Override
    public CategoryWithProductDTO getCategory(int id) {
        return categoryDAO.findById(id);
    }

    @Override
    public List<CategoryWithProductDTO> getAllCategories() {
        return categoryDAO.findAll();
    }

    @Override
    public void updateCategory(int id, CategoryDTO category) {
        categoryDAO.updateById(id, category);
    }

    @Override
    public void deleteCategory(int id) {
        categoryDAO.deleteById(id);
    }
}
