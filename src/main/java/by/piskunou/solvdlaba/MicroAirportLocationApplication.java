package by.piskunou.solvdlaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class MicroAirportLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroAirportLocationApplication.class, args);
	}

}
