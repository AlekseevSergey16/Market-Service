package alekseev.market.controller;

import alekseev.market.dto.ClientDTO;
import alekseev.market.dto.ProductWithoutCategoryDTO;
import alekseev.market.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientDTO>> getClient() {
        List<ClientDTO> categories = clientService.getAllClients();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable int id) {
        ClientDTO clientDTO = clientService.getClient(id);

        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ClientDTO>> getClientByProductForInterval(@RequestBody ProductWithoutCategoryDTO product,
                                                                         @RequestParam(value = "from", required = false) String from,
                                                                         @RequestParam(value = "to", required = false) String to) {
        List<ClientDTO> clients = clientService.getByProductForInterval(product, from, to);

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        clientService.saveClient(clientDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO, @PathVariable int id) {
        clientService.updateClient(id, clientDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable int id)  {
        clientService.deleteClient(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
