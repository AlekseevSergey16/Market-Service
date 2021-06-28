package alekseev.market.controller;

import alekseev.market.dto.ClientDTO;
import alekseev.market.dto.ReservationDTO;
import alekseev.market.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("")
    public ResponseEntity<List<ReservationDTO>> getReservation() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        if (reservations.isEmpty()) {
            return new ResponseEntity<>(reservations, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable int id) {
        ReservationDTO reservation = reservationService.getReservation(id);
        if (reservation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservation) {

        int status = reservationService.createReservation(reservation);

        if (status != 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationDTO> deleteReservation(@PathVariable int id)  {
        int status = reservationService.deleteReservation(id);

        if (status != 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/client")
    public ResponseEntity<List<ReservationDTO>> getClientReservation(@RequestBody ClientDTO client,
                                                                     @RequestParam(value = "from", required = false) String from,
                                                                     @RequestParam(value = "to", required = false) String to) {
        List<ReservationDTO> reservations = reservationService.getReservationsByDate(client.getNameClient(), from, to);

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
