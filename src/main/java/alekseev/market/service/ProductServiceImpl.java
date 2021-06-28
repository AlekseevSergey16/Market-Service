package alekseev.market.service;

import alekseev.market.dao.CategoryDAO;
import alekseev.market.dao.ProductDAO;
import alekseev.market.dto.CategoryDTO;
import alekseev.market.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;

    public ProductServiceImpl(ProductDAO productDAO, CategoryDAO categoryDAO) {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public int saveProduct(ProductDTO productDTO) {
        try {
            productDAO.save(productDTO);
            int productId = productDAO.findIdByTitleAndPrice(productDTO);
            List<Integer> categoryIds = new ArrayList<>();
            for (CategoryDTO category: productDTO.getCategories()) {
                categoryIds.add(categoryDAO.findIdByNameCategory(category.getNameCategory()));
            }
            productDAO.saveProductWithCategory(productId, categoryIds);
            return 1;
        } catch (SQLException | NoSuchElementException e) {
            return 0;
        }
    }

    @Override
    public ProductDTO getProduct(int id) {
        try {
            return productDAO.findById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productDAO.findAll();
    }

    @Override
    public int update(int id, ProductDTO productDTO) {
        try {
            productDAO.updateById(id, productDTO);
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public int delete(int id) {
        try {
            productDAO.deleteById(id);
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

}
