package alekseev.market.dao;

import alekseev.market.dto.CategoryDTO;
import alekseev.market.dto.CategoryWithProductDTO;
import alekseev.market.dto.ProductWithoutCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryDAO implements DAO<CategoryDTO> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(CategoryDTO category) throws SQLException {
        String sql = "INSERT INTO category (name_category) VALUES (?)";
        if(jdbcTemplate.update(sql, category.getNameCategory()) != 1) {
            throw new SQLException();
        }
    }

    @Override
    public CategoryWithProductDTO findById(int id) {
        String sql = "SELECT category_id, name_category FROM category WHERE category_id=?";
        CategoryWithProductDTO category = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CategoryWithProductDTO.class), id)
                .stream().findAny().orElseThrow();
        category.setProducts(getProducts(category));
        return category;
    }

    private List<ProductWithoutCategoryDTO> getProducts(CategoryDTO category) {
        String sql = "SELECT p.product_id, title, price\n" +
                "FROM category\n" +
                "    INNER JOIN product_category pc on category.category_id = pc.category_id\n" +
                "    INNER JOIN product p on pc.product_id = p.product_id\n" +
                "WHERE name_category=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProductWithoutCategoryDTO.class), category.getNameCategory());
    }

    @Override
    public List<CategoryWithProductDTO> findAll() {
        String sql = "SELECT category_id, name_category FROM category";
        List<CategoryWithProductDTO> categories = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CategoryWithProductDTO.class));
        for (CategoryWithProductDTO category : categories) {
            category.setProducts(getProducts(category));
        }
        return categories;
    }

    public int findIdByNameCategory(String nameCategory) {
        String sql = "SELECT category_id FROM category WHERE name_category=?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("category_id"), nameCategory)
                .stream().findAny().orElseThrow();
    }

    @Override
    public void updateById(int id, CategoryDTO category) throws SQLException {
        String sql = "UPDATE category SET name_category=? WHERE category_id=?";
        if (jdbcTemplate.update(sql, category.getNameCategory(), id) != 1) {
            throw new SQLException();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql1 = "DELETE FROM product_category WHERE category_id=?";
        String sq2 = "DELETE FROM category WHERE category_id=?";
        jdbcTemplate.update(sql1, id);
        if (jdbcTemplate.update(sq2, id) != 1) {
            throw new SQLException();
        }
    }
}
