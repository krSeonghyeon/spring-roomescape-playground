package roomescape.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.controller.dto.request.ReservationCreateRequest;
import roomescape.controller.dto.response.ReservationResponse;
import roomescape.domain.Reservation;
import roomescape.domain.Time;
import roomescape.repository.ReservationRepository;
import roomescape.repository.TimeRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final TimeRepository timeRepository;

    public ReservationController(
        ReservationRepository reservationRepository,
        TimeRepository timeRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.timeRepository = timeRepository;
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        List<ReservationResponse> responses = reservations.stream()
                .map(ReservationResponse::from)
                .toList();

        return ResponseEntity.ok().body(responses);
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestBody @Valid ReservationCreateRequest request
    ) {
        Time time = timeRepository.getById(request.time());

        Reservation reservation = new Reservation(
                request.name(),
                request.date(),
                time
        );

        Reservation savedReservation = reservationRepository.save(reservation);

        URI location = URI.create("/reservations/" + savedReservation.getId());
        return ResponseEntity.created(location).body(ReservationResponse.from(savedReservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(
            @PathVariable Long id
    ) {
        reservationRepository.getById(id);

        reservationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
