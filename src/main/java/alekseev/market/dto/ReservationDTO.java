package alekseev.market.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationDTO {

    private int reservationId;
    private ClientDTO client;
    private LocalDateTime reservationDate;
    private List<ProductDTO> products;

    public ReservationDTO() {
        this.reservationDate = LocalDateTime.now();
    }

    public ReservationDTO(ClientDTO client, List<ProductDTO> products) {
        this.client = client;
        this.products = products;
        this.reservationDate = LocalDateTime.now();
    }

    public ReservationDTO(int reservation_id, ClientDTO client, List<ProductDTO> products) {
        this.reservationId = reservation_id;
        this.client = client;
        this.products = products;
        this.reservationDate = LocalDateTime.now();
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservation_id) {
        this.reservationId = reservation_id;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }
}
