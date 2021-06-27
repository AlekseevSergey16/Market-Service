package alekseev.market.dto;

public class ClientDTO {

    private int clientId;
    private String nameClient;

    public ClientDTO() {
    }

    public ClientDTO(String nameClient) {
        this.nameClient = nameClient;
    }

    public ClientDTO(int clientId, String nameClient) {
        this.clientId = clientId;
        this.nameClient = nameClient;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
}
