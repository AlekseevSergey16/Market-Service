package alekseev.market.dao;

import alekseev.market.dto.CategoryDTO;
import alekseev.market.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDAO implements DAO<ProductDTO> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(ProductDTO product) throws SQLException {
        if (jdbcTemplate.update("INSERT INTO product (title, price) VALUES (?, ?)", product.getTitle(), product.getPrice()) != 1) {
            throw new SQLException();
        }
    }

    public void saveProductWithCategory(int productId, List<Integer> categoryIds) throws SQLException {
        String sql = "INSERT INTO product_category (product_id, category_id) VALUES (?, ?)";
        for (int categoryId: categoryIds) {
            if (jdbcTemplate.update(sql, productId, categoryId) != 1) {
                throw new SQLException();
            }
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
    public void updateById(int id, ProductDTO product) throws SQLException {
        String sql = "UPDATE product SET title=?, price=? WHERE product_id=?";
        if (jdbcTemplate.update(sql, product.getTitle(), product.getPrice(), id) != 1) {
            throw new SQLException();
        }
        String sql2 = "DELETE FROM product_category WHERE product_id=?";
        jdbcTemplate.update(sql2, id);

        String sql3 = "INSERT INTO product_category (product_id, category_id) VALUES (?, (SELECT category_id FROM category WHERE name_category=?))";
        for (CategoryDTO category : product.getCategories()) {
            if (jdbcTemplate.update(sql3, id, category.getNameCategory()) != 1) {
                throw new SQLException();
            }
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM product_category WHERE product_id=?";
        String sql2 = "DELETE FROM product WHERE product_id=?";
        jdbcTemplate.update(sql, id);
        if (jdbcTemplate.update(sql2, id) != 1) {
            throw new SQLException();
        }
    }
}
