package roomescape.domain;

import java.time.LocalTime;

public class Time {

    private Long id;

    private LocalTime time;

    public Time() {}

    public Time(LocalTime time) {
        this.time = time;
    }

    public Time(Long id, LocalTime time) {
        this.id = id;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }
}
