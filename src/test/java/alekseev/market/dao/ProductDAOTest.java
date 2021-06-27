package alekseev.market.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductDAOTest {

    @Autowired
    private ProductDAO productDAO;

    @Test
    void findByIdTest() {
        assertTrue(productDAO.findById(1).getTitle().equals("Мужская футболка Nike"));
    }

    @Test
    void findAllByIdTest() {
        productDAO.findAll().forEach(System.out::println);

    }
}