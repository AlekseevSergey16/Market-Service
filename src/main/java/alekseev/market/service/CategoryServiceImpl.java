package alekseev.market.service;

import alekseev.market.dao.CategoryDAO;
import alekseev.market.dto.CategoryDTO;
import alekseev.market.dto.CategoryWithProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
    public int saveCategory(CategoryDTO category) {
        try {
            categoryDAO.save(category);
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }

    @Override
    public CategoryWithProductDTO getCategory(int id) {
        try {
            return categoryDAO.findById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public List<CategoryWithProductDTO> getAllCategories() {
        return categoryDAO.findAll();
    }

    @Override
    public int updateCategory(int id, CategoryDTO category) {
        try {
            categoryDAO.updateById(id, category);
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }

    @Override
    public int deleteCategory(int id) {
        try {
            categoryDAO.deleteById(id);
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }
}
