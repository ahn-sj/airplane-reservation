package airplainreservation.highestway.airplane;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Airplane {
    @Id @GeneratedValue
    private Long id;

//    Boolean[][] seats = new Boolean[][];

}
