package alekseev.market.service;

import alekseev.market.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    int saveProduct(ProductDTO product);
    ProductDTO getProduct(int id);
    List<ProductDTO> getAllProducts();
    int update(int id, ProductDTO productDTO);
    int delete(int id);
}
