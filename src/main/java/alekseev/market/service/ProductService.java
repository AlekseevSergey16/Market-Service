package alekseev.market.service;

import alekseev.market.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductDTO product);
    ProductDTO getProduct(int id);
    List<ProductDTO> getAllProducts();
    void update(int id, ProductDTO productDTO);
    void delete(int id);
}
