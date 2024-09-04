package roomescape.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import roomescape.domain.Reservation;
import roomescape.repository.ReservationRepository;

@Repository
public class ReservationDao implements ReservationRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReservationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Reservation> reservationRowMapper() {
        return (rs, rowNum) -> new Reservation(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getDate("date").toLocalDate(),
            rs.getTime("time").toLocalTime()
        );
    }

    @Override
    public List<Reservation> findAll() {
        String sql = "select id, name, date, time FROM reservation";
        return jdbcTemplate.query(sql, reservationRowMapper());
    }
}
