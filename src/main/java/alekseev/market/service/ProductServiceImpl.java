package alekseev.market.service;

import alekseev.market.dao.CategoryDAO;
import alekseev.market.dao.ProductDAO;
import alekseev.market.dto.CategoryDTO;
import alekseev.market.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;

    public ProductServiceImpl(ProductDAO productDAO, CategoryDAO categoryDAO) {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public void saveProduct(ProductDTO productDTO) {
        productDAO.save(productDTO);
        int productId = productDAO.findIdByTitleAndPrice(productDTO);
        List<Integer> categoryIds = new ArrayList<>();
        for (CategoryDTO category: productDTO.getCategories()) {
            categoryIds.add(categoryDAO.findIdByNameCategory(category.getNameCategory()));
        }
        productDAO.saveProductWithCategory(productId, categoryIds);
    }

    @Override
    public ProductDTO getProduct(int id) {
        return productDAO.findById(id);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productDAO.findAll();
    }

    @Override
    public void update(int id, ProductDTO productDTO) {
        productDAO.updateById(id, productDTO);
    }

    @Override
    public void delete(int id) {
        productDAO.deleteById(id);
    }


}
