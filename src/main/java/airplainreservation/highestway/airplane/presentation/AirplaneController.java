package airplainreservation.highestway.airplane.presentation;

import airplainreservation.highestway.airplane.application.AirplaneService;
import airplainreservation.highestway.airplane.dto.request.AirplaneRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static airplainreservation.highestway.airplane.dto.request.AirplaneRequest.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airplane")
public class AirplaneController {

    private final AirplaneService airplaneService;

    @PostMapping("/save")
    public void saveAirplaneWithSeatList(@RequestBody AirplaneRegisterRequest airplaneRegisterRequest) {
        airplaneService.saveAirplaneWithSeatList(airplaneRegisterRequest);

    }
}
