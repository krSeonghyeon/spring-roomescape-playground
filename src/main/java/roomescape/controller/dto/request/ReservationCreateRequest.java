package roomescape.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationCreateRequest(
        @NotBlank
        String name,

        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate date,

        @NotNull
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime time
) {
}
