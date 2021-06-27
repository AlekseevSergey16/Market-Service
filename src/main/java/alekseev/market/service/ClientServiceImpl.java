package alekseev.market.service;

import alekseev.market.dao.ClientDAO;
import alekseev.market.dto.ClientDTO;
import alekseev.market.dto.ProductWithoutCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDAO clientDAO;

    @Autowired
    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public void saveClient(ClientDTO clientDTO) {
        clientDAO.save(clientDTO);
    }

    @Override
    public ClientDTO getClient(int id) {
        return clientDAO.findById(id);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientDAO.findAll();
    }

    @Override
    public void updateClient(int id, ClientDTO clientDTO) {
        clientDAO.updateById(id, clientDTO);
    }

    @Override
    public void deleteClient(int id) {
        clientDAO.deleteById(id);
    }

    @Override
    public List<ClientDTO> getByProductForInterval(ProductWithoutCategoryDTO product, String from, String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return clientDAO.findByProductForInterval(product, fromDate, toDate);
    }
}
