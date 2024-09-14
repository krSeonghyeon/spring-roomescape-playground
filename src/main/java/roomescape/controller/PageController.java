package roomescape.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/reservation")
    public String getReservation() {
        return "new-reservation";
    }

    @GetMapping("/time")
    public String getTime() {
        return "time";
    }
}
