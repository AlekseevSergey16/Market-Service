package alekseev.market.service;

import alekseev.market.dto.ClientDTO;
import alekseev.market.dto.ProductWithoutCategoryDTO;

import java.util.List;

public interface ClientService {
    int saveClient(ClientDTO clientDTO);
    ClientDTO getClient(int id);
    List<ClientDTO> getAllClients();
    int updateClient(int id, ClientDTO clientDTO);
    int deleteClient(int id);
    List<ClientDTO> getByProductForInterval(ProductWithoutCategoryDTO product, String fromDate, String toDate);
}
