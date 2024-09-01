package roomescape.controller.dto.request;

public record ReservationCreateRequest(
        String name,
        String date,
        String time
) {
}
