package airplainreservation.highestway.airplane.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Airplane {
    @Id @GeneratedValue
    private Long id;

    private String registrationNumber;
    private String departure;
    private String arrival;

    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    @Builder
    public Airplane(String registrationNumber, String departure, String arrival) {
        this.registrationNumber = registrationNumber;
        this.departure = departure;
        this.arrival = arrival;
    }

    public static Airplane of(String registrationNumber, String departure, String arrival) {
        return Airplane.builder()
                .registrationNumber(registrationNumber)
                .departure(departure)
                .arrival(arrival)
                .build();
    }
}
