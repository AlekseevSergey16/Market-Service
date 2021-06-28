package alekseev.market.service;

import alekseev.market.dao.ReservationDAO;
import alekseev.market.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationDAO reservationDAO;

    @Autowired
    public ReservationServiceImpl(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    @Override
    public int createReservation(ReservationDTO reservationDTO) {
        try {
            reservationDAO.save(reservationDTO);
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public ReservationDTO getReservation(int id) {
        try {
            return reservationDAO.findById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationDAO.findAll();
    }

    @Override
    public int deleteReservation(int id) {
        try {
            reservationDAO.deleteById(id);
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public List<ReservationDTO> getReservationsByDate (String nameClient, String from, String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return reservationDAO.findByDate(nameClient, fromDate, toDate);
    }
}
