package alekseev.market.service;

import alekseev.market.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    int createReservation(ReservationDTO reservationDTO);
    ReservationDTO getReservation(int id);
    List<ReservationDTO> getAllReservations();
    int deleteReservation(int id);
    List<ReservationDTO> getReservationsByDate (String nameClient, String from, String to);
}
