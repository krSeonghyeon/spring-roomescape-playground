package roomescape.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import roomescape.controller.dto.request.TimeCreateRequest;
import roomescape.controller.dto.response.TimeResponse;
import roomescape.domain.Time;
import roomescape.repository.TimeRepository;

@RestController
@RequestMapping("/times")
public class TimeController {

    private final TimeRepository timeRepository;

    public TimeController(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @GetMapping
    public ResponseEntity<List<TimeResponse>> getTimes() {
        List<Time> times= timeRepository.findAll();

        List<TimeResponse> responses = times.stream()
            .map(TimeResponse::from)
            .toList();

        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<TimeResponse> createTime(
        @RequestBody @Valid TimeCreateRequest request
    ) {
        Time time = new Time(request.time());

        Time savedTime = timeRepository.save(time);

        URI Location = URI.create("/times/" + savedTime.getId());
        return ResponseEntity.created(Location).body(TimeResponse.from(savedTime));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTime(
        @PathVariable Long id
    ) {
        timeRepository.getById(id);

        timeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
