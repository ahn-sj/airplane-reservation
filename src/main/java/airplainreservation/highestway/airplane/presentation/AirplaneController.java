package airplainreservation.highestway.airplane.presentation;

import airplainreservation.highestway.airplane.application.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static airplainreservation.highestway.airplane.dto.request.AirplaneRequest.AirplaneRegisterRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airplane")
public class AirplaneController {

    private final AirplaneService airplaneService;

    @PostMapping("/save")
    public ResponseEntity<Void> saveAirplane(@RequestBody AirplaneRegisterRequest airplaneRegisterRequest) {
        Long airplaneId = airplaneService.saveAirplaneWithSeatList(airplaneRegisterRequest);

        return ResponseEntity.created(URI.create("api/airplane/" + airplaneId)).build();
    }
}
