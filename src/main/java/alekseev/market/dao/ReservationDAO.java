package alekseev.market.dao;

import alekseev.market.dto.CategoryDTO;
import alekseev.market.dto.ClientDTO;
import alekseev.market.dto.ProductDTO;
import alekseev.market.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationDAO implements DAO<ReservationDTO> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReservationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(ReservationDTO reservation) throws SQLException {
        String sql = "INSERT INTO reservation(client_id, reservation_date) VALUES ((SELECT client_id FROM client WHERE name_client=?), ?)";
        if (jdbcTemplate.update(sql, reservation.getClient().getNameClient(), reservation.getReservationDate()) != 1) {
            throw new SQLException();
        }

        String sql2 = "SELECT reservation_id FROM reservation WHERE reservation.client_id = (SELECT client_id FROM client WHERE name_client=?) " +
                "AND reservation_date=?";
        int reservationId = jdbcTemplate.query(sql2, (rs, rowNum) -> rs.getInt("reservation_id"),
                reservation.getClient().getNameClient(), reservation.getReservationDate()).stream().findAny().orElseThrow();
        String sql3 = "INSERT INTO reservation_product(reservation_id, product_id)\n" +
                "VALUES (?, (SELECT product_id FROM product WHERE title=? AND price=?))";
        for (ProductDTO product : reservation.getProducts()) {
            if (jdbcTemplate.update(sql3, reservationId, product.getTitle(), product.getPrice()) != 1) {
                throw new SQLException();
            }
        }
    }

    @Override
    public ReservationDTO findById(int id) {
        String sql1 = "SELECT reservation.reservation_id, reservation_date\n" +
                "FROM client\n" +
                "         INNER JOIN reservation ON client.client_id = reservation.client_id\n" +
                "WHERE reservation.reservation_id=?";

        ReservationDTO reservation = jdbcTemplate.query(sql1, new BeanPropertyRowMapper<>(ReservationDTO.class), id).stream().findAny().orElseThrow();
        String sql2 = "SELECT client.client_id, name_client\n" +
                "FROM client\n" +
                "         INNER JOIN reservation ON client.client_id = reservation.client_id\n" +
                "WHERE reservation.reservation_id=?";
        ClientDTO client = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(ClientDTO.class), id).stream().findAny().orElseThrow();
        String sql3 = "SELECT product.product_id, title, price\n" +
                "FROM client\n" +
                "         INNER JOIN reservation ON client.client_id = reservation.client_id\n" +
                "         INNER JOIN reservation_product ON reservation.reservation_id = reservation_product.reservation_id\n" +
                "         INNER JOIN product ON reservation_product.product_id = product.product_id\n" +
                "WHERE reservation.reservation_id=?";
        List<ProductDTO> products = jdbcTemplate.query(sql3, new BeanPropertyRowMapper<>(ProductDTO.class), id);

        String sql4 = "SELECT category.category_id, name_category\n" +
                "FROM product\n" +
                "         INNER JOIN product_category on product.product_id = product_category.product_id\n" +
                "         INNER JOIN category on category.category_id = product_category.category_id\n" +
                "WHERE product.product_id=?";

        for (ProductDTO product : products) {
            List<CategoryDTO> categories = jdbcTemplate.query(sql4, new BeanPropertyRowMapper<>(CategoryDTO.class),product.getProductId());
            product.setCategories(categories);
        }

        reservation.setClient(client);
        reservation.setProducts(products);

        return reservation;
    }

    @Override
    public List<ReservationDTO> findAll() {
        String sql = "SELECT reservation.reservation_id\n" +
                "FROM client\n" +
                "         INNER JOIN reservation ON client.client_id = reservation.client_id";
        List<Integer> reservationIds = jdbcTemplate.query(sql, (rs, rowNum) -> {
            return rs.getInt("reservation_id");
        });
        List<ReservationDTO> reservations = new ArrayList<>();
        for (int reservationId : reservationIds) {
            reservations.add(findById(reservationId));
        }
        return reservations;
    }

    public List<ReservationDTO> findByDate (String nameClient, LocalDate from, LocalDate to) {
        String sql = "SELECT reservation.reservation_id\n" +
                "FROM client\n" +
                "         INNER JOIN reservation ON client.client_id = reservation.client_id\n" +
                "WHERE client.client_id = (SELECT client_id FROM client WHERE name_client = ?)\n" +
                "    AND reservation_date BETWEEN ? AND ?";
        List<Integer> reservationIds = jdbcTemplate.query(sql, (rs, rowNum) -> {
            return rs.getInt("reservation_id");
            }, nameClient, from, to);

        List<ReservationDTO> reservations = new ArrayList<>();
        for (int reservationId : reservationIds) {
            reservations.add(findById(reservationId));
        }
        return reservations;
    }

    @Override
    public void updateById(int id, ReservationDTO reservationDTO) {
        // to do
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM reservation_product WHERE reservation_id=?";
        String sql2 = "DELETE FROM reservation WHERE reservation_id=?";
        jdbcTemplate.update(sql, id);
        if (jdbcTemplate.update(sql2, id) != 1) {
            throw new SQLException();
        }
    }
}
