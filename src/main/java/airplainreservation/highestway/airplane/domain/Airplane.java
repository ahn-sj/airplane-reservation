package airplainreservation.highestway.airplane.domain;

import lombok.AccessLevel;
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

    private String RegistrationNumber;

    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    public void addSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
