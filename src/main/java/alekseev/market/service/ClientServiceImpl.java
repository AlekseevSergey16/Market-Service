package alekseev.market.service;

import alekseev.market.dao.ClientDAO;
import alekseev.market.dto.ClientDTO;
import alekseev.market.dto.ProductWithoutCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDAO clientDAO;

    @Autowired
    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public int saveClient(ClientDTO clientDTO) {
        try {
            clientDAO.save(clientDTO);
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }

    @Override
    public ClientDTO getClient(int id) {
        try {
            return clientDAO.findById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientDAO.findAll();
    }

    @Override
    public int updateClient(int id, ClientDTO clientDTO) {
        try {
            clientDAO.updateById(id, clientDTO);
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }

    @Override
    public int deleteClient(int id) {
        try {
            clientDAO.deleteById(id);
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }

    @Override
    public List<ClientDTO> getByProductForInterval(ProductWithoutCategoryDTO product, String from, String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return clientDAO.findByProductForInterval(product, fromDate, toDate);
    }
}
