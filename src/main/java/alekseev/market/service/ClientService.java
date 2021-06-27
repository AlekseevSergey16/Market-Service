package alekseev.market.service;

import alekseev.market.dto.ClientDTO;
import alekseev.market.dto.ProductWithoutCategoryDTO;

import java.util.List;

public interface ClientService {
    void saveClient(ClientDTO clientDTO);
    ClientDTO getClient(int id);
    List<ClientDTO> getAllClients();
    void updateClient(int id, ClientDTO clientDTO);
    void deleteClient(int id);
    List<ClientDTO> getByProductForInterval(ProductWithoutCategoryDTO product, String fromDate, String toDate);
}
