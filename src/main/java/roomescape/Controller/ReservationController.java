package roomescape.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.domain.Reservation;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReservationController {

    List<Reservation> reservations = new ArrayList<>();

    @GetMapping("/reservation")
    String getReservationPage() {
        return "reservation";
    }

    @ResponseBody
    @GetMapping("/reservations")
    ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok().body(reservations);
    }
}
