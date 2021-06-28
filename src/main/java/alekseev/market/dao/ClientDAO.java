package alekseev.market.dao;

import alekseev.market.dto.ClientDTO;
import alekseev.market.dto.ProductWithoutCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ClientDAO implements DAO<ClientDTO> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(ClientDTO client) throws SQLException {
        String sql = "INSERT INTO client(name_client) VALUES(?)";
        if (jdbcTemplate.update(sql, client.getNameClient()) != 1) {
            throw new SQLException();
        }
    }

    @Override
    public ClientDTO findById(int id) {
        String sql = "SELECT * FROM client WHERE client_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClientDTO.class), id)
                .stream().findAny().orElseThrow();
    }

    @Override
    public List<ClientDTO> findAll() {
        String sql = "SELECT * FROM client";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClientDTO.class));
    }

    public List<ClientDTO> findByProductForInterval(ProductWithoutCategoryDTO product, LocalDate from, LocalDate to) {
        String sql = "SELECT DISTINCT name_client, client.client_id\n" +
                "FROM client\n" +
                "         INNER JOIN reservation ON client.client_id = reservation.client_id\n" +
                "         INNER JOIN reservation_product ON reservation.reservation_id = reservation_product.reservation_id\n" +
                "         INNER JOIN product ON reservation_product.product_id = product.product_id\n" +
                "WHERE product.product_id = (SELECT product_id FROM product WHERE title = ?)\n" +
                "AND reservation_date BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClientDTO.class), product.getTitle(), from, to);
    }

    @Override
    public void updateById(int id, ClientDTO client) throws SQLException {
        String sql = "UPDATE client SET name_client=? WHERE client_id=?";
        if (jdbcTemplate.update(sql, client.getNameClient(), id) != 1) {
            throw new SQLException();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM client WHERE client_id=?";
        if (jdbcTemplate.update(sql, id) != 1) {
            throw new SQLException();
        }
    }
}
