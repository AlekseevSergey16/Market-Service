package alekseev.market.service;

import alekseev.market.dao.ReservationDAO;
import alekseev.market.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationDAO reservationDAO;

    @Autowired
    public ReservationServiceImpl(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    @Override
    public void createReservation(ReservationDTO reservationDTO) {
        reservationDAO.save(reservationDTO);
    }

    @Override
    public ReservationDTO getReservation(int id) {
        return reservationDAO.findById(id);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationDAO.findAll();
    }

    @Override
    public void deleteReservation(int id) {
        reservationDAO.deleteById(id);
    }

    @Override
    public List<ReservationDTO> getReservationsByDate (String nameClient, String from, String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return reservationDAO.findByDate(nameClient, fromDate, toDate);
    }
}
