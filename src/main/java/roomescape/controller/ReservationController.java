package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roomescape.controller.dto.request.ReservationCreateRequest;
import roomescape.domain.Reservation;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class ReservationController {

    private AtomicLong index = new AtomicLong(1);
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

    @PostMapping("/reservations")
    ResponseEntity<Reservation> createReservation(@RequestBody ReservationCreateRequest request) {
        Reservation reservation = new Reservation(
                index.getAndIncrement(),
                request.name(),
                LocalDate.parse(request.date()),
                LocalTime.parse(request.time())
        );

        reservations.add(reservation);

        URI location = URI.create("/reservations/" + reservation.getId());

        return ResponseEntity.created(location).body(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservations.removeIf(reservation -> reservation.getId().equals(id));
        return ResponseEntity.noContent().build();
    }
}
