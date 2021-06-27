package alekseev.market.dao;

import alekseev.market.dto.CategoryDTO;
import alekseev.market.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO implements DAO<ProductDTO> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(ProductDTO product) {
        jdbcTemplate.update("INSERT INTO product (title, price) VALUES (?, ?)", product.getTitle(), product.getPrice());
    }

    public void saveProductWithCategory(int productId, List<Integer> categoryIds) {
        String sql = "INSERT INTO product_category (product_id, category_id) VALUES (?, ?)";
        for (int categoryId: categoryIds) {
            jdbcTemplate.update(sql, productId, categoryId);
        }
    }

    @Override
    public ProductDTO findById(int id) {
        String sql = "SELECT product_id, title, price FROM product WHERE product_id=?";
        ProductDTO product1 = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProductDTO.class), id)
                .stream().findAny().orElseThrow();

        product1.setCategories(getCategories(product1));

        return product1;
    }

    public int findIdByTitleAndPrice(ProductDTO product) {
        String sql = "SELECT product_id FROM product WHERE title=? AND price=?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("product_id"), product.getTitle(), product.getPrice())
                .stream().findAny().orElseThrow();
    }

    private List<CategoryDTO> getCategories(ProductDTO product) {
        String sql1 = "SELECT category.category_id,  name_category\n" +
                "FROM category\n" +
                "    INNER JOIN product_category pc on category.category_id = pc.category_id\n" +
                "    INNER JOIN product p on p.product_id = pc.product_id\n" +
                "WHERE title=?";
        return jdbcTemplate.query(sql1, new BeanPropertyRowMapper<>(CategoryDTO.class), product.getTitle());
    }

    @Override
    public List<ProductDTO> findAll() {
        String sql = "SELECT product_id, title, price FROM product";
        List<ProductDTO> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProductDTO.class));

        for (ProductDTO product:products) {
            product.setCategories(getCategories(product));
        }

        return products;
    }

    @Override
    public void updateById(int id, ProductDTO product) {
        String sql = "UPDATE product SET title=?, price=? WHERE product_id=?";
        jdbcTemplate.update(sql, product.getTitle(), product.getPrice(), product.getProductId());
        String sql2 = "SELECT category_id FROM category WHERE name_category=?";
        List<Integer> categoryIds = new ArrayList<>();
        for (CategoryDTO category: product.getCategories()) {
            categoryIds.add(jdbcTemplate.query(sql2, (rs, rowNum) -> rs.getInt("category_id"), category)
                    .stream().findAny().orElseThrow());
        }
        String sql22 = "DELETE FROM product_category WHERE product_id=?";
        jdbcTemplate.update(sql22, id);

        String sql12 = "INSERT INTO product_category (product_id, category_id) VALUES (?, ?)";
        for (int categoryId : categoryIds) {
            jdbcTemplate.update(sql12, id, categoryId);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM product WHERE product_id=?";
        String sql2 = "DELETE FROM product_category WHERE product_id=?";
        jdbcTemplate.update(sql2, id);
        jdbcTemplate.update(sql, id);
    }

}
