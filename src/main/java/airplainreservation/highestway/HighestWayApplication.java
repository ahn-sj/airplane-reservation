package airplainreservation.highestway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HighestWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HighestWayApplication.class, args);
	}

}
