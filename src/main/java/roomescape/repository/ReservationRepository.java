package roomescape.repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import roomescape.domain.Reservation;

public interface ReservationRepository {

    Optional<Reservation> findById(Long id);

    List<Reservation> findAll();

    Reservation save(Reservation reservation);

    void deleteById(Long id);

    default Reservation getById(Long id) {
        return findById(id)
            .orElseThrow(NoSuchElementException::new);
    }
}
